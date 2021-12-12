<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    <h1 align="center">児童・生徒登録</h1>
    <!--a href="./studentTop.jsp">児童生徒トップへ</a-->
    <a href="./registStudentCSV.jsp"><button align="center" name="regist_csv_stu">CSVファイルから登録</button></a>
    <br><br>
    <a href="./registStudentHand.jsp"><button align="center" name="regist_hand_stu">手動で登録</button></a>
    <br><br>
    <p>csvファイルとは：項目がコンマで区切られている、excel等で使用できるファイルを指します</p>
    <br>
    <a href="./studentTop.jsp"><button align="center" name="regist_top">児童・生徒トップへ戻る</button></a>
  </body>
</html>