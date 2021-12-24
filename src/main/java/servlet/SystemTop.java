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


@WebServlet("/SystemTop")
// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class SystemTop extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("SystemTop");
      request.setCharacterEncoding("UTF-8");

      //➀セッションの作成・取得
      HttpSession session = request.getSession();
      User user = new User();
      user=(User)session.getAttribute("User");

      RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF//Users/systemTop.jsp");
      dispatcher.forward(request, response);
    }
}
