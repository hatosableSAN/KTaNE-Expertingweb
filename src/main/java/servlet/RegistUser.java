//--------------------------------
//	RegistInfo.java
//--------------------------------
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

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistUser")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistUser extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RegistUser:Get");
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Users/registUser.jsp");
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RegistUser:Post");

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        // コンソールに確認するために出力
        System.out.println("取得した文字列は" + id + "です！");
        System.out.println("取得した文字列は" + password + "です！");
        System.out.println("取得した文字列は" + password2 + "です！");

        // userオブジェクトに情報を格納
        User user = new User(id, password,password2);

        //Record record2 = new Record(name, tournament, round,date,result);

        // 登録
        if(password.equals(password2)) {
        	// 確認画面を表示する
        	//response.sendRedirect("/StuInfo/RegistInfo");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/Users/checkRegistUser.jsp");

            //セッションの作成・取得
            HttpSession session = request.getSession();
            session.setAttribute("User", user);
            //request.setAttribute("User", user);
            dispatcher.forward(request, response);
        }
        else {
            //response.sendRedirect("/se21g1/RegistUser");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/Users/registUserPass.jsp");
        	dispatcher.forward(request, response);
        }

    }
}
