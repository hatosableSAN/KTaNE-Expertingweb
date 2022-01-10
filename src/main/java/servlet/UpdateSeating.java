package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import beans.*;
import beans.Student;
import beans.User;
import service.StudentService;
import service.ClassService;
import service.SeatingService;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/UpdateSeating")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateSeating extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 座席配置変更確定ボタンを押す
        System.out.println("いまdoGet");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            // User User = (User) session.getAttribute("User");
            // session.setAttribute("User", user);

            // 「配置されている生徒一覧セッション」に座らせた生徒を入れる
            List<Student> setstudentList = new ArrayList<Student>();
            if ((List<Student>) session.getAttribute("setStudentList") != null) {
                setstudentList = (List<Student>) session.getAttribute("setStudentList");
            }
            session.setAttribute("setStudentList", setstudentList);

            List<Student> studentList = new ArrayList<Student>();
            if ((List<Student>) session.getAttribute("StudentList") != null) {
                studentList = (List<Student>) session.getAttribute("StudentList");
            }
            // 座席配置情報をセッションから取得
            SeatingArrangements setseatingArrangements = new SeatingArrangements();
            setseatingArrangements = (SeatingArrangements) session.getAttribute("SeatingArrangements");

            // 「生徒座席一覧(studentSeatingArrList)」の情報を取得
            List<StudentSeatingArr> studentSeatingArrList = new ArrayList<StudentSeatingArr>();
            if ((List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList") != null) {
                studentSeatingArrList = (List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/updateSeatingconfirm.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 座席配置変更が押された時
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまPost");
        // サービスobjectの生成
        ClassService ClassService = new ClassService();
        SeatingService SeatingService = new SeatingService();
        StudentService studentService = new StudentService();

        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            // User User = (User) session.getAttribute("User");

            // セッションの初期化処理を入れる
            // 「配置されている生徒一覧セッション」に初期化した情報を入れる
            session.setAttribute("StudentList", null);
            session.setAttribute("setStudentList", null);
            session.setAttribute("StudentSeatingArrList", null);
            //

            // 座席配置Idから座席配置の情報を取得
            int seatingId = Integer.parseInt(request.getParameter("SeatingId"));
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setId(seatingId);
            seatingArrangements = SeatingService.getSeatingArrangements(seatingArrangements);
            // 後半の00:00:00を削除
            seatingArrangements.setStartDate(
                    seatingArrangements.getStartDate().substring(0, seatingArrangements.getStartDate().length() - 9));
            if (seatingArrangements.getEndDate() != null) {
                seatingArrangements.setEndDate(
                        seatingArrangements.getEndDate().substring(0, seatingArrangements.getEndDate().length() - 9));
            }
            if (seatingArrangements.getName() == null) {
                seatingArrangements.setName("");
            }

            // 座席配置から、クラス情報、クラスの生徒情報 、座席情報の取得
            // クラスの生徒情報 ...クラスIDから全ての生徒情報を取得
            ClassDef ClassDef = new ClassDef(seatingArrangements.getClassId());
            ClassDef = ClassService.findClass(ClassDef);
            List<Student> studentList = ClassService.getAllClassmember(ClassDef);
            List<StudentSeatingArr> studentSeatingArrsList = new ArrayList<StudentSeatingArr>();
            studentSeatingArrsList = SeatingService.getStudentSeatingArrList(seatingArrangements);

            // List<Student> setstudentList = ClassService.getAllClassmember(ClassDef);
            List<Student> setstudentList = new ArrayList<Student>();
            // 座席に座っている生徒をset済み生徒リストに追加
            System.out.println("・座席に座っている生徒をset済み生徒リストに追加");
            for (int i = 0; i < studentSeatingArrsList.size(); i++) {
                Student student = new Student();
                student.setStudent_id(studentSeatingArrsList.get(i).getStudentId());
                student = studentService.searchStudent(student);
                System.out.println("座っている生徒：" + student.getStudent_id() + ":" + student.getStudent_name());
                setstudentList.add(student);
            }

            // 座席に座っていない生徒リストを作成
            System.out.println("・席に座っていない生徒リスト作成");
            List<Student> nosetstudentList = new ArrayList<Student>();
            for (int i = 0; i < studentList.size(); i++) {
                int flag = 0;// 席に座っているかどうか(座っている=1座ってない=0)
                for (int j = 0; j < setstudentList.size(); j++) {
                    if (studentList.get(i).getStudent_id().equals(setstudentList.get(j).getStudent_id())) {
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    System.out.println(
                            "座っていない生徒：" + studentList.get(i).getStudent_id() + ": "
                                    + studentList.get(i).getStudent_name());
                    nosetstudentList.add(studentList.get(i));
                    System.out.println(studentList.get(i).getStudent_name());
                }
            }

            System.out.println(ClassDef);
            System.out.println(studentList);

            // クラス情報をsessionに追加
            request.setAttribute("ClassDef", ClassDef);
            session.setAttribute("ClassDef", ClassDef);
            // 座席情報と座席に座っている生徒をsessionに追加
            session.setAttribute("SeatingArrangements", seatingArrangements);
            session.setAttribute("StudentSeatingArrList", studentSeatingArrsList);
            // 座席に座っていない生徒と座っている生徒をsessionに追加
            session.setAttribute("StudentList", nosetstudentList);
            session.setAttribute("setStudentList", setstudentList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/updateSeating.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }

}