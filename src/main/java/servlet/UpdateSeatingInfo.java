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

import beans.*;
import beans.Student;
import beans.User;
import service.StudentService;
import service.ClassService;
import service.SeatingService;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/UpdateSeatingInfo")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateSeatingInfo extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 座席配置変更確定ボタンを押す
        System.out.println("いまdoGet");

        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        // User User = (User) session.getAttribute("User");
        // session.setAttribute("User", user);

        // 「配置されている生徒一覧セッション」に座らせた生徒を入れる
        List<Student> setstudentList = new ArrayList<Student>();
        if ((List<Student>) session.getAttribute("setStudentList") != null) {
            setstudentList = (List<Student>) session.getAttribute("setStudentList");
        }
        session.setAttribute("setStudentList", setstudentList);

        List<Student> studentList = new ArrayList<Student>();
        if ((List<Student>) session.getAttribute("StudentList") != null) {
            studentList = (List<Student>) session.getAttribute("StudentList");
        }
        // 座席配置情報をセッションから取得
        SeatingArrangements setseatingArrangements = new SeatingArrangements();
        setseatingArrangements = (SeatingArrangements) session.getAttribute("SeatingArrangements");

        // 「生徒座席一覧(studentSeatingArrList)」の情報を取得
        List<StudentSeatingArr> studentSeatingArrList = new ArrayList<StudentSeatingArr>();
        if ((List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList") != null) {
            studentSeatingArrList = (List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/updateSeatingconfirm.jsp");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 座席配置変更確定ボタンを押す
        System.out.println("いまdoPost");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        // User User = (User) session.getAttribute("User");
        // session.setAttribute("User", user);

        // requestから更新された座席配置情報を取得
        String startdate = request.getParameter("startdate");// + " 00:00:00";
        System.out.println(startdate);
        String enddate = request.getParameter("enddate");// + " 00:00:00";
        System.out.println(enddate);
        String seatname = request.getParameter("seatname");

        String tourl = null;
        if (startdate.isEmpty()) {// 開始日時がnullの時（未入力)

            tourl = "/WEB-INF/seating/updateSeatingError.jsp";
            System.out.println("Please full all seatinarrangements information");
        } else {
            // 「配置されている生徒一覧セッション」に座らせた生徒を入れる
            List<Student> setstudentList = new ArrayList<Student>();
            if ((List<Student>) session.getAttribute("setStudentList") != null) {
                setstudentList = (List<Student>) session.getAttribute("setStudentList");
            }
            session.setAttribute("setStudentList", setstudentList);
            List<Student> studentList = new ArrayList<Student>();
            if ((List<Student>) session.getAttribute("StudentList") != null) {
                studentList = (List<Student>) session.getAttribute("StudentList");
            }

            // 座席配置情報に追加し、セッションに追加
            SeatingArrangements seatingArrangements = (SeatingArrangements) session.getAttribute("SeatingArrangements");
            seatingArrangements.setStartDate(startdate);
            seatingArrangements.setEndDate(enddate);
            seatingArrangements.setName(seatname);
            session.setAttribute("SeatingArrangements", seatingArrangements);
            System.out.println(seatingArrangements.getStartDate());
            System.out.println(seatingArrangements.getEndDate());

            // 「生徒座席一覧(studentSeatingArrList)」の情報を取得
            List<StudentSeatingArr> studentSeatingArrList = new ArrayList<StudentSeatingArr>();
            if ((List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList") != null) {
                studentSeatingArrList = (List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList");
            }
            tourl = "/WEB-INF/seating/updateSeatingconfirm.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(tourl);
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        dispatcher.forward(request, response);
    }

}