package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

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
import service.StudentService;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/UpdateStudentConfirm")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class UpdateStudentConfirm extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        //request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registStudentSuccess.jsp");
        //dispatcher.forward(request, response);
        //System.out.println("いまdoGet");
    	doPost(request,response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            HttpSession session = request.getSession(true);
           // requestオブジェクトの文字エンコーディングの設定
          request.setCharacterEncoding("UTF-8");
          //System.out.println("いまHandのPost");

          // requestオブジェクトから登録情報の取り出し
          //String stu_id = request.getParameter("stu_id");
          Student student_confirm = (Student)session.getAttribute("Student");//どこのセッション？
          String stu_id = student_confirm.getStudent_id();//request.getParameter("stu_id");//これを使う！
          String stu_name = student_confirm.getStudent_name(); //request.getParameter("stu_name");
          //String gender = request.getParameter("stu_gender");
          int stu_gender = student_confirm.getStudent_gender(); //Integer.parseInt(gender);
          String stu_user = student_confirm.getStudent_user(); //request.getParameter("stu_user");
          Student student = new Student(stu_id, stu_name, stu_gender,stu_user);
          StudentService service = new StudentService();

          boolean result = service.updateStudent(student);//ここでbooleanかつ削除もする
            String tourl = null;

            System.out.println("after boolean update and "+result);
            if(result == false){
                System.out.println("couldnot delete");
                tourl = "/WEB-INF/student/updateStudentFail.jsp";
                //RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/deleteStudentFail.jsp");
                //dispatcher.forward(request, response);
            }
          //service.updateStudent(student);          

             tourl = "/WEB-INF/student/updateStudentComplete.jsp"; //パスは、webappにいるところから考えないといけない！

         getServletContext().getRequestDispatcher(tourl).forward(request,response);//上のdoGetをまとめて書いている
    }
        
}