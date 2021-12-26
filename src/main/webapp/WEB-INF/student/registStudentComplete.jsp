<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    <p align="right">ユーザーID　${Student.student_user}</p>
    <h1 align="center">児童・生徒の登録が完了しました</h1>
    <form action="./GoRegistStudentTop" method="get">
      <input type="submit" value="児童・生徒登録へ戻る" class="button-red">
    </form>
    <!--a href="./student/registStudentTop.jsp"><button align="center" name="regist_top">児童・生徒登録へ戻る</button></a-->
  </body>
</html>