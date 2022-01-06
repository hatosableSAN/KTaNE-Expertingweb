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
    <h2 align="center">児童・生徒情報の登録に失敗しました</h2>
    <form action="./StudentTop" method="get">
        <input type="submit" value="児童・生徒メニュートップへ戻る" class="backbtn_middle">
    </form>
  </body>
</html>