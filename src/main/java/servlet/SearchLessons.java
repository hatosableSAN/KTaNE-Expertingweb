package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        HttpSession session = request.getSession();
        User User = (User)session.getAttribute("User"); 
        String UserId=User.getId(); 

        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            // requestオブジェクトの文字エンコーディングの設定
            request.setCharacterEncoding("UTF-8");
            // System.out.println("いまHandのPost");

            // requestオブジェクトから登録情報の取り出し
            //String stu_search = request.getParameter("stu_search");;
            List<Lessons> list = new ArrayList<Lessons>();
            String type = request.getParameter("type");//授業日or授業コメント
            GradeService service = new GradeService();
            String searchword = request.getParameter("searchword"); // textboxの値
            String button = request.getParameter("button"); // textboxの値
            System.out.println("type "+type);
            System.out.println("word "+searchword);
            // int stu_gender = Integer.parseInt(gender);
            // String stu_user = "ABC"; //今ログインしている教員ユーザ
            // String taikai_l = request.getParameter("taikai_l");
            // String taikai_k = request.getParameter("taikai_k");

//日付かどうかチェック
Boolean wordisDate=false;
                if (searchword == null || searchword.length() != 10) {
                    wordisDate=false;  
                }else{
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                format.setLenient(false);
             
                    try {
                        format.parse(searchword);
                        wordisDate=true;
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        wordisDate=false;
                    }
                }
                    
                










            if(button.equals("all")){//一覧表示
                list = service.getLessonList(UserId);// 引数を付けます。stu_infoとselect
                System.out.println("一覧表示");
                System.out.println("list size is "+list.size());
                if (list.size() == 0) {
                    // 検索に当てはまる児童がいなかった
                    //tourl = "/WEB-INF/classes/registClassNone.jsp";// 検索結果がありません画面に飛ぶ
                    System.out.println("in if");
            }
        }else if (searchword.isEmpty()) { // テキストボックスが空だったら一覧表示
            System.out.println("空のテキスト");
                    list = service.getLessonList(UserId);
                   System.out.println("list size is "+list.size());
                   if (list.size() == 0) {
                       // 検索に当てはまる児童がいなかった
                       //tourl = "/WEB-INF/classes/registClassNone.jsp";// 検索結果がありません画面に飛ぶ
                       System.out.println("in if");
                   }
    
                   session.setAttribute("LessonList", list);
                  
            } else if(wordisDate){ //検索開始
                System.out.println("日付検索");
                if(type.equals("date")){//日付検索
                list = service.searchLessonWithDate(searchword);

               System.out.println("list size is "+list.size());
               if (list.size() == 0) {
                   // 検索に当てはまる児童がいなかった
                   //tourl = "/WEB-INF/classes/registClassNone.jsp";// 検索結果がありません画面に飛ぶ
                   System.out.println("in if");
               }
            }
                                      }else{//コメント検索
                                        System.out.println("コメント");
                list = service.searchLessonWithComment(searchword);// 引数を付けます。stu_infoとselect

                System.out.println("list size is "+list.size());
                if (list.size() == 0) {
                    // 検索に当てはまる児童がいなかった
                    //tourl = "/WEB-INF/classes/registClassNone.jsp";// 検索結果がありません画面に飛ぶ
                    System.out.println("in if");   
                                      }

           
              
            }
               
        

            
        session.setAttribute("LessonList",list); 
            getServletContext().getRequestDispatcher("/WEB-INF/grade/selectLessons.jsp").forward(request, response);
        
        

    }
}
}
