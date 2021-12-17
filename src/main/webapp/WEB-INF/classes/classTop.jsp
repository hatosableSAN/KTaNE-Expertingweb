<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    <!--h1 align="center">Hello SE21G1!</h1-->
    <h1 align="center">クラスメニュートップ</h1>
    <form action="../RegistStudentGet" method="post">
      <!--input type="submit" value="クラス新規登録" class="button-red"-->
      <!--a href="./registStudentGet.jsp"--><button text-align="center" name="regist_class">クラス新規登録</button><!--/a-->
    </form>
    <!--a href="./manageStudentTop.jsp"><button align="center" name="manage_stu">登録済みの児童・生徒の一覧・管理</button></a-->
    <br>
    <a href="../WEB-INF/Users/systemTop.jsp"><button align-items="center" name="regist_stu">トップへ戻る</button></a>
    <!--input type="button" value="新規登録" align="center" name="regist_stu"-->
  </body>
</html>