<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!-- beans.Userをimportする -->
<%@ page import= "beans.User" %>

<html>
    <head>
        <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
    </head>
  <body>
    <div class="darkgray">
        <p align="right">ユーザーID　${User.id}</p>
        </div>
    <p align="right"></p>
    <br/><br/><br/><br/>

    <h2 align="center">ログアウトしますか？</h2>

    <p align="center">
        <a href="/se21g1/SystemTop" class="btn">トップへ戻る</a>　　　　　
        <a href="/se21g1/Logout" class="btn">ログアウト</a>
      </p>
    
  </body>
</html>
