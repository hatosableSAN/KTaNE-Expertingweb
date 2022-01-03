<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.SeatingArrangements" %>
<% User User = (User)session.getAttribute("User"); %>
<% List<SeatingArrangements> SeatList = (List<SeatingArrangements>)request.getAttribute("SeatList"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
</head>

  <body>
  <p align="right">ID: ${User.id}</p>
    <h1 align="center">授業評価作成</h1>
    <br>
    授業日および授業全体のコメントを入力してください。<br>

    <form action="./RegistGradeInfoConfirm" method="post">
授業日：
      <input type="date" name="ClassDate">
      <input type="text" name="PeriodNum">限<br/>
授業コメント(400文字以内)
      <textarea class="textarea-grade" rows="40" cols="10" maxlength="100" name="Comment" style="  width:500px;
      height:300px;"></textarea>

        <input type="submit" value="確認画面へ">

    </form>
    
    <a href="./GradeTop"><button align="center" name="regist_top">評価メニュートップへ戻る</button></a>
  </body>
</html>