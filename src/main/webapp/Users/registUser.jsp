<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <link rel="stylesheet" href="../style.css">
    </head>
  <body>
    <div class="darkgray"><h2 style="text-align:center"><br/>ユーザ登録画面</h2></div>
    <br/><br/><br/><br/>
    <form action="./RegistUser" method="post">
        <p align="center">
        ID <input type="text" name="id" /><br /><br/>
        パスワード　 <input type="text" name="password" /><br /><br/>
        パスワード確認（再入力）　 <input type="text" name="password2" /><br /><br/>
        <input type="submit" name="OK" value="確認画面へ" class="btn" />
        </p>
    </form>
  </body>
</html>
