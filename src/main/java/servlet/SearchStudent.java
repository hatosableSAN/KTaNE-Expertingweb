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

import beans.Student; //beansに入れた方がいいのかしら
import service.StudentService;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/SearchStudent")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class SearchStudent extends HttpServlet {

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
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        // System.out.println("いまHandのPost");

        // requestオブジェクトから登録情報の取り出し
        String stu_search = request.getParameter("stu_search");
        List<Student> list = new ArrayList<Student>();
        // List<Student> list = new ArrayList<Student>();
        StudentService service = new StudentService();
        // String stu_name = request.getParameter("stu_name");
        // String stu_gender = request.getParameter("stu_gender");
        // int stu_gender = Integer.parseInt(gender);
        // String stu_user = "ABC"; //今ログインしている教員ユーザ
        // String taikai_l = request.getParameter("taikai_l");
        // String taikai_k = request.getParameter("taikai_k");
        // session.setAttribute("stu_id",stu_id);
        // session.setAttribute("stu_id",stu_name);
        // session.setAttribute("stu_id",stu_gender);
        // session.setAttribute("stu_id",stu_user);

        String tourl = null;
        if (stu_search.isEmpty()) { // 本当は良くないけど、テキストボックスが空だったら一覧表示
            list = service.getStudent();
            request.setAttribute("List", list);
            System.out.println("Please full all");
            tourl = "/WEB-INF/classes/registClass.jsp";
        } else {
            // studentオブジェクトに情報を格納
            String stu_id = null;
            String stu_name = null;
            int stu_gender = 0;
            String stu_user = null;
            Student student = new Student(stu_id, stu_name, stu_gender, stu_user);
            // StudentMemo studentmemo = new StudentMemo(stu_id, stu_name,
            // stu_gender,stu_user);
            // HttpSession session = request.getSession();
            // session.setAttribute("StudentMemo",studentmemo);
            // session.setAttribute("Student", student);

            // StudentManagerオブジェクトの生成
            // StudentService service = new StudentService();

            // 登録
            // list = service.searchStudent(stu_search);
            request.setAttribute("List", list);

            // 成功画面を表示する
            // System.out.println("OK牧場");
            // response.sendRedirect("/TableTennis/RegistInfo");
            tourl = "/WEB-INF/classes/regist.jsp"; // パスは、webappにいるところから考えないといけない！
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