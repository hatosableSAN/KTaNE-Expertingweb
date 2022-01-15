<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
  <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
</head>

  <body>
    <div class="darkblue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">授業評価変更</h1>
    </div>
  
    <br>
    <h2>以下の内容に変更します。</h2><br/><br/>
<div style="text-align: center;">
    <form action="./UpdateLessonInfoComplete" method="post">
      <h3>授業日</h3>
        <h3><%=ClassDate%></h3>
        <input type="hidden" value="<%=ClassDate%>" name="LessonDate">
        <h3><%=PeriodNum%>限<br/></h3>
        <input type="hidden" value="<%=PeriodNum%>" name="PeriodNum">
      <h3><p>授業コメント(400文字以内)</p></h3>
  <input type="hidden" value="<%=Comment%>" name="Comment">
  <input type="hidden" value="<%=Lesson.getId()%>" name="Id">
  <h3> <%=Comment%></h3>
      </div>
        <input type="submit" class="btn" id="btn_right" value="変更を確定">

    </form>
    
      <a href="./UpdateLessonInfo"><button align="center" class="button_grey" id="btn_left" name="regist_top">入力画面へ戻る</button></a>
  </body>
</html>