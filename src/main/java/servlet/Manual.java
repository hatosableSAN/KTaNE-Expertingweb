
//　自分が格納されているフォルダ名
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

import beans.User;
import control.UserManager;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/Manual")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class Manual extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Manual:Get");
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./sessionerror.jsp");
            dispatcher.forward(request, response);
        } else {
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Users/manual.jsp");
            dispatcher.forward(request, response);
        }
    }

}
