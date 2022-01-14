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
@WebServlet("/SearchStudent")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class SearchStudent extends HttpServlet {

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
            List<Student> list = new ArrayList<Student>();
            List<Student> stu_classlist = new ArrayList<Student>();
            User User = (User)session.getAttribute("User");
            String userId=User.getId();
            String type = request.getParameter("type");
            StudentService service = new StudentService();
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
            if(type.equals("stu_search_all")){//一覧表示

                list = service.getStudentForGrade(User.getId());
               System.out.println("list size is "+list.size());
               if (stu_classlist.size() == 0) {
                   // 検索に当てはまる児童がいなかった
                   //tourl = "/WEB-INF/classes/registClassNone.jsp";// 検索結果がありません画面に飛ぶ
                   System.out.println("in if");
               }
            

               session.setAttribute("Stu_list", list);
               tourl = "/WEB-INF/grade/selectStudent.jsp"; // パスは、webappにいるところから考えないといけない！
            }else  if (stu_info.isEmpty()) { // テキストボックスが空だったら一覧表示
                if(type.equals("regist")){
                    list = service.getStudent();
                    stu_classlist = service.getStudent();
                    request.setAttribute("List", list);
                    request.setAttribute("List_all" ,stu_classlist);
                    System.out.println("Please full all regist");
                    tourl = "/WEB-INF/classes/registClass.jsp";
                }else if(type.equals("update")){
                    String classid = request.getParameter("ClassId");
                    int class_id = Integer.parseInt(classid);//null
                    String class_name = request.getParameter("class_name");
                    String classyear = request.getParameter("class_year");
                    int class_year = 0;// Integer.parseInt(classyear);
                    String class_user = request.getParameter("class_user");
                    ClassDef classdef = new ClassDef(class_id, class_name, class_year, class_user);
                    classdef = class_service.searchClass(classdef);// クラス情報を取得
                    stu_classlist = class_service.getAllClassmember(classdef);// クラスに所属している児童を取得 //null
                    list = service.getStudent();
                    session.setAttribute("Stu_list", list);//システム内の児童全員
                    session.setAttribute("Stu_classlist", stu_classlist);//システム内の児童全員
                    System.out.println("Please full all update");
                    tourl = "/WEB-INF/classes/updateClass.jsp";
                }else if(type.equals("stu_search_grade")){
                    list = service.getStudentForGrade(userId);
                   System.out.println("stu_classlist size is " + stu_classlist.size());
                   System.out.println("list size is "+list.size());
                   if (stu_classlist.size() == 0) {
                       // 検索に当てはまる児童がいなかった
                       //tourl = "/WEB-INF/classes/registClassNone.jsp";// 検索結果がありません画面に飛ぶ
                       System.out.println("in if");
                   }
    
                   session.setAttribute("Stu_list", list);
                   tourl = "/WEB-INF/grade/selectStudent.jsp"; // パスは、webappにいるところから考えないといけない！
                }
            } else { //検索開始
             if(type.equals("regist")){
                list = service.getStudent(stu_info, select);// 引数を付けます。stu_infoとselect
                System.out.println("list size is " + list.size());
                if (list.size() == 0) {
                    // 検索に当てはまる児童がいなかった
                    //tourl = "/WEB-INF/classes/registClassNone.jsp";// 検索結果がありません画面に飛ぶ
                    System.out.println("in if");
                }
                stu_classlist = service.getStudent();

                request.setAttribute("List", list);
                request.setAttribute("List_all",stu_classlist);
                tourl = "/WEB-INF/classes/registClass.jsp"; // パスは、webappにいるところから考えないといけない！
             }else if(type.equals("update")){
                 stu_classlist = service.getStudent(stu_info, select);// 引数を付けます。stu_infoとselect
                 list = service.getStudent();
                System.out.println("stu_classlist size is " + stu_classlist.size());
                System.out.println("list size is "+list.size());
                if (stu_classlist.size() == 0) {
                    // 検索に当てはまる児童がいなかった
                    //tourl = "/WEB-INF/classes/registClassNone.jsp";// 検索結果がありません画面に飛ぶ
                    System.out.println("in if");
                }

                session.setAttribute("Stu_classlist", stu_classlist);
                session.setAttribute("Stu_list", list);
                tourl = "/WEB-INF/classes/updateClassSearch.jsp"; // パスは、webappにいるところから考えないといけない！
             }else if(type.equals("stu_search_grade")){

                stu_classlist = service.getStudent(stu_info, select);// 引数を付けます。stu_infoとselect
                list = service.getStudentForGrade(userId);
               System.out.println("stu_classlist size is " + stu_classlist.size());
               System.out.println("list size is "+list.size());
               if (stu_classlist.size() == 0) {
                   // 検索に当てはまる児童がいなかった
                   //tourl = "/WEB-INF/classes/registClassNone.jsp";// 検索結果がありません画面に飛ぶ
                   System.out.println("in if");
                   
               }
               tourl = "/WEB-INF/grade/selectStudent.jsp";   
               session.setAttribute("Stu_list", stu_classlist);
            }
        }
            getServletContext().getRequestDispatcher(tourl).forward(request, response);
        
        

    }
}
}
