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
import java.io.PrintWriter;

import beans.Student; //beansに入れた方がいいのかしら
import service.StudentService;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/DeleteStudentConfirm")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class DeleteStudentConfirm extends HttpServlet {

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
            // System.out.println("いまHandのPost");

            // requestオブジェクトから登録情報の取り出し
            // String stu_id = request.getParameter("stu_id");
            Student student_confirm = (Student) session.getAttribute("Student");
            String stu_id = request.getParameter("stu_id");
            String stu_name = null;// student_confirm.getStudent_name();
            // int stu_gender = request.getParameter("stu_gender");
            int stu_gender = 0;// student_confirm.getStudent_gender();
            String stu_user = null; // student_confirm.getStudent_user();
            Student student = new Student(stu_id, stu_name, stu_gender, stu_user);
            StudentService service = new StudentService();
            student = service.searchStudent(student);

            // UserManager manager = new UserManager();

            boolean result = service.deleteStudent(student);// ここでbooleanかつ削除もする
            String tourl = null;

            System.out.println("after boolean delete and " + result);
            if (result == false) {
                // response.sendRedirect("/se21g1/RegistUserRe");
                System.out.println("couldnot delete");
                tourl = "/WEB-INF/student/deleteStudentFail.jsp";
                // RequestDispatcher dispatcher =
                // request.getRequestDispatcher("WEB-INF/student/deleteStudentFail.jsp");
                // dispatcher.forward(request, response);
            } else {
                tourl = "/WEB-INF/student/deleteStudentComplete.jsp"; // パスは、webappにいるところから考えないといけない！
            }

            // tourl = "/WEB-INF/student/deleteStudentComplete.jsp";
            // //パスは、webappにいるところから考えないといけない！

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
}