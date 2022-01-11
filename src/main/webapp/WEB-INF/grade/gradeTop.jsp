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
    <div class="darkblue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">評価メニュートップ</h1>
    </div>
    <a href="./SystemTop" class="backbtn">トップへ戻る</a>
    <br/><br/><br/>
    <p align="center">
    <a href="./SelectGradeSeating"><button class = "button" align="center" style=" width:500px; height:80px; font-size: 30px;">　　　新規作成　　　</button></a><br/><br/>
    <a href="./SelectGradeLessons"><button class = "button" text-align="center" style=" width:500px; height:80px; font-size: 30px;">　　授業評価一覧　　</button></a><br/><br/>
    <a href="./SelectGradeStudent"><button class = "button" text-align="center" style=" width:500px; height:80px; font-size: 30px;">児童生徒個人評価一覧</button></a>
    <br><Br>
  </body>
</html>