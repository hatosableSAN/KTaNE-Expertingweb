<%@page contentType= "text/html; charset=UTF-8" session="true"%>
<%@ page import= "beans.Student" %>
<%@ page import= "beans.User" %>
<%@ page import="java.util.*" %>
<% User User = (User)session.getAttribute("User"); %>
<% String stu_id = request.getParameter("stu_id");%>
<% String stu_name = request.getParameter("stu_name");%>
<% String stu_gender = request.getParameter("stu_gender");%>
<% String stu_user = request.getParameter("stu_user");
  List<Student> studentList = (List<Student>)request.getAttribute("studentList");
  List<Student> failStudentList = (List<Student>)request.getAttribute("failStudentList");
  List<Student> registStudentList = (List<Student>)request.getAttribute("registStudentList");
  %>

<html>
      <head>
        <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
      </head>
    <body>
    <div class="green">
     <p align="right">ユーザーID　${User.id}</p>
    </div>
    <h2 align="center">以下の児童・生徒情報の登録に失敗しました</h2>
    <table align="center">
      <tr>
        <th>番号　　　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　　　　</th><th>性別　　　　　　　　　</th><th>登録者　　　　　　　　　　　　</th></tr>
      </tr>
      <%for(Student s:failStudentList){ %>
                <tr>
                <td><%=s.getStudent_id() %></td>
                <td><%=s.getStudent_name() %></td>
                <td><% if(s.getStudent_gender()==1){ %>
                    男
                    <% }else if(s.getStudent_gender()==2){ %>
                    女
                    <% }else if(s.getStudent_gender()==3){ %>
                    その他
                    <%} %>
                </td>
                <td><%=s.getStudent_user() %></td>
                </tr>
                <%}%>
    </table><br>
　<h2 align="center">登録成功リスト</h2>
    <table align="center">
      <tr>
        <th>番号　　　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　　　　</th><th>性別　　　　　　　　　</th><th>登録者　　　　　　　　　　　　</th></tr>
      </tr>
      <%for(Student s:registStudentList){ %>
                <tr>
                <td><%=s.getStudent_id() %></td>
                <td><%=s.getStudent_name() %></td>
                <td><% if(s.getStudent_gender()==1){ %>
                    男
                    <% }else if(s.getStudent_gender()==2){ %>
                    女
                    <% }else if(s.getStudent_gender()==3){ %>
                    その他
                    <%} %>
                </td>
                <td><%=s.getStudent_user() %></td>
                </tr>
                <%}%>
    </table>
    <form action="./StudentTop" method="get">
      <div class="center">
        <input type="submit" value="児童・生徒メニュートップへ戻る" class="backbtn_middle">
      </div>
    </form>
    </body>
</html>
