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
      <p align="right">ユーザーID　${Student.student_user}</p>
        <!--h2>以下の内容で児童・生徒を登録しますか？</h2>
        <h4>番号　　名前　　　性別　　　登録者</h4>
    <form action="./RegistStudentHandConfirm" method="post"></form>
        <%= stu_id %><%= stu_name %><%= stu_gender %><%= stu_user %>
        <br />
        <a href="./registStudentTop.jsp"><button align="center" name="manage_stu">キャンセル</button></a>
        <input type="submit" value="登録" name="OK" />
    </form-->
    <h1 align="center">児童・生徒登録確認</h1>
    <h2>以下の内容で児童・生徒を登録しますか？</h2>
    <form action="./RegistStudentHandConfirm" method="post">
    <table>
      <tr>
        <th>番号</th>
        <td>${Student.student_id}</td>
      </tr>
      <tr>
        <th>名前</th>
        <td>${Student.student_name}</td>
      </tr>
      <tr>
        <th>性別</th>
        <td>${Student.student_gender}</td>
      </tr>
      <tr>
        <th>登録者</th>
        <td>${Student.student_user}</td>
      </tr>
      <tr>
          </tr>
      <tr>
          </tr>
    </table>
    <div>
      <input type="submit" value="登録" class="button-red">
    </div>
  </form>
  <a href=".webapp/student/registStudentTop.jsp"><button align="center" name="regist_top">キャンセル</button></a>
    </body>
</html>
