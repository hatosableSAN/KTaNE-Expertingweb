<%@page contentType= "text/html; charset=UTF-8" session="true"%>
<% String stu_id = request.getParameter("stu_id");%>
<% String stu_name = request.getParameter("stu_name");%>
<% String stu_gender = request.getParameter("stu_gender");%>
<% String stu_user = request.getParameter("stu_user");%>
<%@ page import= "beans.Student" %>
<%
    Student student=(Student) session.getAttribute("Student");
%>

<html>
      <head>
        <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
      </head>
    <body>
      <div class="green">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">児童・生徒情報削除</h1>
    </div>
    <h2 align="center"><font color="red">以下の児童・生徒を削除しますか？</font></h2><br><br>
    <form action="./DeleteStudentConfirm" method="post">
    <table align="center">
      <tr>
        <th>番号　　　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　　　　</th><th>性別　　　　　　　　　</th><th>登録者　　　　　　　　　　　　</th></tr>
      </tr>
      <tr>
        <td>${Student.student_id}</td>
        <td>${Student.student_name}</td>
<% if( student.getStudent_gender() == 1){ %>
        <td>男</td>
<%} else if(student.getStudent_gender() == 2){ %>
        <td>女</td>
<%} else{ %>
        <td>その他</td>
<% } %>
        <td>${Student.student_user}</td>
      </tr>
    </table>
    <br><br><br>
    <input type='hidden'/ name='delete_hidden' value=${Student.student_id}>
    <div class="right">
      <input type="submit" value="削除" class="btn" id="btn_right" style="color:red; width:120px; height:50px;">
    </div>
  </form>
  <form action="./ManageStudent" method="post">
    <input type="submit" value="キャンセル" class="button_grey" id="btn_left">
  </form>
  <!--a href="/student/registStudentTop.jsp"><button align="center" name="regist_top">キャンセル</button></a-->
    </body>
</html>