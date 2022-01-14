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
      <h1 align="center">児童・生徒登録</h1>
    </div>
    <!--a href="./studentTop.jsp">児童生徒トップへ</a-->
    <div class="center">
    <a href="./registStudentCSV"><button align="center" name="regist_csv_stu" class="button" style="margin: 20px; height: 70px; font-size: 25px;">CSVファイルから登録</button></a>
    </div><!--ここは遷移できない、今csvで登録を別ブランチで作業中なので-->
    <form action="./RegistStudentHand" method="get">
      <div class="center">
      <input type="submit" value="手動で登録" class="button" style="margin: 20px; width:150px; height: 70px; font-size: 25px;">
      </div>
    </form>
    <br><br><br>
    <div class="center">csvファイルとは：項目がコンマで区切られている、excel等で使用できるファイルを指します</div>
    <br>
    <!--a href="./studentTop.jsp"><button align="center" name="regist_top">児童・生徒トップへ戻る</button></a-->
    <form action="./GoStudentTop" method="get">
      <input type="submit" value="児童・生徒メニュートップへ戻る" class="backbtn">
    </form>
  </body>
</html>