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
      <!--まさかのこのページ使っていないという-->
      <p align="right">ユーザーID　${User.id}</p>
    <h1 align="center">児童・生徒情報消去</h1>
    <h2><font color="red">以下の児童・生徒を削除しますか？</font></h2>
    <form action="./DeleteStudentConfirm" method="post">
    <table align="center">
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
        <!--td-->
        <!--td>${Student.student_gender}</td-->
        <% if( stu_gender.equals("1")){ %>
            <td>男</td>
    <%} else if(stu_gender.equals("2")){ %>
    <td>女</td>
    <%} else{ %>
      <td>その他</td>
    <% } %>
<!--/td-->
      </tr>
      <tr>
        <th>登録者</th>
        <td>${Student.student_user}</td>
      </tr>
    </table>
    <div>
      <input type="submit" value="削除" class="button-red">
    </div>
  </form>
  <a href="/student/registStudentTop.jsp"><button align="center" name="regist_top">キャンセル</button></a>
    </body>
</html>
