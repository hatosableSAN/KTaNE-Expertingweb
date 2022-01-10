<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!-- beans.Userをimportする -->
<%@ page import= "beans.User" %>

<html>
    <head>
        <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
    </head>
  <body>
    <p align="right"></p>
    <div class="darkgray"><br/><h1 style="text-align:center">ユーザー情報確認画面</h1></div><br/>
    <br/><br/><br/><br/>
    <p align="center">
        ID ${User.id}<br /><br/>
        パスワード　＊＊＊＊＊＊＊＊ <br /><br/>
    </p>
    <p align="center">
        <a href="/se21g1/RegistUser" ><button class="button_grey">キャンセル</button></a>　　　　　
        <a href="/se21g1/RegistUser2" ><button class="btn">　登録　</button></a>
      </p>
    
  </body>
</html>
