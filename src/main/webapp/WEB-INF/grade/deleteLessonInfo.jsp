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
  <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
</head>

  <body>
    <div class="darkblue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">授業評価削除</h1>
    </div>
  
    <br>
    <div style="text-align: center;">
    <h2 style="color: red;">以下の内容を削除します。</h2><br/><br/>

    <form action="./DeleteLessonComplete" method="post">
     
      <h3>授業日<br/>
        <h3><%=Lesson.getLessonDate()%> <%=Lesson.getPeriodnum()%>限</h3>
        <br/>
    </p>
    <h3>授業コメント(400文字以内)</p></h3>
<input type="hidden" value="<%=Lesson.getId()%>" name="Id">
<h3> <%=Lesson.getComment()%></p></h3>

<br/>
<br/>
<h2 style="color: red;">
・授業日<br/>
・授業コメント<br/>
・授業評価に含まれる全ての個人評価<br/>
が削除されます。
</h2>
</div>

        <input type="submit" class="btn" id="btn_right"　value="入力を確定">

    </form>
    
      <button onclick="window.history.back(); return false;"align="center" class="button_grey" id="btn_left" name="regist_top" >評価メニュートップへ戻る</button></a>
  </body>
</html>