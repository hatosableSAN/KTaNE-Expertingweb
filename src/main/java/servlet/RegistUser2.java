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

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistUser2")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistUser2 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // ➀セッションの作成・取得
        HttpSession session = request.getSession();

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        User userS = new User();

        userS = (User) session.getAttribute("User");

        String id = userS.getId();
        String password = userS.getPassword();
        String password2 = userS.getPassword2();

        // String id = request.getParameter("id");
        // String password = request.getParameter("password");
        // String password2 = request.getParameter("password2");

        // コンソールに確認するために出力
        System.out.println("(RegistUser2)取得した文字列は" + id + "です！");
        System.out.println("(RegistUser2)取得した文字列は" + password + "です！");
        System.out.println("(RegistUser2)取得した文字列は" + password2 + "です！");

        // userオブジェクトに情報を格納
        User user = new User(id, password, password2);

        if (id == null) {
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Users/registUserFailure.jsp");
            dispatcher.forward(request, response);
        } else {
            // RecordManagerオブジェクトの生成
            UserManager manager = new UserManager();

            boolean ex = manager.serchUser(user);

            System.out.println("もう登録してある？");
            System.out.println(ex);

            if (ex == true) {
                // response.sendRedirect("/se21g1/RegistUserRe");
                System.out.println("ユーザ登録画面の再表示（すでにあるID入力のため）");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Users/registUserRe.jsp");
                dispatcher.forward(request, response);
            }

            System.out.println("登録するぜ");
            manager.registUser(user);

            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Users/registUserSuccess.jsp");
            dispatcher.forward(request, response);
        }
    }

}
