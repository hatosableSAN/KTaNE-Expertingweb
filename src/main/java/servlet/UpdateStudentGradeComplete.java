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
import java.util.Arrays;

import service.StudentService;
import service.ClassService;
import service.GradeService;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utility.*;
//beansに入れた方がいいのかしら
import beans.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/UpdateStudentGradeComplete")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateStudentGradeComplete extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GradeService service = new GradeService();
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {

            int red = Integer.parseInt(request.getParameter("red"));
            int blue = Integer.parseInt(request.getParameter("blue"));
            int green = Integer.parseInt(request.getParameter("green"));
            String comment = (String) request.getParameter("comment");
            String attendance = (String) request.getParameter("attendance");
            int id = Integer.parseInt(request.getParameter("id"));
            Boolean attendance_b = false;
            if (attendance.equals("true")) {
                attendance_b = true;
            } else {
                // 欠席の場合データを初期化
                red = 1;
                blue = 1;
                green = 1;
                comment = "";
            }

            service.updateStudentGrade(red, blue, green, comment, attendance_b, id);

            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/WEB-INF/grade/updateStudentGradeComplete.jsp");
            dispatcher.forward(request, response);

        }
    }
}