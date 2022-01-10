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

import beans.SeatingArrangements;
import beans.*;
import service.ClassService;
import service.SeatingService;
import service.StudentService;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/SearchSeating")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class SearchSeating extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        // request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        // RequestDispatcher dispatcher =
        // request.getRequestDispatcher("/WEB-INF/registStudentSuccess.jsp");
        // dispatcher.forward(request, response);
        // System.out.println("いまdoGet");
        doPost(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            // requestオブジェクトの文字エンコーディングの設定
            request.setCharacterEncoding("UTF-8");

            // requestオブジェクトから登録情報の取り出し
            String seating_search = request.getParameter("seating_search");
            String button = request.getParameter("searchbutton");
            String index = request.getParameter("radiobutton");// 1クラス、2開始期間、終了期間
            User user = (User) session.getAttribute("User");

            SeatingService seatingService = new SeatingService();
            ClassService classService = new ClassService();
            StudentService service = new StudentService();

            List<SeatingArrangements> mySeatingArrangementsList = seatingService.getAllMySeatingArr(user.getId());
            List<SeatingArrangements> otherSeatingArrangementsList = seatingService.getAllOtherSeatingArr(user.getId());

            String tourl = null;
            if (seating_search.isEmpty() || button.equals("searchReset")) {
                // 本当は良くないけど、テキストボックスが空だったら一覧表示・一覧表示ボタンが押されていたら
                request.setAttribute("mySeatingArrangementsList", mySeatingArrangementsList);
                request.setAttribute("otherSeatingArrangementsList", otherSeatingArrangementsList);
                request.setAttribute("Word", null);
                request.setAttribute("index", "class");
                System.out.println("Please full all");
                tourl = "/WEB-INF/seating/manageSeatingTop.jsp";
            } else {
                List<SeatingArrangements> searchmyList = new ArrayList<SeatingArrangements>(); // 検索した自分の座席配置
                List<SeatingArrangements> searchotherList = new ArrayList<SeatingArrangements>();// 検索した相手の座席配置
                // 検索結果をとってくる
                mySeatingArrangementsList = seatingService.getsearchMySeatingArr(user.getId(), index, seating_search);
                otherSeatingArrangementsList = seatingService.getsearchOtherSeatingArr(user.getId(), index,
                        seating_search);

                request.setAttribute("mySeatingArrangementsList", mySeatingArrangementsList);
                request.setAttribute("otherSeatingArrangementsList", otherSeatingArrangementsList);
                request.setAttribute("Word", seating_search);
                request.setAttribute("index", index);
                tourl = "/WEB-INF/seating/manageSeatingTop.jsp";
            }
            // RequestDispatcher dispatcher = request.getRequestDispatcher(tourl);
            //
            request.getRequestDispatcher(tourl).forward(request, response);// 上のdoGetをまとめて書いている
        }

    }
}
