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

import beans.Student; //beansに入れた方がいいのかしら
import beans.User;
import service.StudentService;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/RegistStudentHand")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class RegistStudentHand extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./sessionerror.jsp");
            dispatcher.forward(request, response);
        } else {
            // requestオブジェクトの文字エンコーディングの設定
            // request.setCharacterEncoding("UTF-8");
            // forwardはrequestオブジェクトを引数として、次のページに渡すことができる
            // RequestDispatcher dispatcher =
            // request.getRequestDispatcher("/WEB-INF/registStudentSuccess.jsp");
            // dispatcher.forward(request, response);
            getServletContext().getRequestDispatcher("/WEB-INF/student/registStudentHand.jsp").forward(request,
                    response);// 上のdoGetをまとめて書いている
            System.out.println("doGet now hand");
            // doPost(request,response);
        }
    }

    // requestオブジェクトには、フォームで入力された文字列などが格納されている。
    // responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./sessionerror.jsp");
            dispatcher.forward(request, response);
        } else {
            // requestオブジェクトの文字エンコーディングの設定
            request.setCharacterEncoding("UTF-8");
            System.out.println("いまHandのPost");

            // requestオブジェクトから登録情報の取り出し
            String stu_id = request.getParameter("stu_id");
            String stu_name = request.getParameter("stu_name");
            String stu_gender = request.getParameter("stu_gender");// これは文字列
            // int stu_gender = Integer.parseInt(gender);
            User user = (User) session.getAttribute("User");
            String stu_user = user.getId(); // 今ログインしている教員ユーザ

            // コンソールに確認するために出力
            System.out.println("取得した文字列は" + stu_id + "です！");
            System.out.println("取得した文字列は" + stu_name + "です！");
            System.out.println("取得した文字列は" + stu_gender + "です！");
            // System.out.println("取得した文字列は" + taikai_l + "です！");
            // System.out.println("取得した文字列は" + taikai_k + "です！");

            String tourl = null;
            if (stu_id.isEmpty() || stu_name.isEmpty() || stu_gender.isEmpty()) {
                tourl = "/WEB-INF/student/registStudentHandError.jsp";
                System.out.println("Please full all");
            } else {
                // studentオブジェクトに情報を格納
                int gender = 0;
                switch (stu_gender) {
                    case "1":
                        gender = 1;
                        break;
                    case "2":
                        gender = 2;
                        break;
                    case "3":
                        gender = 3;
                        break;
                }

                Student student = new Student(stu_id, stu_name, gender, stu_user);
                // StudentMemo studentmemo = new StudentMemo(stu_id, stu_name,
                // stu_gender,stu_user);
                // HttpSession session = request.getSession();
                // session.setAttribute("StudentMemo",studentmemo);
                request.setAttribute("Student", student);
                session.setAttribute("Student", student);

                // StudentManagerオブジェクトの生成
                // StudentService service = new StudentService();

                // 登録
                // service.registStudent(student);

                // 成功画面を表示する
                // System.out.println("OK牧場");
                // response.sendRedirect("/TableTennis/RegistInfo");
                tourl = "/WEB-INF/student/registStudentHandConfirm.jsp"; // パスは、webappにいるところから考えないといけない！
            }

            getServletContext().getRequestDispatcher(tourl).forward(request, response);// 上のdoGetをまとめて書いている

            // studentオブジェクトに情報を格納
            // Student student = new Student(player_n, taikai_n, taikai_l, taikai_k);

            // StudentManagerオブジェクトの生成
            // StudentManager manager = new StudentManager();

            // 登録
            // manager.registStudent(student);

            // 成功画面を表示する
            // System.out.println("OK牧場");
            // response.sendRedirect("/TableTennis/RegistInfo");
        }

    }
}