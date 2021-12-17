<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    <p align="right">ユーザーID　${User.id}</p>
    <h1 align="center">児童・生徒情報の変更が完了しました</h1>
    <!--a href="./student/registStudentTop.jsp"><button align="center" name="regist_top">児童・生徒一覧へ戻る</button></a-->
    <form action="./ManageStudent" method="post">
        <input type="submit" value="児童・生徒一覧へ戻る" class="button-red">
    </form>
  </body>
</html>