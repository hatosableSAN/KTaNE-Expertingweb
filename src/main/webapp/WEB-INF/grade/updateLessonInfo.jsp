<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.*" %>
<%@ page import="beans.SeatingArrangements" %>
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
授業日：
      <input type="date" name="ClassDate" value="<%=Lesson.getLessonDate()%>"required>
      <input type="text" name="PeriodNum" value ="<%=Lesson.getPeriodnum()%>"required>限<br/><font color="red">* </h2>
        <br/><p style="position: absolute;right:300px;">*は必須項目です</font>
授業コメント(400文字以内)
      <textarea class="textarea-grade" rows="40" cols="10" maxlength="400" name="Comment" style="  width:500px;
      height:300px;"><%=Lesson.getComment()%></textarea>
<input type="hidden" value="<%=Lesson.getId()%>" name="Id">
        <input type="submit" class="btn" id="btn_right" value="確認画面へ">

    </form>
    
    <a href="./GradeTop"><button align="center" class="button_grey" id="btn_left"　name="regist_top">評価メニュー<br/>トップへ戻る</button></a>
  </body>
</html>