<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.Lessons" %>
<% User User = (User)session.getAttribute("User"); %>
<% List<Lessons> LessonList = (List<Lessons>)request.getAttribute("LessonList"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
  <body>
    <div class="darkblue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">授業評価一覧</h1>
    </div>
    <br>
  <br>
    <table border="1">
      <tr>
        <th>授業日</th>
        <th>時限</th>
        <th>授業コメント</th>

    </tr>
      <% if(LessonList.size() > 0) {
        for(Lessons Lesson : LessonList ){ %>
 
        <tr>
            
            <td><%=Lesson.getLessonDate() %></td>
            <td><%=Lesson.getPeriodnum()%></td>
            <td><%=Lesson.getComment()%></td>
            <td><form action="./ShowLessonGrades" method="post">
              <input type="hidden" name="id" value=<%=Lesson.getId() %> >
              <input type="hidden" name="LessonDate" value=<%=Lesson.getLessonDate() %> >
              <input type="hidden" name="Comment" value=<%=Lesson.getComment() %> >
            <input type="submit" value="評価閲覧" ></form></td>
      <% } }%>
    </table>
      <br/>
      <a href="./LessoningTop"><button align="center" class="backbtn" name="regist_top">評価メニュートップへ戻る</button></a>
  </body>
</html>