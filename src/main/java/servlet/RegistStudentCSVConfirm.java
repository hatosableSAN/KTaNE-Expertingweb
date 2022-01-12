package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

import beans.Student; //beansに入れた方がいいのかしら
import beans.User;
import service.StudentService;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistStudentCSVConfirm")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistStudentCSVConfirm extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        // request.setCharacterEncoding("UTF-8");
        // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        // RequestDispatcher dispatcher =
        // request.getRequestDispatcher("/WEB-INF/registStudentSuccess.jsp");
        // dispatcher.forward(request, response);
        System.out.println("いまdoGet");
        doPost(request, response);
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        System.out.println("いまHandのPost");
        HttpSession session = request.getSession(true); // Servletへのリクエストrequestからの
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            // session.setAttribute("名前", 式); // セッション変数への オブジェクト の格納
            // 型 変数 = (型)session.getAttribute("名前"); // セッション変数の オブジェクト の参照

            String tourl = "/WEB-INF/student/registStudentComplete.jsp";// 成功のURL

            // studentListオブジェクトに情報を格納
            List<Student> studentList_confirm = (List<Student>) session.getAttribute("studentList");
            User user = (User) session.getAttribute("User");
            String stu_user = user.getId(); // 今ログインしている教員ユーザ

            // StudentManagerオブジェクトの生成
            StudentService service = new StudentService();

            List<Student> failStudentList = new ArrayList<Student>();// 登録に失敗した生徒一覧
            List<Student> registStudentList = new ArrayList<Student>();// 登録に成功した生徒一覧
            for (int i = 0; i < studentList_confirm.size(); i++) {// それぞれの生徒がすでに登録されているか調べる
                boolean result = service.checkStudent(studentList_confirm.get(i).getStudent_id());
                System.out.println("checkstudent:" + studentList_confirm.get(i).getStudent_id() + ":" + result);
                if (result == true) { // そのidで登録できないor登録失敗
                    System.out.println("already used this stuid");
                    failStudentList.add(studentList_confirm.get(i));// 失敗した生徒一覧に追加
                    tourl = "/WEB-INF/student/registStudentCSVFail.jsp";
                    continue;
                } else {
                    // そのidで登録出来る
                    service.registStudent(studentList_confirm.get(i));
                    registStudentList.add(studentList_confirm.get(i));
                }
            }
            request.setAttribute("failStudentList", failStudentList);
            session.setAttribute("failStudentList", failStudentList);
            request.setAttribute("registStudentList", registStudentList);
            session.setAttribute("registStudentList", registStudentList);

            getServletContext().getRequestDispatcher(tourl).forward(request, response);// 上のdoGetをまとめて書いている
        }
    }
}