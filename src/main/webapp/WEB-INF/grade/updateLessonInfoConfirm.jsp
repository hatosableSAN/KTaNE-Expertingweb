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
    以下の内容に変更します。<br/><br/>

    <form action="./UpdateLessonInfoComplete" method="post">
      <p>授業日</p>
        <%=ClassDate%>
        <input type="hidden" value="<%=ClassDate%>" name="LessonDate">
        <%=PeriodNum%>限<br/>
        <input type="hidden" value="<%=PeriodNum%>" name="PeriodNum">
  <p>授業コメント(400文字以内)</p>
  <input type="hidden" value="<%=Comment%>" name="Comment">
  <input type="hidden" value="<%=Lesson.getId()%>" name="Id">
  <p> <%=Comment%></p>
        </textarea>

        <input type="submit" class="btn" id="btn_right" value="変更を確定">

    </form>
    
      <a href="./GradeTop"><button align="center" class="button_grey" id="btn_left" name="regist_top">評価メニュートップへ戻る</button></a>
  </body>
</html>