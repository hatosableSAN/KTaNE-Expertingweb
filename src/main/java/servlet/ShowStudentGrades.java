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

import beans.ClassDef;
import beans.Grade;
import beans.Lessons;
import beans.SeatingArrangements;
import beans.Student; //beansに入れた方がいいのかしら
import beans.StudentSeatingArr;
import service.ClassService;
import service.GradeService;
import service.SeatingService;
import service.StudentService;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/ShowStudentGrades")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class ShowStudentGrades extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        System.out.println("生徒評価確認");
        String idnumber=request.getParameter("studentid");
        int id=Integer.parseInt(idnumber);//授業ID
        GradeService service=new GradeService();
        List<Grade> GradeList=service.getStudentGradeList(id);//ひょうかりすと
        session.setAttribute("Grade", GradeList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/grade/showStudentGradesList.jsp");
        dispatcher.forward(request, response);

    }

}