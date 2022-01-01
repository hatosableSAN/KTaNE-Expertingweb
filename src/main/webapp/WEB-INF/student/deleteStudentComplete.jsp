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
    <h2 align="center">児童・生徒情報の削除が完了しました</h2>
    <!--a href="./student/registStudentTop.jsp"><button align="center" name="regist_top">児童・生徒一覧へ戻る</button></a-->
    <form action="./ManageStudent" method="post">
      <div class="center">
        <input type="submit" value="児童・生徒一覧へ戻る" class="backbtn_middle">
      </div>
    </form>
  </body>
</html>