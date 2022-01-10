<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
    </head>
  <body>
    <div class="darkgray">
        <p align="right" class="id">ユーザーID　${User.id}</p>
        <h1 style="text-align:center">パスワード変更</h1>
        <a href="/se21g1/SystemTop" class="backbtn">トップへ戻る</a>　
      </div>
    <br/><br/><br/><br/>
    <form action="./UpdateUser" method="post">
        <p align="center">
            変更前のパスワード　 <input type="password" name="passwordP" placeholder="半角英数字(８～１５文字)" pattern="^[a-zA-Z0-9]+$" maxlength="15"/><br /><br/>
            変更後のパスワード　 <input type="password" name="passwordU" placeholder="半角英数字(８～１５文字)" pattern="^[a-zA-Z0-9]+$" maxlength="15"/><br /><br/>
            変更後パスワード確認（再入力）　 <input type="password" name="passwordU2" placeholder="半角英数字(８～１５文字)" pattern="^[a-zA-Z0-9]+$" maxlength="15"/><br /><br/>
            <font color="red">パスワードは半角英数字を必ず含む８～１５文字で入力してください</font><br/>
        <input type="submit" name="OK" value="　変更　" class="btn" id="btn_check"/>
        </p>
    </form>
  </body>
</html>