<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.Student" %>
<% String error = request.getParameter("error");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
  <body>
    <div class="green">
    <p align="right">ユーザーID　${User.id}</p>
    <h1 align="center">児童・生徒登録(CSV)</h1>
    </div>

<div class="center"style="margin-top:30px;">
    <form action="./registStudentCSV" method="post"enctype="multipart/form-data">
      <div>
        <label for="file">児童・生徒情報（csvファイル）の読み込み</label><br>
        <input type="file" id="csv" name="file" accept=".txt,.csv">
      </div><Br>
      csv形式は、1行目項目名、2行目以降に番号,名前,性別（男・女・その他）<br>
      <div><button  class="btn" id="btn_right">送信</button></div>
    </form>
    <font color="red">${error}</font>
    </div>
    <form action="./GoRegistStudentTop" method="get">
    <input type="submit" value="キャンセル" class="button_grey" id="btn_left">
  </form>
    <!--<form action="./RegistStudentHand" method="post">
      <table align="center">
        <tr><td>番号　<font color="red">＊</font>　　　　　　　　　　　　　</td><td>名前　<font color="red">＊</font>　　　　　　　　　　　　　　　　　</td><td>性別　　　　　　　　　</td></tr>
        <tr><td><input type="text" name="stu_id" placeholder="半角英数字(6~15文字)" maxlength="15" minlength="6" pattern="^[0-9A-Za-z]+$"/></td>
        <td><input type="text" name="stu_name" placeholder="(1~20文字)" maxlength="20" minlength="1" pattern="[ぁ-んァ-ヶｦ-ﾟ一-龠a-zA-Z\-\u30FC]+"/></td>
        <td>
        <select name="stu_gender">
            <option value="1">男</option>
            <option value="2">女</option>
            <option value="3">その他</option>
            </select>
        </td></tr>
      </table>
        <br><br><br>
        <div class="center">
          <input type="submit" value="確認" name="hand" class="btn" id="btn_check">
        </div>
      </form>-->
      <br />
      <form action="./StudentTop" method="get">
          <input type="submit" value="児童・生徒メニュートップへ戻る" class="backbtn">
      </form>
      <!--a href="./registStudentTop.jsp"><button align="center" name="regist_top">児童・生徒登録画面へ戻る</button></a-->
  </body>
</html>