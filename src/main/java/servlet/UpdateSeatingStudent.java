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

import beans.ClassDef; //beansに入れた方がいいのかしら
import beans.Student;
import beans.User;
import service.StudentService;
import service.ClassService;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utility.*;
import beans.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/UpdateSeatingStudent")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateSeatingStudent extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 確定画面から戻ってきたとき
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
            session.setAttribute("StudentList", studentList);

            SeatingArrangements seatingArrangements = (SeatingArrangements) session.getAttribute("SeatingArrangements");
            session.setAttribute("SeatingArrangements", seatingArrangements);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/updateSeating.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまPost");

        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            // requestから変更された座席配置情報を取得
            String startdate = request.getParameter("startdate");// + " 00:00:00";
            System.out.println(startdate);
            String enddate = request.getParameter("enddate");// + " 00:00:00";
            System.out.println(enddate);
            String seatname = request.getParameter("seatname");

            // 座席配置情報に追加し、セッションに追加
            SeatingArrangements seatingArrangements = (SeatingArrangements) session.getAttribute("SeatingArrangements");
            seatingArrangements.setStartDate(startdate);
            seatingArrangements.setEndDate(enddate);
            seatingArrangements.setName(seatname);
            session.setAttribute("SeatingArrangements", seatingArrangements);
            System.out.println(seatingArrangements.getStartDate());
            System.out.println(seatingArrangements.getEndDate());

            // User User = (User) session.getAttribute("User");seatname
            // session.setAttribute("User", user);

            // postされた座席と生徒の対応関係を取得
            int seatNum = Integer.parseInt(request.getParameter("seatNum"));
            String StudentId = request.getParameter("StudentId");
            System.out.println(seatNum + ":" + StudentId);

            if (StudentId != "") {
                sakujo(request, seatNum);
                // 座らせた生徒(idから生徒情報を取得している)
                StudentService StudentService = new StudentService();
                Student student = new Student();
                student.setStudent_id(StudentId);
                Student setStudent = StudentService.searchStudent(student);
                System.out.println(setStudent.getStudent_id() + ":" + setStudent.getStudent_name());

                // 「配置されている生徒一覧セッション」に座らせた生徒を入れる
                List<Student> setstudentList = new ArrayList<Student>();
                if ((List<Student>) session.getAttribute("setStudentList") != null) {
                    setstudentList = (List<Student>) session.getAttribute("setStudentList");
                }
                setstudentList.add(setStudent);
                session.setAttribute("setStudentList", setstudentList);

                // 「配置されていない生徒一覧をセッション」から座らせた生徒を削除する
                List<Student> studentList = new ArrayList<Student>();
                if ((List<Student>) session.getAttribute("StudentList") != null) {
                    studentList = (List<Student>) session.getAttribute("StudentList");
                }
                for (int i = 0; i < studentList.size(); i++) {
                    System.out.println(studentList.get(i).getStudent_id() + " ?= " + setStudent.getStudent_id());
                    if (studentList.get(i).getStudent_id().equals(setStudent.getStudent_id())) {
                        System.out.println(i + ": StudentListから削除");
                        studentList.remove(i);
                    }
                }
                session.setAttribute("StudentList", studentList);

                // 「生徒座席一覧セッション」に座らせた生徒と座席の情報を入れる
                StudentSeatingArr studentseatingarr = new StudentSeatingArr(-1, -1, StudentId, seatNum);
                System.out.println(StudentId + ":::" + seatNum);
                List<StudentSeatingArr> studentSeatingArrList = new ArrayList<StudentSeatingArr>();
                if ((List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList") != null) {
                    studentSeatingArrList = (List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList");
                }
                studentSeatingArrList.add(studentseatingarr);
                session.setAttribute("StudentSeatingArrList", studentSeatingArrList);
            } else {
                System.out.println("「なし」が選ばれた");
                // TODO:「なし」の時
                // 生徒座席一覧セッションから「なし」にした席の生徒の情報を削除する（もともと「なし」の場合も場合分け)

                // 選択された席に座っている生徒がいるかどうかを確かめる
                sakujo(request, seatNum);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/updateSeating.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }

    public void sakujo(HttpServletRequest request, int seatNum) {// すでに座席に座っている生徒がいる時,入れ替える関数
        // request.setCharacterEncoding("UTF-8");
        System.out.println("いま削除関数");

        HttpSession session = request.getSession();
        // 選択された席に座っている生徒がいるかどうかを確かめる
        // 生徒と座席の関係セッション情報を取得する
        List<StudentSeatingArr> studentSeatingArrList = new ArrayList<StudentSeatingArr>();
        if ((List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList") != null) {
            studentSeatingArrList = (List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList");
        }
        // 座席番号と生徒と座席のsession情報を照合
        for (int i = 0; i < studentSeatingArrList.size(); i++) {
            if (studentSeatingArrList.get(i).getSeat() == seatNum) {// 既に座っている生徒がいる
                System.out.println("既に座っている生徒がいる");
                // 配置された生徒一覧セッションから該当生徒を削除
                List<Student> setstudentList = new ArrayList<Student>();
                StudentService StudentService = new StudentService();
                Student student = new Student();
                Student existStudent = new Student();
                if ((List<Student>) session.getAttribute("setStudentList") != null) {
                    setstudentList = (List<Student>) session.getAttribute("setStudentList");
                    student.setStudent_id(setstudentList.get(i).getStudent_id());
                    // 座っている生徒(idから生徒情報を取得している)
                    System.out.println("座っている生徒ID：" + student.getStudent_id());
                    existStudent = StudentService.searchStudent(student);
                    // 削除
                    System.out.println("配置された生徒一覧セッションから削除");
                    setstudentList.remove(i);
                    session.setAttribute("setStudentList", setstudentList);
                }

                // 配置されていない生徒一覧セッションに該当生徒を追加
                List<Student> studentList = new ArrayList<Student>();
                if ((List<Student>) session.getAttribute("StudentList") != null) {
                    studentList = (List<Student>) session.getAttribute("StudentList");
                }
                // 追加
                System.out.println("配置されていない生徒一覧セッションに追加" + existStudent.getStudent_id() + ":"
                        + existStudent.getStudent_name());
                studentList.add(existStudent);
                session.setAttribute("StudentList", studentList);

                // 座席情報セッションから削除
                System.out.println("座席情報セッションから削除");
                studentSeatingArrList.remove(i);
            }
        }
    }

}