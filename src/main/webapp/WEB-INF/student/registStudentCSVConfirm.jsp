<%@page contentType= "text/html; charset=UTF-8" session="true"%>
<%@ page import= "beans.Student" %>
<%@ page import= "beans.User" %>
<%@ page import="java.util.*" %>
<% User User = (User)session.getAttribute("User"); %>
<% String stu_id = request.getParameter("stu_id");%>
<% String stu_name = request.getParameter("stu_name");%>
<% String stu_gender = request.getParameter("stu_gender");%>
<% String stu_user = request.getParameter("stu_user");
  String filename=(String)request.getAttribute("filename");
  List<Student> studentList = (List<Student>)request.getAttribute("studentList");
  %>

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


    <h3 align="center"><%=filename %></h3>

    <form action="./RegistStudentCSVConfirm" method="post">
    <table align="center">
      <tr>
        <th>番号　　　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　　　　</th><th>性別　　　　　　　　　</th><th>登録者　　　　　　　　　　　　</th></tr>
      </tr>
      <%for(Student s:studentList){ %>
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
    <br><br><br>
    <div class="right">
      <input type="submit" value="登録" class="btn" id="btn_right">
    </div>
  </form>
  <form action="./registStudentCSV" method="get">
    <input type="submit" value="キャンセル" class="button_grey" id="btn_left">
  </form>
  <!--a href="/student/registStudentTop.jsp"><button align="center" name="regist_top">キャンセル</button></a-->
    </body>
</html>
