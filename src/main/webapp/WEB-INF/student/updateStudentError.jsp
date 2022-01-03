<%@page contentType= "text/html; charset=UTF-8" session="true"%>
<% String stu_id = request.getParameter("stu_id");%>
<% String stu_name = request.getParameter("stu_name");%>
<% String stu_gender = request.getParameter("stu_gender");%>
<% String stu_user = request.getParameter("stu_user");%>
<%@ page import= "beans.Student" %>
<html>
    <head>
      <head>
        <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
    </head>
    </head>
    <body>
      <div class="green">
      <p align="right">ユーザーID　${User.id}</p>
    <h1 align="center">児童・生徒情報変更</h1>
    </div>
    <form action="./UpdateStudentCheck" method="post">
    <table align="center">
      <tr><th>番号<font color="red">＊</font>　　　　　　　　</th><th>名前<font color="red">＊</font>　　　　　　　　　　　　　</th><th>性別　　　　　　</th><th>登録者　　　　　　　　　　</th></tr>
        <tr><td>${Student.student_id}<input type="hidden" name="stu_id" value=${Student.student_id}></td>
        <td><input type="text" name="stu_name" placeholder=${Student.student_name}></td>
        <td>
        <!--td>${Student.student_gender}</td-->
<% if( stu_gender.equals("1")){ %>
  <select name='stu_gender'>");
    <option value='1' selected>男</option>
    <option value='2'>女</option>
    <option value='3'>その他</option>
   </select>
<%} else if(stu_gender.equals("2")){ %>
  <select name='stu_gender'>");
    <option value='1'>男</option>
    <option value='2' selected>女</option>
    <option value='3'>その他</option>
   </select>
<%} else{ %>
  <select name='stu_gender'>");
    <option value='1'>男</option>
    <option value='2'>女</option>
    <option value='3' selected>その他</option>
   </select>
<% } %>
</td>
        <td>${Student.student_user}<input type="hidden" name="stu_user" value=${Student.student_user}></td>
      </tr>
    </table>
    <h5 align="center"><font color="red">＊は必須項目です。すべて入力し、変更を押してください</font></h5>
    <div class="center">
      <input type="submit" value="変更" class="button">
    </div>
  </form>
  <form action="./ManageStudent" method="post">
   <input type="submit" value="児童生徒一覧へ戻る" class="backbtn_middle">
  </form>
    </body>
</html>