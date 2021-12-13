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
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Users/registUser.jsp");
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
        System.out.println("(RegistUser)取得した文字列は" + id + "です！");
        System.out.println("(RegistUser)取得した文字列は" + password + "です！");
        System.out.println("(RegistUser)取得した文字列は" + password2 + "です！");

        // userオブジェクトに情報を格納
        User user = new User(id, password,password2);

        //IDが６～１５文字であるかどうか
        if(checkID(id)==false){
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Users/registUserIdOK.jsp");
        	dispatcher.forward(request, response);
        }
        
        //パスワードが半角英数字を含み、８～１５文字であるかどうか
        if(checkPass(password)==false){
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Users/registUserPassOK.jsp");
        	dispatcher.forward(request, response);
        }

        // 登録
        if(password.equals(password2)) {
        	// 確認画面を表示する
        	//response.sendRedirect("/StuInfo/RegistInfo");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Users/checkRegistUser.jsp");

            //セッションの作成・取得
            HttpSession session = request.getSession();
            session.setAttribute("User", user);
            //request.setAttribute("User", user);
            dispatcher.forward(request, response);
        }
        else {
            //response.sendRedirect("/se21g1/RegistUser");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Users/registUserPass.jsp");
        	dispatcher.forward(request, response);
        }
    }
    public boolean checkID(String id){
        int len = id.length();
        if(len<6){
            System.out.println("IDが短い");
            return false;
        }
        return true;
    }

    public boolean checkPass(String pass){
        char[] c = pass.toCharArray();
        int len = pass.length();
        //boolean s = false;
        //boolean l = false;
        boolean e= false;
        boolean n= false;
        boolean ok =false;

        if(len<8){
            return false;
        }

        for(int i=0;i<len;i=i+1) {
        	int c2 =c[i] ;
        	if(c2>=97&&c2<=122) {
        		e=true;
        		break;
        	}
        }
        for(int i=0;i<len;i=i+1) {
        	int c2 =c[i] ;
        	if(c2>=65&&c2<=90) {
        		e=true;
        		break;
        	}
        }
        for(int i=0;i<len;i=i+1) {
        	int c2 =c[i] ;
        	if(c2>=48&&c2<=57) {
        		n=true;
        		break;
        	}
        }
        if(e==true&&n==true) {
        	System.out.println("このパスワードは使ってよし");
            ok=true;
        }

        return ok;
    }
}
