package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;
import java.net.http.HttpRequest;

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

import beans.Grade;
import beans.Student; //beansに入れた方がいいのかしら
import beans.StudentSeatingArr;
import service.SeatingService;
import service.StudentService;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistGradeInfoConfirm")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistGradeInfoConfirm extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Classes

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/grade/registGradeInfoConfirm.jsp");
        dispatcher.forward(request, response);

    }

  
}