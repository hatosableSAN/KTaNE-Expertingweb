<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <link rel="stylesheet" href="../style.css">
    </head>
  <body>
    <div class="darkgray"><h2 style="text-align:center">ユーザ登録画面</h2></div><br/>
    <br/><br/><br/><br/>
    <form action="./RegistUser" method="post">
        <p align="center">
        ID <input type="text" name="id" placeholder="半角英数字(６～１５文字)" pattern="^[a-zA-Z0-9]+$" maxlength="15"/><br /><br/>
        パスワード　 <input type="password" name="password" placeholder="半角英数字(８～１５文字)" pattern="^[a-zA-Z0-9]+$" maxlength="15"/><br /><br/>
        パスワード確認（再入力）　 <input type="password" name="password2" placeholder="半角英数字(８～１５文字)" pattern="^[a-zA-Z0-9]+$" maxlength="15"/><br /><br/>
        <input type="submit" name="OK" value="確認画面へ" class="btn" />
        </p>
    </form>
  </body>
</html>
