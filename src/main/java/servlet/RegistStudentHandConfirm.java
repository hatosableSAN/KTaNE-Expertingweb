package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import beans.Student; //beansに入れた方がいいのかしら
import service.StudentService;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistStudentHandConfirm")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistStudentHandConfirm extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        //request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registStudentSuccess.jsp");
        //dispatcher.forward(request, response);
        System.out.println("いまdoGet");
    	doPost(request,response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        //console.log("いまHandのpost");
        System.out.println("いまHandのPost");
        HttpSession session = request.getSession(true); // Servletへのリクエストrequestからの
        //session.setAttribute("名前", 式);               // セッション変数への オブジェクト の格納
        //型 変数 = (型)session.getAttribute("名前");     // セッション変数の オブジェクト の参照

        // requestオブジェクトから登録情報の取り出し
        /*String stu_id = request.getParameter("stu_id");
        String stu_name = request.getParameter("stu_name");
        //int stu_gender = request.getParameter("stu_gender");
        String gender = request.getParameter("stu_gender");
        int stu_gender = Integer.parseInt(gender);
        String stu_user = "ABC"; //今ログインしている教員ユーザ*/
        //String taikai_l = request.getParameter("taikai_l");
        //String taikai_k = request.getParameter("taikai_k");
        //session.setAttribute("stu_id",stu_id);
        //session.setAttribute("stu_id",stu_name);
        //session.setAttribute("stu_id",stu_gender);
        //session.setAttribute("stu_id",stu_user);

        // コンソールに確認するために出力
        //System.out.println("取得した文字列は" + stu_id + "です！");
        //System.out.println("取得した文字列は" + stu_name + "です！");
        //System.out.println("取得した文字列は" + stu_gender + "です！");
        //System.out.println("取得した文字列は" + taikai_l + "です！");
        //System.out.println("取得した文字列は" + taikai_k + "です！");

        String tourl = null;
        
        	//studentオブジェクトに情報を格納
            //Student student = new Student(stu_id, stu_name, stu_gender,stu_user);
            Student student_confirm = (Student)session.getAttribute("Student");
            String stu_id = student_confirm.getStudent_id();
            String stu_name = student_confirm.getStudent_name();
            //int stu_gender = request.getParameter("stu_gender");
            String stu_gender = student_confirm.getStudent_gender();
            String stu_user = "櫨山";

            //StudentManagerオブジェクトの生成
            StudentService service = new StudentService();
            //HttpSession session = request.getSession(true);
            //session.setAttribute("Student", student);
            //Student student_confirm = (Student)session.getAttribute("Student");
            switch(stu_gender){
                case "男": stu_gender="1";
                          break;
                case "女": stu_gender="2";
                          break;
                case "その他": stu_gender="3";
                          break;
            }
            Student student = new Student(stu_id, stu_name, stu_gender,stu_user);

            // 登録
            service.registStudent(student);

            // 成功画面を表示する
            // System.out.println("OK牧場");
            //response.sendRedirect("/TableTennis/RegistInfo");
            tourl = "/student/registStudentComplete.jsp"; //パスは、webappにいるところから考えないといけない！
        

        getServletContext().getRequestDispatcher(tourl).forward(request,response);//上のdoGetをまとめて書いている

        // studentオブジェクトに情報を格納
        //Student student = new Student(player_n, taikai_n, taikai_l, taikai_k);

        // StudentManagerオブジェクトの生成
        //StudentManager manager = new StudentManager();

        // 登録
        //manager.registStudent(student);

        // 成功画面を表示する
        // System.out.println("OK牧場");
        //response.sendRedirect("/TableTennis/RegistInfo");
    }
}
