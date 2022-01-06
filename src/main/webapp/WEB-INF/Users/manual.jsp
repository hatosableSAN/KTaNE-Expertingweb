<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- beans.Userをimportする -->
<!--%@ page import= "beans.User" %-->
<html>
    <head>
        <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
    </head>
  <body>
    <div class="darkgray"><p align="left"><a href="/se21g1/SystemTop" class="backbtn">トップへ戻る</a>　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
      ユーザーID ${User.id}</p>
    <h2 style="text-align:center">システムマニュアル</h2></div>
    <h3>　目次</h3>
    　１．ユーザー情報管理<br/>
    　２．<br/>
    　３．<br/>
    　４．<br/>
    　５．<br/>
    <h3>　ご利用方法</h3>
    　１．ユーザー情報管理<br/>
    　１．１ユーザー登録<br/>
        　　　①スタート画面からユーザー登録ボタンを押します。<br/>
        　　　②IDの項目に６～１５字の半角英数字で登録したいID、パスワードの項目に８～１５字の半角英数字のパスワード、パスワード確認の項目に再度パスワードを入力し、確認画面へボタンを押します。パスワードには英字と数字を必ず１文字以上含めてください。<br/>
        　　　③登録情報を確認し、登録ボタンを押します。<br/>
        　　　④ユーザー登録が完了すると、ユーザー登録完了画面に遷移します。ログイン画面へボタンからログインボタンへ遷移します。<br/>
    　１．２ログイン<br/>
        　　　①ID、パスワードの項目にそれぞれ入力をしてログインボタンを押します。<br/>
        　　　②ログインに成功するとシステムトップに遷移します。<br/>
    　１．３パスワード変更<br/>
        　　　①変更前のパスワードの項目に現在使用しているパスワード、変更後のパスワードの項目に８～１５字の半角英数字で変更後のパスワード、変更後のパスワード確認の項目に再度変更後のパスワードを入力し、変更ボタンを押します。変更後のパスワードには英字と数字を必ず１文字以上含めてください。<br/>
        　　　②パスワード変更が完了するとパスワード変更完了画面に遷移します。<br/>
        
    　
    <p align="center">
    
    </p>


  </body>
</html>