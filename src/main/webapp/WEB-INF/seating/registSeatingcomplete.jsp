<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
  <body>
    <div class="blue">
      <p align="right">ユーザーID　${User.id}</p>
    </div>
    <h2 align="center">座席配置の登録が完了しました</h2>
    <br><br><br>
    <p align = "center"><a href="./SeatingTop" class="backbtn3">座席配置メニュートップへ戻る</a></p>
  </body>
</html>