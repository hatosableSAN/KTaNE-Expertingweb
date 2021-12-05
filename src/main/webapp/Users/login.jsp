<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="../style.css">
        <h2 align="center">ログイン画面<br/></h2>
    </head>
  <body>
    <br/><br/><br/><br/>
    <form action="./Login" method="post">
        <p align="center">
        ID 　<input type="text" name="id" placeholder="半角英数字(６～１５文字)" pattern="^[a-zA-Z0-9]+$" maxlength="15"/><br /><br/>
        パスワード　 <input type="password" name="password" placeholder="半角英数字(８～１５文字)" pattern="^[a-zA-Z0-9]+$" maxlength="15"/><br /><br/>
        <input type="submit" name="OK" value="ログイン" class="btn" />
        </p>
    </form>
</html>