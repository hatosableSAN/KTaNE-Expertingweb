<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!-- beans.Studentをimportする -->
<%@ page import= "beans.User" %>

<html>
  <head>
    <title>ユーザ登録情報確認</title>
  </head>

  <body>
    <br />
    <p align="center">
        ID ${User.id}<br /><br/>
        パスワード　${User.password} <br /><br/>
    </p>
    <a href="./index.jsp">試合情報登録に戻る</a>
  </body>
</html>
