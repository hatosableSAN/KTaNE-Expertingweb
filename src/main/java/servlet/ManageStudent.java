package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@WebServlet("/ManageStudent")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class ManageStudent extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        }else{
            request.setCharacterEncoding("UTF-8");
            // System.out.println("いまHandのPost");
            List<Student> list = new ArrayList<Student>();
            StudentService service = new StudentService();
            list = service.getStudent(); // 児童全員持ってくる
            session.setAttribute("List", list);
            String tourl = "/WEB-INF/student/manageStudent.jsp"; //パスは、webappにいるところから考えないといけない！
            getServletContext().getRequestDispatcher(tourl).forward(request,response);//上のdoGetをまとめて書いている
        }
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
            List<Student> list = new ArrayList<Student>();
            StudentService service = new StudentService();

            String tourl = null;
            // studentオブジェクトに情報を格納
            // StudentMemo studentmemo = new StudentMemo(stu_id, stu_name,
            // stu_gender,stu_user);
            // HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute("User");
            String user_id = user.getId();
            String stu_id = null;
            String stu_name = null;
            int stu_gender = 0;
            String stu_user = null;
            Student student = new Student(stu_id, stu_name, stu_gender, stu_user);// 必要？
            list = service.getStudent(); // 児童全員持ってくる
            session.setAttribute("List", list);
            /*
            response.setContentType("text/html; charset=UTF-8");
            System.out.println("here");
            PrintWriter out = response.getWriter();
            out.print(
                    "<html><head><link rel='stylesheet' href='${pagecontext.request.contextpath}/se21g1/style.css' type='text/css'></head>");
            // out.print(pagecontext.request.contextpath);
            // out.print("/se21g1/style.css type='text/css'></head>");
            // cssファイル見つからないと言われてしまう
            out.println("<style>");
            out.println(".student{");
            out.println("width:1000px;");
            out.println("height:350px;");
            out.println("border:1px solid #000;");
            out.println("overflow-y:scroll;");
            out.println("text-align:center;");
            out.println("margin:0 auto; }");
            out.println("#bar{");
            out.println("background-color:#F8AB74;}"); // 表の上のオレンジ部分
            out.println(".green{");
            out.println("background-color:#C1F6CD;}"); // ヘッダーの緑
            out.println("height:100px;");
            out.println("margin:auto;}");
            out.println(".button{");
            // out.println("box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);");
            out.println("padding: 10px;");
            out.println("border-radius: 0px;");
            out.println("text-decoration: none;}");
            out.println("#blue{");
            out.println("background: #B6D8FF;}");
            out.println(".backbtn{");// 一覧に戻るボタン
            out.println("border-radius: 5px;");
            out.println("background: #FFF4CB;");
            out.println("padding: 10px;");
            out.println("position: absolute;");
            out.println("top:20px;");
            out.println("left:10px");
            out.println("text-decoration: none;");
            out.println("color: black;");
            out.println("filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));}");
            out.println("#grey{");
            out.println("background: #C4C4C4;");
            out.println("color:red;");
            out.println("}</style><body>");
            out.println("<div class='green'>");
            out.print("<p align='right'>ユーザーID　");
            out.print(user_id);
            out.print("</p>");
            // out.println("<p align='right'>ID ${User.id}</p>");
            out.println("<h1 align='center'>児童・生徒一覧</h1>");
            out.println("</div>");
            // out.println("<form action='./ManageStudentDetail' method='post'>");
            out.println("<div class='student'>");
            out.println("<table align='center'>");
            out.println(
                    "<tr id='bar'><th>番号　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　</th><th>性別　　　　　　　　　</th><th>登録者　　　　　　　　　</th><th>　　　　　</th><th>　　　　　</th></tr>");
            if (list.size() == 0) {
                out.println("</table>");
                out.println("<h4 align='center'>児童が登録されていません</h4>");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    // System.out.println("in for");
                    Student studentinfo = list.get(i);
                    // User user = session.getAttribute("User");
                    out.print("<tr><td>");
                    out.print(studentinfo.getStudent_id());
                    out.println("</td>");
                    out.print("<td>");
                    out.print(studentinfo.getStudent_name());
                    out.println("</td>");
                    out.print("<td>");
                    if (studentinfo.getStudent_gender() == 1) { // genderをintにした
                        out.print("男");
                    } else if (studentinfo.getStudent_gender() == 2) {
                        out.print("女");
                    } else {
                        out.print("その他");
                    }
                    out.println("</td>");
                    out.print("<td>");
                    out.print(studentinfo.getStudent_user());
                    out.print("</td>");
                    if (studentinfo.getStudent_user().equals(user_id)) {
                        // System.out.println("in if");
                        out.println("<form action='./UpdateStudent' method='post'>");
                        out.print("<td>");
                        out.print("<input type='submit' name='student_update' value='変更' class='button' id='blue'>");
                        out.print("<input type='hidden' name='update_hidden' value=");
                        out.print(studentinfo.getStudent_id());
                        out.print(">");
                        out.println("</form>");
                        out.print("</td>");
                        out.println("<form action='./DeleteStudent' method='post'>");
                        out.print("<td>");
                        out.print("<input type='submit'/ name='student_delete' value='削除' class='button' id='grey'>");
                        out.print("<input type='hidden'/ name='delete_hidden' value=");
                        out.print(studentinfo.getStudent_id());
                        out.print(">");
                        out.println("</form>");
                        out.print("</td>");
                    } else {
                        out.print("<td>　　</td>");
                        out.print("<td>　　</td>");
                    }
                    out.println("</tr>");
                }
                out.println("</table>");
            }
            // out.println("</table>");
            out.println("</div></form><br>");
            out.println("<form action='./GoStudentTop' method='get'>");
            out.println("<input type='submit' value='児童・生徒メニュートップへ戻る' class='backbtn'></form>");
            // out.println()</form>"
            out.println("</body>");
            out.println("</html>");
            */

            //session.setAttribute("Student", student);

            // 登録
            // service.searchStudent(student);

            // 成功画面を表示する
            // System.out.println("OK牧場");
            // response.sendRedirect("/TableTennis/RegistInfo");
            tourl = "/WEB-INF/student/manageStudent.jsp"; //パスは、webappにいるところから考えないといけない！

            getServletContext().getRequestDispatcher(tourl).forward(request,response);//上のdoGetをまとめて書いている

        }
    }
}