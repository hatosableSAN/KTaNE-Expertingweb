package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import beans.Student; //beansに入れた方がいいのかしら
import beans.User;
import service.StudentService;
import utility.*;

@WebServlet("/GoStudentTop")

public class GoStudentTop extends HttpServlet {// クラス

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            // RequestDispatcher dispatcher =
            // request.getRequestDispatcher("WEB-INF/student/studentTop.jsp");
            // dispatcher.forward(request, response);
            getServletContext().getRequestDispatcher("/WEB-INF/student/studentTop.jsp").forward(request, response);// 上のdoGetをまとめて書いている
            // System.out.println("doGet now");
            // doPost(request,response);
        }

        /*
         * public void doPost(HttpServletRequest request, HttpServletResponse response)
         * throws ServletException, IOException {
         *
         * // requestオブジェクトの文字エンコーディングの設定
         * request.setCharacterEncoding("UTF-8");
         * // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
         * //RequestDispatcher dispatcher =
         * request.getRequestDispatcher("WEB-INF/student/studentTop.jsp");
         * //dispatcher.forward(request, response);
         * getServletContext().getRequestDispatcher("/WEB-INF/student/studentTop.jsp").
         * forward(request,response);//上のdoGetをまとめて書いている
         * System.out.println("doGet now");
         * //doPost(request,response);
         * }
         */
    }
}
/*
 * public class GoStudentTop extends HttpServlet {
 *
 * public void doGet(HttpServletRequest request, HttpServletResponse response)
 *
 * throws ServletException, IOException {
 *
 * System.out.println("in go student top");
 *
 * //フォワード先の指定
 *
 * RequestDispatcher dispatcher =
 * request.getRequestDispatcher("/WEB-INF/Users/login.jsp");
 *
 * dispatcher.forward(request, response);
 *
 * //System.out.println("サーブレットの終了");
 *
 * }
 * }
 */
