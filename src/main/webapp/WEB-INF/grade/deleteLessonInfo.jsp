<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.*" %>
<%@ page import="beans.SeatingArrangements" %>
<% Lessons Lesson = (Lessons)session.getAttribute("Lesson"); %>

  
    <% String ClassDate=(String)request.getParameter("ClassDate");%>
    <% String PeriodNum=(String)request.getParameter("PeriodNum");%>
    <% String Comment=(String)request.getParameter("Comment");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>

  <body>
  <p align="right">ID: ${User.id}</p>
    <h1 align="center">授業評価作成</h1>
    <br>
    以下の内容を削除します。<br/><br/>

    <form action="./DeleteLessonComplete" method="post">
    <p>授業日</p>
      <%=Lesson.getLessonDate()%>
      <%=Lesson.getPeriodnum()%>限<br/>
<p>授業コメント(400文字以内)</p>
<input type="hidden" value="<%=Lesson.getId()%>" name="Id">
<p> <%=Lesson.getComment()%></p>
      </textarea>

        <input type="submit" value="入力を確定">

    </form>
    
      <a href="./GradeTop"><button align="center" name="regist_top">評価メニュートップへ戻る</button></a>
  </body>
</html>