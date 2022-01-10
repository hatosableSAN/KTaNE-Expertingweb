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
import java.io.PrintWriter;

import beans.Student; //beansに入れた方がいいのかしら
import beans.User;
import service.StudentService;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/DeleteStudent")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class DeleteStudent extends HttpServlet {

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
        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        // System.out.println("いまHandのPost");
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./sessionerror.jsp");
            dispatcher.forward(request, response);
        } else {

            // requestオブジェクトから登録情報の取り出し
            // String stu_id = request.getParameter("stu_id");
            String stu_id = request.getParameter("delete_hidden");
            System.out.println(stu_id);
            String stu_name = null;
            int stu_gender = 0;
            String stu_user = null; // 今ログインしている教員ユーザ
            // String taikai_l = request.getParameter("taikai_l");
            // String taikai_k = request.getParameter("taikai_k");

            String tourl = null;
            // if(stu_update.isEmpty() || stu_name.isEmpty() || stu_gender.isEmpty()) {
            // tourl = "/student/registStudentHandError.jsp";
            // System.out.println("Please full all");
            // }else {

            Student student = new Student(stu_id, stu_name, stu_gender, stu_user);
            StudentService service = new StudentService();
            User user = (User) session.getAttribute("User");

            student = service.searchStudent(student);
            response.setContentType("text/html; charset=UTF-8");
            System.out.println("here delete");
            PrintWriter out = response.getWriter();
            out.println("<html><head></head>");
            // ここにstyleあった
            out.println("<style>");
            out.println(".green{");
            out.println("background-color:#C1F6CD;"); // ヘッダーの緑
            out.println("height:100px;");
            out.println("margin:auto;}");
            out.println(".btn{");// 削除ボタン
            out.println("background-color:#FFC700;");
            out.println("width:120px;");
            out.println("height:50px;");
            out.println("padding: 10px;");
            out.println("border-radius: 5px;");
            out.println("text-decoration: none;");
            out.println("filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));}");
            out.println(".button_grey{");
            out.println("background-color:#C4C4C4;");
            out.println("box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);");
            out.println("padding: 10px;");
            out.println("border-radius: 5px;");
            out.println("text-decoration: none;");
            out.println("width:120px;");
            out.println("height:50px;}");
            out.println("</style><body>");
            out.println("<div class='green'>");
            out.print("<p align='right'>ユーザーID　");
            out.print(user.getId());
            out.println("</p><h1 align='center'>児童・生徒情報削除</h1></div>");
            // out.println("<p align='right'>ID ${User.id}</p>");
            out.println("<h2 align='center'><font color='red'>以下の児童・生徒を削除しますか？</font></h2>");
            out.println("<form action='./DeleteStudentConfirm' method='post'>");
            // out.println("<div class='student'>");
            out.println("<table align='center'>");
            out.println(
                    "<tr><th>番号　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　</th><th>性別　　　　　</th><th>登録者　　　　　　　　　</th><th>　　　　　</th><th>　　　　　</th></tr>");
            // for(int i=0;i<list.size();i++){
            // System.out.println("in for");
            // Student studentinfo = list.get(i);
            // User user = session.getAttribute("User");
            out.print("<tr><td>");
            out.print(student.getStudent_id());
            out.print("<input type='hidden' name='stu_id' value=");
            out.print(student.getStudent_id());
            out.println("></td>");
            out.print("<td>");
            out.print(student.getStudent_name());
            out.println("</td>");
            out.print("<td>");
            if (student.getStudent_gender() == 1) { // genderをintにした
                out.print("男");
            } else if (student.getStudent_gender() == 2) {
                out.print("女");
            } else {
                out.print("その他");
            }
            // out.print(studentinfo.getStudent_gender());
            out.println("</td>");
            out.print("<td>");
            out.print(student.getStudent_user());
            out.print("</td>");
            out.println("</tr>");
            out.println("</table>");
            out.print(
                    "<input type='submit'/ name='student_delete' value='削除' class='btn' style='color:red;position:absolute; right:40px; bottom: 50px; font-size: 20px;'>");
            out.print("<input type='hidden'/ name='delete_hidden' value=");
            out.print(student.getStudent_id());
            out.print(">");
            out.println("</form>");
            out.println("<form action='./ManageStudent' method='post'>");
            out.println(
                    "<input type='submit' value='キャンセル' class='button_grey' style='position:absolute; left:40px; bottom: 50px; font-size: 20px;'></form>");
            out.println("</body>");
            // out.println("</div></form>");
            out.println("</body>");
            out.println("</html>");
            request.setAttribute("Student", student);

            // tourl = "/WEB-INF/student/deleteStudentCheck.jsp";
            // //パスは、webappにいるところから考えないといけない！
            // }

            // getServletContext().getRequestDispatcher(tourl).forward(request,response);//上のdoGetをまとめて書いている

        }
    }

}