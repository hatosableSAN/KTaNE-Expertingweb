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
import service.SeatingService;
import service.StudentService;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistGradeInfo")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistGradeInfo extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        System.out.println("教室風評価登録");
        String idnumber=request.getParameter("id");
        int id=Integer.parseInt(idnumber);
        session.setAttribute("Seatnum", idnumber);
        SeatingService Service = new SeatingService();
        List<StudentSeatingArr> StudentList=Service.getStudentSeatingArrList(id);
        session.setAttribute("StudentSeatingArrList", StudentList);//ゲットした座席リストをセッションに入れるよ

        List<StudentSeatingArr> NoGradeStudentList=Service.getStudentSeatingArrList(id);
        session.setAttribute("NoGradeStudentList", NoGradeStudentList);//ゲットした座席リストをセッションに入れるよ

        List<Grade> Grade=new ArrayList<Grade>();
        session.setAttribute("Grade", Grade);//ゲットした座席リストをセッションに入れるよ

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/grade/registGrade.jsp");
        dispatcher.forward(request, response);

    }

  
}