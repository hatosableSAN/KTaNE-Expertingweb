<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.Lessons" %>
<% User User = (User)session.getAttribute("User"); %>
<% List<Lessons> LessonList = (List<Lessons>)session.getAttribute("LessonList"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
    <style>


        th{background-color:#F8AB74;}
      </style>
  </head>
  <body>
    <div class="darkblue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">授業評価一覧</h1>
    </div>
    <br>
  <br>
  <font size="2px">検索：</font>
  <div style="display:inline-flex">
  <form action="./SearchLessons" method="post">
    <input type="text" name="searchword" maxlength="20" minlength="1" pattern="[ぁ-んァ-ヶｦ-ﾟ一-龠a-zA-Z0-9\-\u30FC]+"/>
    <input type="radio" name="type" value="date" checked> <font size="2px">授業日(yyyy/mm/dd)</font>
    <input type="radio" name="type" value="comment"> <font size="2px">授業コメント</font>
    <input type="hidden" value="search" name="button">
    <button text-align="center" name="show_class" class="button" style="width: 70px; height:30px; margin: 5px;padding: 0px;">検索実行</button>
</form>
<form action="./SearchLessons" method="post">
  <input type="hidden" value="all" name="button">
  <button text-align="center" name="regist_class" class="button" style="width: 70px; height:30px; margin:5px; margin: 5px;padding: 0px;">一覧表示</button>
  </form>
  </div>
  <div class="student">
    <table border="1" style="border-collapse: collapse;width: 800px;text-align: center;padding: 100;">
      <tr>
        <th>授業日</th>
        <th>時限</th>
        <th>授業コメント</th>

    </tr>
      <% if(LessonList.size() > 0&&LessonList!=null) {
        for(Lessons Lesson : LessonList ){ %>
 
        <tr>
            
            <td><%=Lesson.getLessonDate() %></td>
            <td><%=Lesson.getPeriodnum()%></td>
            <td><%=Lesson.getComment()%></td>
            <td><form action="./ShowLessonGrades" method="post">
              <input type="hidden" name="id" value=<%=Lesson.getId() %> >
              <input type="hidden" name="LessonDate" value=<%=Lesson.getLessonDate() %> >
              <input type="hidden" name="Comment" value=<%=Lesson.getComment() %> >
            <input type="submit" class="btn"value="評価閲覧" ></form></td>
      <% } }else{ %>
        <tr><td></td><td align="center">検索結果に当てはまる児童が</br>いませんでした</td></tr>
    <% }%>
    </table>
  </div>
      <br/>
      <a href="./GradeTop"><button align="center" class="backbtn" name="regist_top">評価メニュートップへ戻る</button></a>
  </body>
</html>
