<%@page contentType= "text/html; charset=UTF-8" session="true"%>
<% String stu_id = request.getParameter("stu_id");%>
<% String stu_name = request.getParameter("stu_name");%>
<% String stu_gender = request.getParameter("stu_gender");%>
<% String stu_user = request.getParameter("stu_user");%>
<%@ page import= "beans.Student" %>

<html>
    <head>
       <title></title>
    </head>
    <body>
      <p align="right">ユーザーID　${User.id}</p>
    <h1 align="center">児童・生徒登録確認</h1>
    <h3 align="center">以下の内容で児童・生徒を登録しますか？</h3>
    <form action="./RegistStudentHandConfirm" method="post">
    <table align="center">
      <tr>
        <th>番号　　　　　　　</th><th>名前　　　　　　　　　　</th><th>性別　　　　</th><th>登録者　　　　　　　</th></tr>
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
    <div>
      <input type="submit" value="登録" class="button-red">
    </div>
  </form>
<<<<<<< HEAD
  <form action="./GoRegistStudentTop" method="get">
    <input type="submit" value="キャンセル" class="button-red">
  </form>
  <!--a href="/student/registStudentTop.jsp"><button align="center" name="regist_top">キャンセル</button></a-->
=======
  <a href=".webapp/student/registStudentTop.jsp"><button align="center" name="regist_top">キャンセル</button></a>
>>>>>>> 04f9aa699ff96c1f165bc2cb12f4f574062e0d78
    </body>
</html>
