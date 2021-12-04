<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!-- beans.Userをimportする -->
<%@ page import= "beans.User" %>

<html>
    <head>
        <link rel="stylesheet" href="../style.css">
    </head>
  <body>
    <p align="right"><br/></p>
    <div class="darkgray"><h2 style="text-align:center">ユーザ情報確認画面</h2></div>
    <br/><br/><br/><br/>
    <p align="center">
        ID ${User.id}<br /><br/>
        パスワード　＊＊＊＊＊＊＊＊ <br /><br/>
    </p>
    <p align="center">
        <a href="/se21g1/RegistUser" class="btn">キャンセル</a>　　　　　
        <a href="/se21g1/RegistUser2" class="btn">登録</a>
      </p>
    
  </body>
</html>
