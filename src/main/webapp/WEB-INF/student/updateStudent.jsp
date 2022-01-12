<%@page contentType= "text/html; charset=UTF-8" session="true"%>
<%@ page import= "beans.Student" %>
<%
    Student student=(Student) session.getAttribute("Student");
%>
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
    </div><br><br><br>
    <form action="./UpdateStudentCheck" method="post">
    <table align="center">
      <tr><th>番号<font color="red">＊</font>　　　　　　　　　　　　</th><th>名前<font color="red">＊</font>　　　　　　　　　　　　　</th><th>性別　　　　　　　　　</th><th>登録者　　　　　　　　　　</th></tr>
        <tr><td>${Student.student_id}<input type="hidden" name="stu_id" value=${Student.student_id}></td>
        <td><input type="text" name="stu_name" value=${Student.student_name} pattern="[ぁ-んァ-ヶｦ-ﾟ一-龠a-zA-Z\-\u30FC]+"></td>
        <td>
        <!--td>${Student.student_gender}</td-->
<% if( student.getStudent_gender()==1){ %>
  <select name='stu_gender'>");
    <option value='1' selected>男</option>
    <option value='2'>女</option>
    <option value='3'>その他</option>
   </select>
<%} else if(student.getStudent_gender()==2){ %>
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
    </table><br><br><br>
    <div class="center">
      <input type="submit" value="変更" class="btn" style="font-size: 20px; width:120px; height:50px;">
    </div>
  </form>
  <a href="./ManageStudent"><button align="center" name="class_top" class="backbtn">児童・生徒<br>一覧へ戻る</button></a>
  <!--form action="./ManageStudent" method="post">
   <input type="submit" value="児童生徒一覧へ戻る" class="backbtn">
  </form-->
    </body>
</html>