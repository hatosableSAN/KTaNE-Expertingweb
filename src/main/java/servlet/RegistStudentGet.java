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

import beans.Student; //beansに入れた方がいいのかしら
import service.StudentService;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistStudentGet")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistStudentGet extends HttpServlet {

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
            System.out.println("いまHandのPost");
            List<Student> list = new ArrayList<Student>();
            List<Student> list_all = new ArrayList<Student>();

            String tourl = null;
            int check=0;//flag 0の時は初実行
            // StudentManagerオブジェクトの生成
            StudentService service = new StudentService();

            // findAll
            list = service.getStudent();
            list_all = service.getStudent();
            // String gender = list.get();
            // Student studentinfo = new Student("E195407", "キムソクジン", "男", "ABC");
            // list.add(studentinfo);
            // session.setAttribute("Student",studentinfo);
            request.setAttribute("List", list);
            request.setAttribute("List_all",list_all);
            request.setAttribute("Check", check);
            // System.out.println(session.getAttribute("Student"));

            // 成功画面を表示する
            // System.out.println("OK牧場");
            // response.sendRedirect("/TableTennis/RegistInfo");
            tourl = "/WEB-INF/classes/registClass.jsp"; // パスは、webappにいるところから考えないといけない！

            // }

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