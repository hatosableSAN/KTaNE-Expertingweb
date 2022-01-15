<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.User" %>
<% User User = (User)session.getAttribute("User"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
  <body>
    <div class="blue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">座席配置メニュートップ</h1>
    </div>
    <a href="./SystemTop" class="backbtn">トップへ戻る</a>
    <br/><br/><br/><br/>
    <p align="center">
    <a href="./RegistSeatingClass"><button text-align="center" name="regist_seating" class = "button" style=" width:500px; height:80px; font-size: 30px;">座席配置新規登録</button></a><br><br>
    <a href="./manageSeatingTop"><button align="center" name="manage_sea" class = "button" style=" width:500px; height:80px; font-size: 30px;">登録済みの座席配置の閲覧・管理</button></a><br>
    <br><Br>
  </body>
</html>