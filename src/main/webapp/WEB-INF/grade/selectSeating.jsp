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
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
  <body>
    <div class="darkblue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">授業評価新規作成</h1>
    </div>
    <a href="./GradeTop" class="backbtn">評価メニュートップへ戻る</a>

    <br>
    　座席配置を選択してください。<br>
    <table border="1">
      <tr>
        <th>クラス</th>
        <th>開始期間</th>
        <th>終了期間</th>
        <th>クラス名</th>
    </tr>
      <% if(SeatList.size() > 0) {
        for(SeatingArrangements Seating : SeatList ){ %>
 
        <tr>
            
            <td>クラスです</td>
            <td><%=Seating.getStartDate() %></td>
            <td><%=Seating.getEndDate()%></td>
            <td><%=Seating.getName() %></td>
            <td><form action="./RegistGradeInfo" method="post">
              <input type="hidden" name="id" value=<%=Seating.getId() %> >
            <input type="submit" value="選択" ></form></td>
      <% } }%>
    </table>
      <br/>
  </body>
</html>