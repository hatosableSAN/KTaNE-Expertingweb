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
@WebServlet("/ClassTop")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class ClassTop extends HttpServlet {

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
            User user = (User) session.getAttribute("User");
            // requestオブジェクトの文字エンコーディングの設定
            request.setCharacterEncoding("UTF-8");
            // Classserviceobjectの作成
            ClassService ClassService = new ClassService();
            List<ClassDef> myClassDefList = ClassService.getAllMyClass(user.getId());
            List<ClassDef> otherClassDefList = ClassService.getAllOtherClass(user.getId());
            request.setAttribute("myClassDefList", myClassDefList);
            request.setAttribute("otherClassDefList", otherClassDefList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/classes/classTop.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまdoGet");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./sessionerror.jsp");
            dispatcher.forward(request, response);
        } else {
            // requestオブジェクトの文字エンコーディングの設定
            request.setCharacterEncoding("UTF-8");
            // Classserviceobjectの作成
            ClassService ClassService = new ClassService();
            ClassDef ClassDef = new ClassDef();
            ClassDef.setClass_id(Integer.parseInt(request.getParameter("ClassId")));
            List<Student> StudentList = ClassService.getAllClassmember(ClassDef);
            ClassDef = ClassService.searchClass(ClassDef);
            request.setAttribute("ClassDef", ClassDef);
            request.setAttribute("List", StudentList);

            // seatingsserviceobjectの作成
            SeatingService seatingService = new SeatingService();
            List<SeatingArrangements> seatingArrangementsList = seatingService.getSeatingArrangements(ClassDef);

            // クラスに座席配置情報が登録されているかどうか調べ、していたらCandeleteにtrue
            boolean Candelete = false;
            if (seatingArrangementsList.size() == 0) {
                Candelete = true;
            }
            request.setAttribute("Candelete", Candelete);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/classes/classInfo.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }

}