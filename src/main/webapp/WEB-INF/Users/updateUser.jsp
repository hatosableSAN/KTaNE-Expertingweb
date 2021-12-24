<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" type="text/css" >
    </head>
  <body>
    <div class="darkgray"><p align="left"><a href="/se21g1/SystemTop" class="backbtn">トップへ戻る</a>　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
        ID ${User.id}</p>
      <h2 style="text-align:center">パスワード変更</h2></div><br/>
    <br/><br/><br/><br/>
    <form action="./UpdateUser" method="post">
        <p align="center">
        変更前のパスワード　 <input type="password" name="passwordP" placeholder="半角英数字(８～１５文字)" pattern="^[a-zA-Z0-9]+$" maxlength="15"/><br /><br/>
        変更後のパスワード　 <input type="password" name="passwordU" placeholder="半角英数字(８～１５文字)" pattern="^[a-zA-Z0-9]+$" maxlength="15"/><br /><br/>
        変更後パスワード確認（再入力）　 <input type="password" name="passwordU2" placeholder="半角英数字(８～１５文字)" pattern="^[a-zA-Z0-9]+$" maxlength="15"/><br /><br/>
        <input type="submit" name="OK" value="確認画面へ" class="btn" />
        </p>
    </form>
  </body>
</html>
