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

import beans.*;
import service.*;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/SearchLessons")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class SearchLessons extends HttpServlet {

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
            // System.out.println("いまHandのPost");

            // requestオブジェクトから登録情報の取り出し
            //String stu_search = request.getParameter("stu_search");
            int id=(int) session.getAttribute("searchstuid");
            List<Lessons> list = new ArrayList<Lessons>();
            String type = request.getParameter("type");
            GradeService service = new GradeService();
            ClassService class_service = new ClassService();
            String stu_info = request.getParameter("stu_search"); // textboxの値
            String select = request.getParameter("radiobutton"); // ラジオボタンどちらが押されたか
            System.out.println("select "+select);
            System.out.println("type "+type);
            System.out.println("stu_info "+stu_info);
            // int stu_gender = Integer.parseInt(gender);
            // String stu_user = "ABC"; //今ログインしている教員ユーザ
            // String taikai_l = request.getParameter("taikai_l");
            // String taikai_k = request.getParameter("taikai_k");

            String tourl = null;
            if (stu_info.isEmpty()) { // テキストボックスが空だったら一覧表示
                
                    list = service.getLessonList();
                   System.out.println("list size is "+list.size());
                   if (list.size() == 0) {
                       // 検索に当てはまる児童がいなかった
                       //tourl = "/WEB-INF/classes/registClassNone.jsp";// 検索結果がありません画面に飛ぶ
                       System.out.println("in if");
                   }
    
                   session.setAttribute("Stu_list", list);
                   tourl = "/WEB-INF/grade/showStudentGradesList.jsp"; // パスは、webappにいるところから考えないといけない
            } else { //検索開始
                if(select.equals("date")){
                list = service.searchLessonWithDate(stu_info);// 引数を付けます。stu_infoとselect

               System.out.println("list size is "+list.size());
               if (list.size() == 0) {
                   // 検索に当てはまる児童がいなかった
                   //tourl = "/WEB-INF/classes/registClassNone.jsp";// 検索結果がありません画面に飛ぶ
                   System.out.println("in if");
               }
            }

            session.setAttribute("LessonList",list); 
               tourl = "/WEB-INF/grade/selectLessons.jsp"; // パスは、webappにいるところから考えないといけない！
            }
                

            
        
            getServletContext().getRequestDispatcher(tourl).forward(request, response);
        }
        

    }
}