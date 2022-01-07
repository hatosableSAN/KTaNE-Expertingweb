package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

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

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/UpdateStudent")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateStudent extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        //request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        //RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/studentTop.jsp");
        //dispatcher.forward(request, response);
        //System.out.println("いまdoGet");
    	//doPost(request,response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession session = request.getSession(true);
        // requestオブジェクトの文字エンコーディングの設定
        HttpSession session = request.getSession(true);
        String stu_id = request.getParameter("update_hidden");
        String stu_name = null;
        int stu_gender = 0;
        String stu_user = null;
        Student student = new Student(stu_id, stu_name, stu_gender,stu_user);
        StudentService service = new StudentService();
        student = service.searchStudent(student); //どの児童か探す
        //response.setContentType("text/html; charset=UTF-8");//元はShift_JIS
        session.setAttribute("Student", student);
        getServletContext().getRequestDispatcher("/WEB-INF/student/updateStudent.jsp").forward(request,response);//上のdoGetをまとめて書いている
        //System.out.println("doGet now regist");
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/student/updateStudent.jsp");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        //dispatcher.forward(request, response);
        request.setCharacterEncoding("UTF-8");
        //System.out.println("いまHandのPost");
        //List<Student> list = new ArrayList<Student>();
        /*StudentService service = new StudentService();
        

        String tourl = null;
        	// studentオブジェクトに情報を格納
            //StudentMemo studentmemo = new StudentMemo(stu_id, stu_name, stu_gender,stu_user);
            HttpSession session = request.getSession(true);
            //session.setAttribute("Student", student);
            String stu_id = request.getParameter("update_hidden");
            String stu_name = null;
            int stu_gender = 0;
            String stu_user = null;
            Student student = new Student(stu_id, stu_name, stu_gender,stu_user);
            //StudentService service = new StudentService();
            student = service.searchStudent(student); //どの児童か探す
            response.setContentType("text/html; charset=UTF-8");//元はShift_JIS
            System.out.println("here update");
            User user = (User)session.getAttribute("User");
            PrintWriter out = response.getWriter();
            out.println("<html><head></head>");
            out.println("<style>");
            out.println(".green{");
            out.println("background-color:#C1F6CD;"); //ヘッダーの緑
            out.println("height:100px;");
            out.println("margin:auto;}");
            out.println(".btn{");//変更ボタン
            out.println("background-color:#FFC700;");
            out.println("width:120px;");
            out.println("height:50px;");
            out.println("padding: 10px;");
            out.println("border-radius: 5px;");
            out.println("text-decoration: none;");
            out.println("filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));}");
            out.println("div.center{");//中心に置くclass
            out.println("text-align: center;}");
            out.println(".backbtn{");//一覧に戻るボタン
            out.println("border-radius: 5px;");
            out.println("background: #FFF4CB;");
            out.println("padding: 10px;");
            out.println("position: absolute;");
            out.println("top:20px;");
            out.println("left:10px");
            out.println("text-decoration: none;");
            out.println("filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));");
            out.println("color: black;}");
            out.println("</style><body>");
            //out.println("<body>");
            out.println("<div class='green'>");
            out.print("<p align='right'>ユーザーID　");
            out.print(user.getId());
            out.println("</p>");
            out.println("<h1 align='center'>児童・生徒情報変更</h1></div>");
            out.println("<form action='./UpdateStudentCheck' method='post'>");
            out.println("<table align='center'>");
            out.println("<tr><th>番号　　　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　　　　　</th><th>性別　　　　　　　　</th><th>登録者　　　　　　　　　　　　　</th><th>　　　　　</th><th>　　　　　</th></tr>");
            out.print("<tr><td>");
            out.print(student.getStudent_id());
            out.print("<input type='hidden' name='stu_id' value=");
            out.print(student.getStudent_id());
            out.print("></td><td><input type='text' name='stu_name' maxlength='20' minlength='1' pattern='");
            out.print("[ぁ-んァ-ヶｦ-ﾟ一-龠a-zA-Z\-]+' value=");
            out.print(student.getStudent_name());
            out.print("></td><td>");
            switch(student.getStudent_gender()){
                case 1: out.println("<select name='stu_gender'>");
                        out.println("<option value='1' selected>男</option>");
                        out.println("<option value='2'>女</option>");
                        out.println("<option value='3'>その他</option>");
                        out.println("</select>");
                        break;
                case 2: out.println("<select name='stu_gender'>");
                        out.println("<option value='1'>男</option>");
                        out.println("<option value='2' selected>女</option>");
                        out.println("<option value='3'>その他</option>");
                        out.println("</select>");
                        break;
                case 3: out.println("<select name='stu_gender'>");
                        out.println("<option value='1'>男</option>");
                        out.println("<option value='2'>女</option>");
                        out.println("<option value='3' selected>その他</option>");
                        out.println("</select>");
                        break;
            }
            out.print("</td><td>");
            out.print(student.getStudent_user());
            out.print("</td><input type='hidden' name='stu_user' value=");
            out.print(student.getStudent_user());
            out.print("></td></table>");
            out.println("<div class='center'><input type='submit' name='student_update' value='変更' class='btn' style='font-size:20px;'></div>");
            //out.println("</div></form>");
            out.println("</form>");
            out.println("<form action='./ManageStudent' method='post'>");
            out.println("<input type='submit' value='児童生徒一覧へ戻る' class='backbtn'></form>");
            out.println("</body>");
            out.println("</html>");
            
            session.setAttribute("Student", student);

            // 登録
            //service.searchStudent(student);

            // 成功画面を表示する
            // System.out.println("OK牧場");
            //response.sendRedirect("/TableTennis/RegistInfo");
            //tourl = "/student/manageStudentTop.jsp"; //パスは、webappにいるところから考えないといけない！
        

        //getServletContext().getRequestDispatcher(tourl).forward(request,response);//上のdoGetをまとめて書いている
        */

    }
}