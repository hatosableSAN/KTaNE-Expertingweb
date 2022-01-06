<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!-- beans.Userをimportする -->
<%@ page import= "beans.User" %>

<html>
    <head>
        <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
    </head>
  <body>
    <p align="right"></p>
    <div class="darkgray"><p align="right">ユーザーID ${User.id}</p><h2 style="text-align:center"></h2></div><br/>
    <br/><br/><br/><br/>
    <p align="center">
        <FONT size="6">
            ログアウトしますか？<br/><br/><br/>
        </FONT>
    </p>
    <p align="center">
        <a href="/se21g1/SystemTop" class="btn">トップへ戻る</a>　　　　　
        <a href="/se21g1/Logout" class="btn">ログアウト</a>
      </p>
    
  </body>
</html>
