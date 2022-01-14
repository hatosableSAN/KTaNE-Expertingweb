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

import beans.ClassDef;
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
import java.text.ParseException;

//アノテーションの記述
@WebServlet("/RegistSeatingInfo")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistSeatingInfo extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 座席を確定し、期間を入力するように促す
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまdoGet");
        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {

            // 「生徒座席一覧(studentSeatingArrList)」に座らせた生徒と座席の情報を入れる //いらないかも
            List<StudentSeatingArr> studentSeatingArrList = new ArrayList<StudentSeatingArr>();
            if ((List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList") != null) {
                studentSeatingArrList = (List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList");
            }
            // 座席配置情報をセッションから取得
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            // クラス情報をセッションから取得
            ClassDef ClassDef = (ClassDef) session.getAttribute("ClassDef");
            if ((SeatingArrangements) session.getAttribute("SeatingArrangements") != null) {
                seatingArrangements = (SeatingArrangements) session.getAttribute("SeatingArrangements");
            } else {
                // セッションに入っていない=デフォルト表記を入れる
                seatingArrangements.setStartDate(ClassDef.getClass_year() + "-04-01");
                seatingArrangements.setEndDate("");
                seatingArrangements.setName("");
            }
            session.setAttribute("SeatingArrangements", seatingArrangements);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/registSeatingInfo.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 座席配置情報の確定を押した後
            // requestオブジェクトの文字エンコーディングの設定
            request.setCharacterEncoding("UTF-8");
            System.out.println("いまPost");

            // 入力された座席情報を取得
            String seatname = request.getParameter("seatname");
            String startdate = request.getParameter("startdate");
            String enddate = request.getParameter("enddate");
            HttpSession session = request.getSession();
            if (LoginChecker.notLogin(session)) {
                System.out.println("セッション情報がありません");
                RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
                dispatcher.forward(request, response);
            } else {
                User user = (User) session.getAttribute("User");

                String tourl = null;
                if (startdate.isEmpty()) {// 開始日時がnullの時（未入力)//TODO開始期間が正しく入力されていないとき
                    tourl = "/WEB-INF/seating/registSeatingError.jsp";
                    System.out.println("Please full all seatinarrangements information");
                } else {// 開始日時が正しく入力されているとき
                    if (!enddate.isEmpty()) {// 終了日時がnullでない時
                        // 開始期間と終了期間を比較する
                        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                        Date Startdate = sdformat.parse(startdate);
                        Date Enddate = sdformat.parse(enddate);
                        System.out.println("Startdate: " + sdformat.format(Startdate));
                        System.out.println("Enddate: " + sdformat.format(Enddate));
                        if (!Enddate.after(Startdate)) {// 終了期間が開始期間より前で登録されている時
                            System.out.println("Enddate is not after Startdate");
                            tourl = "/WEB-INF/seating/registSeatingErrordate.jsp";// ない
                            System.out.println("終了期間は開始期間より後に入力してください");
                        } else {// 終了期間が開始期間より後で正しく登録されている時
                            tourl = "/WEB-INF/seating/registSeatingconfirm.jsp";
                        }
                    } else {
                        tourl = "/WEB-INF/seating/registSeatingconfirm.jsp";
                    }
                    ClassDef classdef = (ClassDef) session.getAttribute("ClassDef");
                    Date createdDate = new Date();// 現在時刻＝作成日
                    String createddate = new SimpleDateFormat("yyyy-MM-dd").format(createdDate);
                    System.out
                            .println(seatname + ":" + startdate + ":" + enddate + ":" + classdef.getClass_id() + ":"
                                    + user.getId() + ":" + createddate);

                    // registSeatingArrangementsオブジェクトに座席情報を入れる
                    SeatingArrangements seatingArrangements = new SeatingArrangements();
                    seatingArrangements.setClassId(classdef.getClass_id());
                    if (seatname.equals(null)) {
                        seatname = "";
                    }
                    seatingArrangements.setName(seatname);
                    seatingArrangements.setStartDate(startdate);
                    seatingArrangements.setEndDate(enddate);
                    seatingArrangements.setCreatedDate(createddate);
                    seatingArrangements.setUserId(user.getId());

                    System.out
                            .println(seatingArrangements.getName() + ":" + seatingArrangements.getStartDate() + ":"
                                    + seatingArrangements.getEndDate() + seatingArrangements.getClassId() + ":"
                                    + seatingArrangements.getUserId() + ":" + seatingArrangements.getCreatedDate());

                    request.setAttribute("SeatingArrangements", seatingArrangements);// いる...けど、registSeatingconfirm.jsp次第
                    session.setAttribute("SeatingArrangements", seatingArrangements);// いる
                    // }
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher(tourl);
                // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
                dispatcher.forward(request, response);
            }
        } catch (ParseException e) {
            System.out.println("期間は正しい形式(y-m-d)で入力してください");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/registSeatingInfo.jsp");
            dispatcher.forward(request, response);
            e.printStackTrace();
        }
    }

}