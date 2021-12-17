<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    <!--h1 align="center">Hello SE21G1!</h1-->
    <p align="right">ID ${User.id}</p>
    <h1 align="center">児童・生徒メニュートップ</h1>
    <form action="./ManageStudent" method="post">
      <input type="submit" value="登録済みの児童・生徒の一覧・管理" class="button-red">
    </form>
    <br>
    <form action="./GoRegistStudentTop" method="get">
      <input type="submit" value="新規登録" class="button-red">
    </form>
    <!--a href="/se21g1/student/registStudentTop.jsp"><button text-align="center" name="regist_stu">新規登録</button></a-->
    <br><br>
    <a href="./SystemTop"><button align-items="center" name="go_top">トップへ戻る</button></a>
    <!--input type="button" value="新規登録" align="center" name="regist_stu"-->
  </body>
</html>