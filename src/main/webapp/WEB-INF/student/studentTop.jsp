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
      <h1 align="center">児童・生徒メニュートップ</h1>
    </div>
    <a href="./SystemTop" class="backbtn">トップへ戻る</a>
    <!--div class="center"-->
    <form action="./ManageStudent" method="post">
      <div class="center">
        <br><br><br>
      <input type="submit" value="登録済みの児童・生徒の一覧・管理" class="button" style=" width:500px; height:80px; font-size: 30px;">
      </div>
    </form>
    <br><br>
    <form action="./GoRegistStudentTop" method="get">
      <div class="center">
      <input type="submit" value="新規登録" class="button" style=" width:300px; height:80px; font-size: 30px;">
      </div>
    </form>
    <!--a href="/se21g1/student/registStudentTop.jsp"><button text-align="center" name="regist_stu">新規登録</button></a-->
    <!--/div-->
    <!--p align="left"><a href="./SystemTop"><button name="go_top" class="backbtn">トップへ戻る</button></a></p-->
    <!--input type="button" value="新規登録" align="center" name="regist_stu"-->
  <!--/div-->
  </body>
</html>