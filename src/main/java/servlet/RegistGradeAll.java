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
import service.GradeService;
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
@WebServlet("/RegistGradeAll")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistGradeAll extends HttpServlet {

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
            // // 「生徒座席一覧(studentSeatingArrList)」に座らせた生徒と座席の情報を入れる
            // List<StudentSeatingArr> studentSeatingArrList = new
            // ArrayList<StudentSeatingArr>();
            // if ((List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList")
            // != null) {
            // studentSeatingArrList = (List<StudentSeatingArr>)
            // session.getAttribute("StudentSeatingArrList");
            // }

            // // SeatingServiceオブジェクトの生成
            // SeatingService seatingService = new SeatingService();
            // // 座席を登録
            // seatingService.registStudentSeatingArr(studentSeatingArrList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/registSeatingInfo.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("登録します");

        // // 入力された座席情報を取得
        // String seatname = request.getParameter("seatname");
        // String startdate = request.getParameter("startdate");
        // String enddate = request.getParameter("enddate");
        // HttpSession session = request.getSession();
        // User user = (User) session.getAttribute("User");
        // ClassDef classdef = (ClassDef) session.getAttribute("ClassDef");
        // Date createdDate = new Date();// 現在時刻＝作成日
        // String createddate = new SimpleDateFormat("yyyy-MM-dd").format(createdDate);
        // System.out.println(seatname + ":" + startdate + ":" + enddate + ":" +
        // classdef.getClass_id() + ":"
        // + user.getId() + ":" + createddate);

        // // registSeatingArrangementsオブジェクトに座席情報を入れる
        // SeatingArrangements seatingArrangements = new SeatingArrangements();
        // seatingArrangements.setClassId(classdef.getClass_id());
        // seatingArrangements.setName(seatname);
        // seatingArrangements.setStartDate(startdate);
        // seatingArrangements.setEndDate(enddate);
        // seatingArrangements.setCreatedDate(createddate);
        // // seatingArrangements.setUserId(user.getId());

        // System.out.println(seatingArrangements.getName() + ":" +
        // seatingArrangements.getStartDate() + ":"
        // + seatingArrangements.getEndDate() + seatingArrangements.getClassId() + ":"
        // + seatingArrangements.getUserId() + ":" +
        // seatingArrangements.getCreatedDate());

        HttpSession session = request.getSession();

        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./sessionerror.jsp");
            dispatcher.forward(request, response);
        } else {
            // まずは授業情報を取得
            Lessons Lessons = new Lessons();

            String ClassDate = (String) request.getParameter("ClassDate");
            String PeriodNum = (String) request.getParameter("PeriodNum");
            int period_num = Integer.parseInt(PeriodNum);
            String Comment = (String) request.getParameter("Comment");
            String SeatNum = (String) session.getAttribute("Seatnum");
            int idnumber = Integer.parseInt(SeatNum);
            Lessons.setSeating_arrangements_id(idnumber);
            Lessons.setLessonDate(ClassDate);
            Lessons.setPeriodnum(period_num);
            Lessons.setComment(Comment);

            GradeService service = new GradeService();
            service.registLessons(Lessons);
            // 授業を登録します
            // 登録した授業のIDを取得します

            int lessonid = service.getLessonId();

            // 次に評価を登録します
            List<Grade> GradeList = (List<Grade>) session.getAttribute("Grade");
            User User = (User) session.getAttribute("User");
            String UserId = User.getId();
            for (Grade Grade : GradeList) {
                Grade.setLessonId(lessonid);
                Grade.setUserId(UserId);

                service.registGrade(Grade);

                System.out.println(UserId + "さんの評価を登録しました");
            }

            System.out.println("全ての評価が登録されました");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/grade/registGradecomplete.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }
}
