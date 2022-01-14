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
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/UpdateClass")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateClass extends HttpServlet {

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
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            // requestオブジェクトの文字エンコーディングの設定
            request.setCharacterEncoding("UTF-8");
            // System.out.println("いまHandのPost");
            List<Student> stu_list = new ArrayList<Student>();// 全児童
            List<Student> stu_classlist = new ArrayList<Student>();// クラスに所属している児童
            StudentService stu_service = new StudentService();
            ClassService class_service = new ClassService();

            String classid = request.getParameter("ClassId");
            int class_id = Integer.parseInt(classid);// null
            String class_name = null;
            int class_year = 0;
            User user = (User) session.getAttribute("User");
            String class_user = null;// user.getId(); // 今ログインしている教員ユーザ

            String tourl = null;
            // if (class_name.isEmpty() || classyear.isEmpty()) {
            ClassDef classdef = new ClassDef(class_id, class_name, class_year, class_user);
            classdef = class_service.searchClass(classdef);// クラス情報を取得
            stu_classlist = class_service.getAllClassmember(classdef);// クラスに所属している児童を取得

            stu_list = stu_service.getStudent();// 全児童を取得

            session.setAttribute("Stu_list", stu_list);// sessionもしくはrequest
            session.setAttribute("Stu_classlist", stu_classlist);
            session.setAttribute("ClassDef", classdef);
            // request.setAttribute("Student", studentinfo);
            // tourl = "/classes/registClassError.jsp";
            // System.out.println("Please full all class information");
            // } else {
            // studentオブジェクトに情報を格納
            // int class_year = Integer.parseInt(classyear);
            // String[] checkedStudents = request.getParameterValues("student_member");
            // System.out.println(Arrays.toString(checkedStudents));
            // System.out.println("right here");
            // System.out.println(request.getParameter("student_member"));
            /*
             * for (int i = 0; i < checkedStudents.length; i++) {//これは確認の時に必要だったような
             * String stu_id = checkedStudents[i];
             * String stu_name = null;
             * int stu_gender = 0;
             * String stu_user = null;
             * Student student = new Student(stu_id, stu_name, stu_gender, stu_user);
             * student = service.searchStudent(student);
             * list.add(student);
             * }
             */
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

            // ClassDef classdef = new ClassDef(class_name, class_year, class_user);
            // request.setAttribute("ClassDef", classdef);
            // session.setAttribute("List", list);
            // session.setAttribute("Student", studentinfo);
            // System.out.println(list);
            // System.out.println(session.getAttribute("Student"));
            // System.out.println(request.getAttribute("ClassDef"));

            tourl = "/WEB-INF/classes/updateClass.jsp"; // パスは、webappにいるところから考えないといけない！
            // }

            getServletContext().getRequestDispatcher(tourl).forward(request, response);// 上のdoGetをまとめて書いている

        }
    }

}