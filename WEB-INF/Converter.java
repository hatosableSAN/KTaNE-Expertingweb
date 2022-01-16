

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("./Converter")
@MultipartConfig
public class Main extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//name属性がpictのファイルをPartオブジェクトとして取得
		File file=request.getParameter("select");
		//ファイル名を取得
		//String filename=part.getSubmittedFileName();//ie対応が不要な場合
		//アップロードするフォルダ
		//実際にファイルが保存されるパス確認
		System.out.println(file);
		//書き込み

		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/result.jsp");
		rd.forward(request, response);
	}
}class test {
    
}
