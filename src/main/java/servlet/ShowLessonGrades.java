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

import beans.ClassDef;
import beans.Grade;
import beans.Lessons;
import beans.SeatingArrangements;
import beans.Student; //beansに入れた方がいいのかしら
import beans.StudentSeatingArr;
import service.ClassService;
import service.GradeService;
import service.SeatingService;
import service.StudentService;
import utility.*;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/ShowLessonGrades")

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class ShowLessonGrades extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);

        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./sessionerror.jsp");
            dispatcher.forward(request, response);
        } else {

            System.out.println("教室風評価登録");
            String idnumber = request.getParameter("id");
            int id = Integer.parseInt(idnumber);// 授業ID
            session.setAttribute("Lessonnum", idnumber);
            GradeService gService = new GradeService();

            List<Grade> GradeList = gService.getGradeList(id);// ひょうかりすと
            Lessons lesson = gService.searchLesson(id);// じゅぎょう(コメント、授業名、授業日が必要)
            SeatingService sService = new SeatingService();
            SeatingArrangements SeatingArrangements = sService
                    .searchSeatingArrangements(lesson.getSeating_arrangements_id());
            // 得た授業に対応する座席配置(開始期間、終了期間)
            ClassService cService = new ClassService();
            ClassDef Class = new ClassDef();
            Class.setClass_id(SeatingArrangements.getClassId());
            Class = cService.findClass(Class);

            session.setAttribute("GradeList", GradeList);// ゲットした座席リストをセッションに入れるよ
            session.setAttribute("Lesson", lesson);// ゲットした授業をセッションに入れるよ
            session.setAttribute("SeatingArrangements", SeatingArrangements);// ゲットした座席配置をセッションに入れるよ
            session.setAttribute("Class", Class);// ゲットした座席配置をセッションに入れるよ

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/grade/showLessonGrades.jsp");
            dispatcher.forward(request, response);

        }
    }

}