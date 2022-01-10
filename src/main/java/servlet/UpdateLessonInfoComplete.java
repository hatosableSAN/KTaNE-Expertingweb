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

import beans.Grade;
import beans.Student; //beansに入れた方がいいのかしら
import beans.StudentSeatingArr;
import service.GradeService;
import service.SeatingService;
import service.StudentService;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/UpdateLessonInfoComplete")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateLessonInfoComplete extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./sessionerror.jsp");
            dispatcher.forward(request, response);
        } else {

            GradeService Service = new GradeService();

            String Id = request.getParameter("Id");
            String Date = request.getParameter("LessonDate");
            String PeriodNum = request.getParameter("PeriodNum");
            String Comment = request.getParameter("Comment");

            System.out.println("受け取った文字は");
            System.out.println(Id);
            System.out.println(Date);
            System.out.println(PeriodNum);
            System.out.println(Comment);

            int id = Integer.parseInt(Id);
            int periodnum = Integer.parseInt(PeriodNum);

            Service.UpdateLessonInfo(id, Date, periodnum, Comment);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/grade/updateLessoncomplete.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);

        }
    }
}