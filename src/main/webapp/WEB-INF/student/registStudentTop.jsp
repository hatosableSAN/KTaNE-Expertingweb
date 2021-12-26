<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    <p align="right">ID ${User.id}</p><!--ここのID出てこない…-->
    <h1 align="center">児童・生徒登録</h1>
    <!--a href="./studentTop.jsp">児童生徒トップへ</a-->
    <a href="./registStudentCSV.jsp"><button align="center" name="regist_csv_stu">CSVファイルから登録</button></a>
    <br><br>
    <!--a href="./registStudentHand.jsp"><button align="center" name="regist_hand_stu">手動で登録</button></a-->
    <form action="./RegistStudentHand" method="get">
      <input type="submit" value="手動で登録" class="button-red">
    </form>
    <br><br>
    <p>csvファイルとは：項目がコンマで区切られている、excel等で使用できるファイルを指します</p>
    <br>
    <!--a href="./studentTop.jsp"><button align="center" name="regist_top">児童・生徒トップへ戻る</button></a-->
    <form action="./GoStudentTop" method="get">
      <input type="submit" value="児童・生徒メニュートップへ戻る" class="button-red">
    </form>
  </body>
</html>