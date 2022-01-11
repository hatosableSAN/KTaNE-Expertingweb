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
@WebServlet("/UpdateSeatingAll")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class updateSeatingAll extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまdoGet");
        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {

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
        System.out.println("いまPost");
        String tourl = "/WEB-INF/seating/updateSeatingfail.jsp";

        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            // 座席配置情報をセッションから取得
            SeatingArrangements setseatingArrangements = new SeatingArrangements();
            setseatingArrangements = (SeatingArrangements) session.getAttribute("SeatingArrangements");
            // SeatingServiceオブジェクトの生成
            SeatingService seatingService = new SeatingService();
            // 座席配置情報を更新・取得
            setseatingArrangements = seatingService.updateSeatingArrangements(setseatingArrangements);
            if (setseatingArrangements == null) {
                System.out.println("座席配置情報登録失敗");
                tourl = "/WEB-INF/seating/updateSeatingfail.jsp";
            } else {
                System.out.println("座席配置情報登録完了");

                // 「生徒座席一覧(studentSeatingArrList)」の情報を取得
                List<StudentSeatingArr> studentSeatingArrList = new ArrayList<StudentSeatingArr>();
                if ((List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList") != null) {
                    studentSeatingArrList = (List<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList");
                }
                // 座席配置IDをすべての座席に登録
                for (int i = 0; i < studentSeatingArrList.size(); i++) {
                    studentSeatingArrList.get(i).setSeatingArrangementId(setseatingArrangements.getId());
                }
                // 座席を登録
                seatingService.updateStudentSeatingArr(setseatingArrangements.getId(), studentSeatingArrList);
                if (false) {
                    System.out.println("座席（生徒）更新失敗");
                    tourl = "/WEB-INF/seating/updateSeatingfail.jsp";
                } else {
                    System.out.println("座席（生徒）更新完了");
                    tourl = "/WEB-INF/seating/updateSeatingcomplete.jsp";
                }
            }

            // // request,sessionで座席配置情報を送る
            // request.setAttribute("SeatingArrangements", seatingArrangements);
            // session.setAttribute("SeatingArrangements", seatingArrangements);

            RequestDispatcher dispatcher = request.getRequestDispatcher(tourl);
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }
}