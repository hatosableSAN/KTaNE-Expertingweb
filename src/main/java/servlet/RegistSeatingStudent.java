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
import beans.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistSeatingStudent")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistSeatingStudent extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("いまdoGet");
        // // RequestDispatcher dispatcher =
        // // request.getRequestDispatcher("/WEB-INF/registStudentSuccess.jsp");
        // // dispatcher.forward(request, response);

        // // requestオブジェクトの文字エンコーディングの設定
        // request.setCharacterEncoding("UTF-8");
        // ClassService ClassService = new ClassService();
        // List<ClassDef> ClassDefList = ClassService.getAllClass();
        // request.setAttribute("ClassDefList", ClassDefList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/registSeating.jsp");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまPost");

        HttpSession session = request.getSession();
        // User User = (User) session.getAttribute("User");
        // session.setAttribute("User", user);

        // postされた座席と生徒の対応関係を取得
        int seatNum = Integer.parseInt(request.getParameter("seatNum"));
        String StudentId = request.getParameter("StudentId");
        // String StudentName = request.getParameter("StudentName");
        // String StudentGender = request.getParameter("StudentGender");
        // String StudentUser = request.getParameter("StudentUser");
        // System.out.println(seatNum + ":" + StudentId + " " + StudentName + " " +
        // StudentGender + " " + StudentUser);
        System.out.println(seatNum + ":" + StudentId);

        if (StudentId != "") {
            // 座らせた生徒(idから生徒情報を取得している)
            StudentService StudentService = new StudentService();
            Student student = new Student();
            student.setStudent_id(StudentId);
            Student setStudent = StudentService.searchStudent(student);
            System.out.println(setStudent.getStudent_id() + ":" + setStudent.getStudent_name());

            // 「配置されている生徒一覧セッション」に座らせた生徒を入れる
            List<Student> setstudentList = new ArrayList<Student>();
            if ((List<Student>) session.getAttribute("setStudentList") != null) {
                setstudentList = (List<Student>) session.getAttribute("setStudentList");
            }
            setstudentList.add(setStudent);
            session.setAttribute("setStudentList", setstudentList);

            // 「配置されていない生徒一覧をセッション」から座らせた生徒を削除する
            List<Student> studentList = new ArrayList<Student>();
            if ((List<Student>) session.getAttribute("StudentList") != null) {
                studentList = (List<Student>) session.getAttribute("StudentList");
            }
            for (int i = 0; i < studentList.size(); i++) {
                System.out.println(studentList.get(i).getStudent_id() + " ?= " + setStudent.getStudent_id());
                if (studentList.get(i).getStudent_id().equals(setStudent.getStudent_id())) {
                    System.out.println(i + ": StudentListから削除");
                    studentList.remove(i);
                }
            }
            session.setAttribute("StudentList", studentList);

            // 「生徒座席一覧セッション」に座らせた生徒と座席の情報を入れる
            StudentSeatingArr studentseatingarr = new StudentSeatingArr(-1, -1, StudentId, seatNum);
            List<StudentSeatingArr> studentSeatingArrList = new ArrayList<StudentSeatingArr>();
            if ((List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList") != null) {
                studentSeatingArrList = (List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList");
            }
            studentSeatingArrList.add(studentseatingarr);
            session.setAttribute("StudentSeatingArrList", studentSeatingArrList);
        } else {
            System.out.println("「なし」が選ばれた");
            // TODO:「なし」の時
            // 生徒座席一覧セッションから「なし」にした席の生徒の情報を削除する（もともと「なし」の場合も場合分け)
            // 配置された生徒一覧セッションから該当生徒を削除
            // 配置されていない生徒一覧セッションに該当生徒を追加
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/registSeating.jsp");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        dispatcher.forward(request, response);
    }

}