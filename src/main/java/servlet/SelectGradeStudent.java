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

import beans.ClassDef; //beansに入れた方がいいのかしら
import beans.Student;
import beans.User;
import service.StudentService;
import service.ClassService;
import service.SeatingService;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utility.*;
import beans.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/SelectGradeStudent")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class SelectGradeStudent extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまdoGet");

        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./sessionerror.jsp");
            dispatcher.forward(request, response);
        } else {

            StudentService Service = new StudentService();

            List<Student> StudentList = Service.getStudent();

            request.setAttribute("StudentList", StudentList);
            System.out.println(StudentList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/grade/selectStudent.jsp");

            dispatcher.forward(request, response);
        }
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる

        // requestオブジェクトには、フォームで入力された文字列などが格納されている。
        // responseオブジェクトを使って、次のページを表示する

    }
}