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
    <br/>

    <h2 align="center">ログアウトしますか？</h2>
    <br/>
    <p align="center">
        <a href="/se21g1/SystemTop" ><button class="backbtn3" style="height: 50px; font-size: 16;">トップへ戻る</button></a>　　　　　
        <a href="/se21g1/Logout" ><button class="btn" style="font-size: 16;" >ログアウト</button></a>
      </p>
    
  </body>
</html>
