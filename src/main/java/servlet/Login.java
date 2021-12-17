
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
@WebServlet("/Login")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Login:Get");
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Users/login.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Login:Post");

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String password2 = null;

        // コンソールに確認するために出力
        System.out.println("（Login）取得した文字列は" + id + "です！");
        System.out.println("（Login）取得した文字列は" + password + "です！");
        System.out.println("（Login）取得した文字列は" + password2 + "です！");

        // userオブジェクトに情報を格納
        User user = new User(id, password, password2);

        System.out.println("（User）取得した文字列は" + user.getId() + "です！");
        System.out.println("（User）取得した文字列は" + user.getPassword() + "です！");
        System.out.println("（User）取得した文字列は" + user.getPassword2() + "です！");

        // requestオブジェクトにオブジェクトを登録
        // request.setAttribute("User", user);

        // Record record2 = new Record(name, tournament, round,date,result);

        // RecordManagerオブジェクトの生成
        UserManager manager = new UserManager();

        boolean ok = false;

        // ログインの可否
        // manager.searchUser(user)の修正を行うことで、ログインできない理由まで表示できそう
        ok = manager.loginUser(user);

        // ログイン
        if (ok == true) {
            System.out.println("ログイン成功");
<<<<<<< HEAD
        	// 完了画面を表示する
        	//RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Users/LoginSuccess.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Users/systemTop.jsp");
            //request.setAttribute("User", user);
            //セッションの作成・取得
=======
            // 完了画面を表示する
            // RequestDispatcher dispatcher =
            // request.getRequestDispatcher("/Users/LoginSuccess.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Users/systemTop.jsp");
            // request.setAttribute("User", user);
            // セッションの作成・取得
>>>>>>> 04f9aa699ff96c1f165bc2cb12f4f574062e0d78
            HttpSession session = request.getSession();
            session.setAttribute("User", user);
            dispatcher.forward(request, response);
        } else {
            System.out.println("ログイン失敗");
<<<<<<< HEAD
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Users/loginFailure.jsp");
=======
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Users/loginFailure.jsp");
>>>>>>> 04f9aa699ff96c1f165bc2cb12f4f574062e0d78
            dispatcher.forward(request, response);
            // response.sendRedirect("/se21g1/Login");
        }

    }
}