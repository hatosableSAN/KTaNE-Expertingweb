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
@WebServlet("/DeleteSeatingconfirm")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class DeleteSeatingconfirm extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 削除ボタンを押し、確認画面へ飛ぶ
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまdoGet");

        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            User user = (User) session.getAttribute("User");

            // requestオブジェクトの文字エンコーディングの設定
            request.setCharacterEncoding("UTF-8");
            // Classserviceobjectの作成
            SeatingService SeatingService = new SeatingService();
            List<SeatingArrangements> mySeatingArrangementsList = SeatingService.getAllMySeatingArr(user.getId());
            List<SeatingArrangements> otherSeatingArrangementsList = SeatingService.getAllOtherSeatingArr(user.getId());
            request.setAttribute("mySeatingArrangementsList", mySeatingArrangementsList);
            request.setAttribute("otherSeatingArrangementsList", otherSeatingArrangementsList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/deleteSeating.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 削除ボタンを押し、確認画面へ飛ぶPOST
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまdoPost");

        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            User user = (User) session.getAttribute("User");

            // 送信された座席配置Idを取得
            int seatingId = Integer.parseInt(request.getParameter("SeatingId"));
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setId(seatingId);

            // SServiceオブジェクトの生成(座席配置情報、クラス、生徒の取得)
            SeatingService seatingService = new SeatingService();
            ClassService ClassService = new ClassService();
            StudentService StudentService = new StudentService();

            // 座席配置情報・クラス情報を取得
            seatingArrangements = seatingService.getSeatingArrangements(seatingArrangements);
            ClassDef ClassDef = new ClassDef();
            ClassDef.setClass_id(seatingArrangements.getClassId());
            ClassDef = ClassService.searchClass(ClassDef);
            List<Student> StudentList = ClassService.getAllClassmember(ClassDef);
            List<StudentSeatingArr> StudentSeatingArrList = seatingService
                    .getStudentSeatingArrList(seatingArrangements);

            request.setAttribute("SeatingArrangements", seatingArrangements);
            request.setAttribute("StudentList", StudentList);
            request.setAttribute("ClassDef", ClassDef);
            request.setAttribute("StudentSeatingArrList", StudentSeatingArrList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/deleteSeating.jsp");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            dispatcher.forward(request, response);
        }
    }

}