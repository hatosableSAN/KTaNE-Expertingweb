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
    
  </body>
</html>
