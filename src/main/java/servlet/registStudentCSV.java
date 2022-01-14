package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.io.File;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Part;
import beans.Student; //beansに入れた方がいいのかしら
import beans.User;
import service.StudentService;
import utility.*;
import java.nio.file.Files;
import java.nio.file.Path;

//アノテーションの記述
//jspで示してあげると、jspから呼び出さられる
@WebServlet("/registStudentCSV")
// アノテーションで、サーブレットがmultipart/form-data形式のリクエストに対応
@MultipartConfig(maxFileSize = 1048576)

// HttpServletを継承することで、このクラスはServletとして、働くことができる
public class registStudentCSV extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // doPostメソッドから呼び出される(リダイレクトされる)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (LoginChecker.notLogin(session)) {
            System.out.println("セッション情報がありません");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/student/registStudentCSV.jsp").forward(request,
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
            RequestDispatcher dispatcher = request.getRequestDispatcher(LoginChecker.getErrorpage());
            dispatcher.forward(request, response);
        } else {
            // requestオブジェクトの文字エンコーディングの設定
            request.setCharacterEncoding("UTF-8");
            System.out.println("registStudentcsvのPost");
            User user = (User) session.getAttribute("User");
            String error = "エラー";// エラー文

            // アップロード操作
            Part part = request.getPart("file");
            if (part.getSize() == 0) {
                System.out.println("ファイルが送信されてません");
                error = "ファイルが送信されてません";
                request.setAttribute("error", error);
                RequestDispatcher rd = request
                        .getRequestDispatcher("/WEB-INF/student/registStudentCSVError.jsp");
                rd.forward(request, response);
            } // ファイルが送信されなかった場合
              // String filename=part.getSubmittedFileName();//ie対応が不要な場合
            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            // アップロードするフォルダ
            String path = getServletContext().getRealPath("/upload");
            // 実際にファイルが保存されるパス確認
            System.out.println(path);
            // 書き込み
            part.write(path + File.separator + filename);
            // CSVファイルの読み込み
            List<Student> studentList = new ArrayList<Student>();// 生徒リスト
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(path + File.separator + filename), Charset.forName("Shift-JIS")))) {
                String line;
                int index = 0;
                while ((line = reader.readLine()) != null) {
                    if (index > 0) {// 最初の項目タイトルを飛ばす
                        // 形式:1行目項目名、2行目以降 id,name,gender（女男その他)
                        String[] data = line.split(",");
                        if (data.length > 2) {
                            // 読み込んだCSVファイルの内容を出力
                            // 不正な値がないかチェック（性別の書きミスや文字数制限、他TODO
                            boolean checkdatastyle = true;
                            for (int i = 0; i < 2; i++) {
                                // 他、文字数や記号等もTODO
                                if (data[i] == null || data[i].equals("")) {// 空白
                                    System.out.println("空白項目ありエラー");
                                    checkdatastyle = false;
                                }
                                if (i == 0 && !(data[i].length() <= 15 && data[i].matches("^[0-9A-Za-z]+$"))) {// 文字数
                                    System.out.println("番号に不正な値あり");
                                    checkdatastyle = false;
                                }
                                if (i == 1
                                        && !(data[i].length() <= 20)
                                        && data[i].matches("^[ぁ-んァ-ヶｦ-ﾟ一-龠a-zA-Z0-9・]+$")) {
                                    System.out.println("名前に不正な値あり");
                                    checkdatastyle = false;
                                }
                                if (!checkdatastyle) {
                                    error = "不正な値の項目があります";
                                    request.setAttribute("error", error);
                                    RequestDispatcher rd = request
                                            .getRequestDispatcher("/WEB-INF/student/registStudentCSVError.jsp");
                                    rd.forward(request, response);
                                }
                            }
                            System.out.println(data[0] + "," + data[1] + "," + data[2]);
                            int gender = 0;
                            switch (data[2]) {
                                case "男":
                                    gender = 1;
                                    break;
                                case "女":
                                    gender = 2;
                                    break;
                                case "その他":
                                    gender = 3;
                                    break;
                                default:
                                    System.out.println("性別欄に不正な値");
                                    error = "不正な値の項目があります";
                                    request.setAttribute("error", error);
                                    RequestDispatcher rd = request
                                            .getRequestDispatcher("/WEB-INF/student/registStudentCSVError.jsp");
                                    rd.forward(request, response);
                            }
                            Student student = new Student(data[0], data[1], gender, user.getId());
                            System.out.println(data[0] + "," + data[1] + "," + gender + "," + user.getId());
                            studentList.add(student);
                        }
                    }
                    index++;
                }
            } catch (IOException e) {
                System.out.println("ファイル読み込みに失敗");
                error = "ファイル読み込みに失敗";
                request.setAttribute("error", error);
                RequestDispatcher rd = request
                        .getRequestDispatcher("/WEB-INF/student/registStudentCSVError.jsp");
                rd.forward(request, response);
            }
            // ファイル名を受け渡す
            request.setAttribute("filename", filename);
            request.setAttribute("studentList", studentList);
            session.setAttribute("studentList", studentList);

            // CSVファイル内でIDがかぶっているときにエラーを出す
            boolean checkresult = false;
            Set<String> checkHash = new HashSet<>();
            for (int i = 0; i < studentList.size(); i++) {
                if (checkHash.contains(studentList.get(i).getStudent_id())) {
                    // 重複があればtrueをセットし終了
                    checkresult = true;
                    break;
                } else {
                    // 重複しなければハッシュセットへ追加
                    checkHash.add(studentList.get(i).getStudent_id());
                }
            }

            if (checkresult) {// 重複がある
                error = "csvの中に重複した番号が存在します。";
                request.setAttribute("error", error);
                RequestDispatcher rd = request
                        .getRequestDispatcher("/WEB-INF/student/registStudentCSVError.jsp");
                rd.forward(request, response);
            } else {// 重複がない
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/student/registStudentCSVConfirm.jsp");
                rd.forward(request, response);
            }

        }

    }

    private boolean checkDatalength(String data, int length) {
        if (data.length() > length) {
            return false;
        }
        return true;
    }

}