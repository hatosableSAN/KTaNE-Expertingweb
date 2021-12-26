<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
  <body>
    <div class="green">
    <p align="right">ユーザーID　${User.id}</p>
    </div>
    <h2 align="center">児童・生徒の登録が完了しました</h2>
    <form action="./GoRegistStudentTop" method="get">
      <div class="center">
      <input type="submit" value="児童・生徒登録へ戻る" class="backbtn2">
      </div>
    </form>
    <!--a href="./student/registStudentTop.jsp"><button align="center" name="regist_top">児童・生徒登録へ戻る</button></a-->
  </body>
</html>