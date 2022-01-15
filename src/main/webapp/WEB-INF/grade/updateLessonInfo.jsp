<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.*" %>
<%@ page import="beans.SeatingArrangements" %>
<% SeatingArrangements Seat= (SeatingArrangements)session.getAttribute("SeatingArrangements"); %>
<% Lessons Lesson = (Lessons)session.getAttribute("Lesson"); %>


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
    変更後の授業日および授業全体のコメントを入力してください。<br>

    <form action="./UpdateLessonInfoConfirm" method="post">
      <h2 style="text-align: center;">授業日：
        <input type="date" name="ClassDate" required value=<%=Seat.getStartDate()%> min=<%=Seat.getStartDate()%> max=<%=Seat.getEndDate()%>>
        <input type="number" name="PeriodNum" value=1 required min=0>限  <font color="red">* </h2>
          <br/><p style="position: absolute;right:350px;">*は必須項目です。座席配置期間外の授業は登録できません。</font></p></br>
  <h2>授業コメント(400文字以内)</h2>
        <textarea class="textarea-grade" rows="40" cols="10" maxlength="400" name="Comment" style="  width:500px;
        height:300px;"></textarea>
<input type="hidden" value="<%=Lesson.getId()%>" name="Id">
        <input type="submit" class="btn" id="btn_right" value="確認画面へ">

    </form>
    
    <a href="./GradeTop"><button align="center" class="button_grey" id="btn_left"　name="regist_top">評価メニュートップへ戻る</button></a>
  </body>
</html>