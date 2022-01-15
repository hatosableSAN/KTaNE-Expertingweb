<%@page contentType= "text/html; charset=UTF-8" session="true" pageEncoding="UTF-8"%>
<% String stu_id = request.getParameter("stu_id");%>
<% String stu_name = request.getParameter("stu_name");%>
<% String stu_gender = request.getParameter("stu_gender");%>
<% String stu_user = request.getParameter("stu_user");%>
<%@ page import= "beans.Student" %>
<% Student student_info = (Student)session.getAttribute("Student"); %>
<html>
    <head>
      <head>
        <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
      <script type="text/javascript" src="<%=request.getContextPath()%>/css/stop_button.js"></script>
    </head>
    </head>
    <body>
      <div class="green">
       <p align="right">ユーザーID　${User.id}</p>
       <h1 align="center">児童・生徒情報変更確認</h1>
      </div>
    <h2 align="center">以下の内容で児童・生徒情報の変更を確定しますか？</h2><br><br>
    <form action="./UpdateStudentConfirm" method="post">
    <table align="center">
      <tr>
        <th>番号　　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　</th><th>性別　　　　　　　　　</th><th>登録者　　　　　　　　　　</th>
      </tr>
      <tr>
        <td>${Student.student_id}</td>
        <td>${Student.student_name}</td>
<% if( student_info.getStudent_gender() == 1){ %>
        <td>男</td>
<%} else if(student_info.getStudent_gender() == 2){ %>
        <td>女</td>
<%} else{ %>
        <td>その他</td>
<% } %>
        <td>${Student.student_user}</td>
      </tr>
    </table>
    <div>
      <input type="submit" value="変更を確定" class="btn" id="btn_right">
    </div>
  </form>
  <form action="./UpdateStudent" method="post">
    <input type="submit" value="入力画面へ戻る" class="button_grey" id="btn_left">
  </form>
  <!--a href="/student/registStudentTop.jsp"><button align="center" name="regist_top">キャンセル</button></a-->
    </body>
</html>