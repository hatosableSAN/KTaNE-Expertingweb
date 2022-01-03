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

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistSeatingClass")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistSeatingClass extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 座席配置新規登録ボタンを押した時
        System.out.println("いまdoGet");

        // セッション
        HttpSession session = request.getSession();
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        ClassService ClassService = new ClassService();
        List<ClassDef> ClassDefList = ClassService.getAllClass();
        // 全クラスの情報をrequestにいれる
        request.setAttribute("ClassDefList", ClassDefList);
        session.setAttribute("ClassDefList", ClassDefList);// TODO:いらないかも？

        // セッションの初期化処理を入れる(作りかけで戻った場合にはリセットされたほうが良いので
        // 「配置されている生徒一覧セッション」に初期化した情報を入れる
        session.setAttribute("StudentList", null); // 座席に配置されていない生徒一覧
        session.setAttribute("setStudentList", null);// 座席に配置している生徒一覧
        session.setAttribute("StudentSeatingArrList", null);// 座席一覧（座席番号と生徒）
        session.setAttribute("ClassDef", null); // クラスの情報を初期化
        session.setAttribute("SeatingArrangements", null); // 座席配置を初期化

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/registSeatingClass.jsp");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまPost");
        ClassService ClassService = new ClassService();

        HttpSession session = request.getSession();
        // User User = (User) session.getAttribute("User");

        // クラスIDからクラスの情報を取得
        int classId = Integer.parseInt(request.getParameter("classId"));
        ClassDef Classdef = new ClassDef(classId);
        ClassDef ClassDef = ClassService.findClass(Classdef);
        System.out.println(ClassDef);
        // request.setAttribute("ClassDef", ClassDef);// いらないかも？
        session.setAttribute("ClassDef", ClassDef);

        // クラスIDから全ての生徒情報を取得
        List<Student> studentList = ClassService.getAllClassmember(Classdef);
        System.out.println(studentList);
        // request.setAttribute("StudentList", studentList);// いらないかも？
        session.setAttribute("StudentList", studentList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/registSeating.jsp");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        dispatcher.forward(request, response);
    }

}