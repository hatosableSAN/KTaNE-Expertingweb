<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.User" %>
<% User User = (User)session.getAttribute("User"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <p align="right">ID: ${User.id}</p>
    <div class="darkgray"><h2 style="text-align:center">座席配置メニュートップ</h2></div>
    <br/><br/><br/><br/>
    <p align="center">
    <a href="./manageSeatingTop"><button align="center" name="manage_sea">登録済みの座席配置の閲覧・管理</button></a><br><Br>
    <a href="./RegistSeatingClass"><button text-align="center" name="regist_seating">座席配置新規登録</button></a>
    <br><Br>
    <a href="../WEB-INF/Users/systemTop.jsp"><button align-items="center" name="regist_stu">トップへ戻る</button></a>
  </body>
</html>