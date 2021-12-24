<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.User" %>
<% User User = (User)session.getAttribute("User"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <p align="right">ID: ${User.id}</p>
    <div class="darkgray"><h2 style="text-align:center">評価メニュートップ</h2></div>
    <br/><br/><br/><br/>
    <p align="center">
    <a href="./SelectGradeSeating"><button align="center" >新規作成</button></a>
    <a href="./SelectGradeLessons"><button text-align="center" >授業評価一覧</button></a>
    <a href="./SelectGradeStudent"><button text-align="center" >児童生徒個人評価一覧</button></a>
    <br><Br>
    <a href="../WEB-INF/Users/systemTop.jsp"><button align-items="center" name="regist_stu">トップへ戻る</button></a>
  </body>
</html>