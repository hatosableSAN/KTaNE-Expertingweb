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
    <div class="darkgray">
      <p align="right" class="id">ユーザーID　${User.id}</p>
      <h1 style="text-align:center">児童・生徒評価システムトップ</h1>
    </div>
    <br/><br/><br/>
    <p align="center">
    <a href="./StudentTop"><button class="button" style=" width:250px; height:80px; font-size: 30px; margin: 20px;">
        児童生徒管理
      </button></a>
      <a href="./ClassTop"><button class="button" style=" width:250px; height:80px; font-size: 30px; margin: 20px;">
        クラス管理
      </button><!--br/><br/--><br/><br/>
      <a href="./SeatingTop"><button class="button" style=" width:250px; height:80px; font-size: 30px; margin: 20px;">
        座席配置管理
      </button></a>
      <button class="button" style=" width:250px; height:80px; margin: 20px;">
        このボタンは<font color="red">サンプル</font>のため動作しません
      </button>
    </p>
  </body>
</html>