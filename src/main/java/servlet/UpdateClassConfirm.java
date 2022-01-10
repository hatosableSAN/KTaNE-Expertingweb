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
@WebServlet("/UpdateClassConfirm")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateClassConfirm extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        // request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        // RequestDispatcher dispatcher =
        // request.getRequestDispatcher("/WEB-INF/registStudentSuccess.jsp");
        // dispatcher.forward(request, response);
        // System.out.println("いまdoGet");
        doPost(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./sessionerror.jsp");
            dispatcher.forward(request, response);
        } else {
            // requestオブジェクトの文字エンコーディングの設定
            request.setCharacterEncoding("UTF-8");
            System.out.println("いまHandのPost");
            List<Student> list = new ArrayList<Student>();
            StudentService stu_service = new StudentService();
            ClassService class_service = new ClassService();
            // requestオブジェクトから登録情報の取り出し
            String classid = request.getParameter("ClassId");
            int class_id = Integer.parseInt(classid);// null
            String classyear = request.getParameter("class_year");
            int class_year = Integer.parseInt(classyear); // null
            System.out.println(classyear);
            String class_name = request.getParameter("class_name");
            // User user = (User)session.getAttribute("User");
            String class_user = request.getParameter("class_user"); // 今ログインしている教員ユーザ

            // Student studentinfo = new Student("E195407", "キムソクジン", "男", "ABC");

            String tourl = null;

            // studentオブジェクトに情報を格納

            String[] checkedStudents = request.getParameterValues("student_member");
            String[] noMember = new String[0];
            System.out.println(Arrays.toString(checkedStudents));
            System.out.println("update class confirm");
            boolean result = false;

            ClassDef classdef = new ClassDef(class_id, class_name, class_year, class_user);
            if (request.getParameter("student_member").equals("")) {
                // クラスから誰もいなくなった
                // ClassDef classdef = new ClassDef(class_name, class_year, class_user);
                System.out.println("now this class became no one");
                checkedStudents = noMember;
                result = class_service.updateClass(classdef, checkedStudents);
                // class_service.registClassOnly(classdef);
            } else {
                // String[] checkedStudents = request.getParameterValues("student_member");
                result = class_service.updateClass(classdef, checkedStudents);// クラス情報(名前と年度)を更新
                for (int i = 0; i < checkedStudents.length; i++) {
                    String stu_id = checkedStudents[i];
                    String stu_name = null;
                    int stu_gender = 0;
                    String stu_user = null;
                    Student student = new Student(stu_id, stu_name, stu_gender, stu_user);
                    student = stu_service.searchStudent(student);
                    list.add(student);
                }
            }
            /*
             * boolean result = class_service.updateClass(classdef,
             * checkedStudents);//クラス情報(名前と年度)を更新
             * for (int i = 0; i < checkedStudents.length; i++) {
             * String stu_id = checkedStudents[i];
             * String stu_name = null;
             * int stu_gender = 0;
             * String stu_user = null;
             * Student student = new Student(stu_id, stu_name, stu_gender, stu_user);
             * student = stu_service.searchStudent(student);
             * list.add(student);
             * }
             */

            if (result == false) {
                System.out.println("couldnot update class");
                tourl = "/WEB-INF/classes/updateClassFail.jsp";
                // RequestDispatcher dispatcher =
                // request.getRequestDispatcher("WEB-INF/student/deleteStudentFail.jsp");
                // dispatcher.forward(request, response);
            }
            // これには、valueが全部入ってるから、あーメンバーに登録するときに使える これを突っ込めばよい
            // int i = 0;
            request.setAttribute("ClassDef", classdef);
            request.setAttribute("List", list);
            // request.setAttribute("Student", studentinfo);
            // System.out.println(list);

            // StudentManagerオブジェクトの生成
            // StudentService service = new StudentService();

            tourl = "/WEB-INF/classes/updateClassComplete.jsp"; // パスは、webappにいるところから考えないといけない！
            // }

            getServletContext().getRequestDispatcher(tourl).forward(request, response);// 上のdoGetをまとめて書いている
        }

    }
}