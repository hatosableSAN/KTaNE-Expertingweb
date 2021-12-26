package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

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

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistClass")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistClass extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        // request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        // RequestDispatcher dispatcher =
        // request.getRequestDispatcher("/WEB-INF/registStudentSuccess.jsp");
        // dispatcher.forward(request, response);
        System.out.println("いまdoGet");
        doPost(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまHandのPost");
        List<Student> list = new ArrayList<Student>();
        StudentService service = new StudentService();
        // requestオブジェクトから登録情報の取り出し
        // int class_id = request.getParameter("class_id");
        String class_name = request.getParameter("class_name");
        String classyear = request.getParameter("class_year");
        User user = (User)session.getAttribute("User");
        String class_user = user.getId(); // 今ログインしている教員ユーザ

        //Student studentinfo = new Student("E195407", "キムソクジン", "男", "ABC");

        String tourl = null;
        if (class_name.isEmpty() || classyear.isEmpty()) {
            list = service.getStudent();
            request.setAttribute("List", list);
            //request.setAttribute("Student", studentinfo);
            tourl = "/WEB-INF/classes/registClassError.jsp";
            System.out.println("Please full all class information");
        } else {
            // studentオブジェクトに情報を格納
            int class_year = Integer.parseInt(classyear);
            String[] checkedStudents = request.getParameterValues("student_member");
            System.out.println(Arrays.toString(checkedStudents));
            System.out.println("right here");
            System.out.println(request.getParameter("student_member"));
            for (int i = 0; i < checkedStudents.length; i++) {
                String stu_id = checkedStudents[i];
                String stu_name = null;
                int stu_gender = 0;
                String stu_user = null;
                Student student = new Student(stu_id, stu_name, stu_gender, stu_user);
                student = service.searchStudent(student);
                list.add(student);
            }
            // これには、valueが全部入ってるから、あーメンバーに登録するときに使える これを突っ込めばよい
            // int i = 0;
            /*
             * while(!checkedStudents[i].isEmpty()){
             * list =
             * }
             */
            // for(int i=0;i<checkedStudents.length;i++){ //このfor文はDAOでやろう
            // String student_num = checkedStudents[i];
            // list = service.getStudentNumber(checkedStudents); //やめます！上手くいかない
            // }
            // list = service.getStudent(); //一回コメントアウト

            ClassDef classdef = new ClassDef(class_name, class_year, class_user);
            request.setAttribute("ClassDef", classdef);
            session.setAttribute("List", list);
            //session.setAttribute("Student", studentinfo);
            System.out.println(list);
            System.out.println(session.getAttribute("Student"));
            System.out.println(request.getAttribute("ClassDef"));

            // StudentManagerオブジェクトの生成
            // StudentService service = new StudentService();

            // 登録
            // service.registStudent(student);

            // 成功画面を表示する
            // System.out.println("OK牧場");
            // response.sendRedirect("/TableTennis/RegistInfo");
            tourl = "/WEB-INF/classes/registClassConfirm.jsp"; // パスは、webappにいるところから考えないといけない！
        }

        getServletContext().getRequestDispatcher(tourl).forward(request, response);// 上のdoGetをまとめて書いている

        // studentオブジェクトに情報を格納
        // Student student = new Student(player_n, taikai_n, taikai_l, taikai_k);

        // StudentManagerオブジェクトの生成
        // StudentManager manager = new StudentManager();

        // 登録
        // manager.registStudent(student);

        // 成功画面を表示する
        // System.out.println("OK牧場");
        // response.sendRedirect("/TableTennis/RegistInfo");
    }

}