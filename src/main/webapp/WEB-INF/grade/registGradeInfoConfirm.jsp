<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.SeatingArrangements" %>
<% User User = (User)session.getAttribute("User"); %>
<% List<SeatingArrangements> SeatList = (List<SeatingArrangements>)request.getAttribute("SeatList"); %>
  
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
      <h1 align="center">評価新規作成</h1>
    </div>
    <br>
    この内容で登録します。<br/><br/>

    <form action="./RegistGradeAll" method="post">
      
      <h2>授業日：
      <%=ClassDate%>
      <input type="hidden" value="<%=ClassDate%>" name="ClassDate">
      <%=PeriodNum%>限</h2><br/>
      <input type="hidden" value="<%=PeriodNum%>" name="PeriodNum">
      <h2>授業コメント(400文字以内)</h2>
<input type="hidden" value="<%=Comment%>" name="Comment">
<h3> <%=Comment%></h3>
      </textarea>
    

        <input type="submit" class="btn" id="btn_right" value="評価を確定する">

    </form>
    
    <a href="./RegistClassesInfo"><button align="center" class="button_grey" id="btn_left" name="regist_top">入力画面へ戻る</button></a>
  </body>
</html>

