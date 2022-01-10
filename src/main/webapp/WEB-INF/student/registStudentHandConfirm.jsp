<%@page contentType= "text/html; charset=UTF-8" session="true"%>
<% String stu_id = request.getParameter("stu_id");%>
<% String stu_name = request.getParameter("stu_name");%>
<% String stu_gender = request.getParameter("stu_gender");%>
<% String stu_user = request.getParameter("stu_user");%>
<%@ page import= "beans.Student" %>

<html>
      <head>
        <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
      </head>
    <body>
      <div class="green">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">児童・生徒登録確認</h1>
    </div>
    <h2 align="center">以下の内容で児童・生徒を登録しますか？</h2><br><br>
    <form action="./RegistStudentHandConfirm" method="post">
    <table align="center">
      <tr>
        <th>番号　　　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　　　　</th><th>性別　　　　　　　　　</th><th>登録者　　　　　　　　　　　　</th></tr>
      </tr>
      <tr>
        <td>${Student.student_id}</td>
        <td>${Student.student_name}</td>
<% if( stu_gender.equals("1")){ %>
        <td>男</td>
<%} else if(stu_gender.equals("2")){ %>
        <td>女</td>
<%} else{ %>
        <td>その他</td>
<% } %>
        <td>${Student.student_user}</td>
      </tr>
    </table>
    <br><br><br>
    <div class="right">
      <input type="submit" value="登録" class="btn" id="btn_right">
    </div>
  </form>
  <form action="./GoRegistStudentTop" method="get">
    <input type="submit" value="キャンセル" class="button_grey" id="btn_left">
  </form>
  <!--a href="/student/registStudentTop.jsp"><button align="center" name="regist_top">キャンセル</button></a-->
    </body>
</html>
