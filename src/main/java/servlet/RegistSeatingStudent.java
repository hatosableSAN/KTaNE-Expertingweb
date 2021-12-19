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

import service.StudentService;
import service.ClassService;
import service.GradeService;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import utility.*;
//beansに入れた方がいいのかしら
import beans.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistSeatingStudent")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistSeatingStudent extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO :確定画面から戻ってきた画面（GET）
        System.out.println("いまdoGet");

        HttpSession session = request.getSession();
        // User User = (User) session.getAttribute("User");
        // session.setAttribute("User", user);

        // 「配置されている生徒一覧セッション」に座らせた生徒を入れる
        List<Student> setstudentList = new ArrayList<Student>();
        if ((List<Student>) session.getAttribute("setStudentList") != null) {
            setstudentList = (List<Student>) session.getAttribute("setStudentList");
        }
        session.setAttribute("setStudentList", setstudentList);

        List<Student> studentList = new ArrayList<Student>();
        if ((List<Student>) session.getAttribute("StudentList") != null) {
            studentList = (List<Student>) session.getAttribute("StudentList");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/seating/registSeating.jsp");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        dispatcher.forward(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        request.setCharacterEncoding("UTF-8");
        System.out.println("セッションに個人評価を追加するよ～");
        HttpSession session = request.getSession();

        List<StudentSeatingArr> StudentList=(List<StudentSeatingArr>)session.getAttribute("StudentSeatingArrList");//生徒全員のリスト所得
        String x=request.getParameter("seatnum");
        //System.out.println(x);
        
        StudentSeatingArr SelectedSeatingArr=StudentList.get(Integer.parseInt(x));//配置済みの座席順に合致する座席を取得
        String GradeStudentId=SelectedSeatingArr.getStudentId();
        int gradeseatnum=SelectedSeatingArr.getSeat();

        String attendance=(String) request.getParameter("attendance");
        Boolean attendance_b=false;
        if(attendance.equals("true")){
            attendance_b=true;
        }
        int red=Integer.parseInt(request.getParameter("red"));
        int blue= Integer.parseInt(request.getParameter("blue"));
        int green= Integer.parseInt(request.getParameter("green"));
        String comment=(String) request.getParameter("comment");
        User User=(User) session.getAttribute("User");
        String Name=User.getId();

        System.out.println("ゲットしたのは");
        System.out.println(red);
        System.out.println(blue);
        System.out.println(green);
        System.out.println(comment);
        System.out.println(attendance);
        System.out.println(GradeStudentId);
        System.out.println(Name);
        System.out.println(gradeseatnum);

       List<Grade> GradeList=(List<Grade>) session.getAttribute("Grade");
        Grade Grade=new Grade();

        Grade.setRed(red);
        Grade.setBlue(blue);
        Grade.setGreen(green);
        Grade.setComment(comment);
        Grade.setAttendance(attendance_b);
        Grade.setComment(comment);
        Grade.setStudentId(Name);
        Grade.setSeat(gradeseatnum);

        GradeList.add(Grade);
        System.out.println(gradeseatnum+"の評価を作成してセッションにいれました");
        session.setAttribute("Grade", GradeList);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/grade/registGrade.jsp");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        dispatcher.forward(request, response);
        
    }
}