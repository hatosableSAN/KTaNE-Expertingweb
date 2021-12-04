
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Users/LoginSuccess.jsp");
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
        System.out.println("取得した文字列は" + id + "です！");
        System.out.println("取得した文字列は" + password + "です！");
        System.out.println("取得した文字列は" + password2 + "です！");

        // userオブジェクトに情報を格納
        User user = new User(id, password,password2);
        User user2 = new User(null,null,null);

        //Record record2 = new Record(name, tournament, round,date,result);

        // RecordManagerオブジェクトの生成
        UserManager manager = new UserManager();

        //boolean ok =false;

        // ユーザ検索
        user2= manager.searchUser(user);

        // ログイン
        if(user2!=null) {

        	// 完了画面を表示する
        	//response.sendRedirect("/StuInfo/RegistInfo");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("../webapp/Users/LoginSuccess.jsp");
            dispatcher.forward(request, response);
        }
        else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("../webapp/Users/LoginSuccess.jsp");
        	dispatcher.forward(request, response);
        }

    }
}
