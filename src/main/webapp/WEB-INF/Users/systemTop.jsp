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
    <p align="right">ID ${User.id}</p>
    <div class="darkgray"><h2 style="text-align:center">児童・生徒評価システムトップ</h2></div>
    <br/><br/><br/><br/>
    <p align="center">
    <button>
        このボタンは<big><b><font color="red">サンプル</font></b></big>のため動作しません
      </button>　　　　
      <button>
        このボタンは<big><b><font color="red">サンプル</font></b></big>のため動作しません
      </button><br/><br/><br/><br/>
      <button>
        このボタンは<big><b><font color="red">サンプル</font></b></big>のため動作しません
      </button>　　　　
      <button>
        このボタンは<big><b><font color="red">サンプル</font></b></big>のため動作しません
      </button><br/><br/><br/><br/><br/>


      <button>
        <big><b>システムマニュアル</b></big>
      </button>　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
      <a href="/se21g1/Login" class="btn">ログイン</a>
    </p>


  </body>
</html>