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
  <body>
  <p align="right">ID: ${User.id}</p>
    <h1 align="center">座席配置新規作成</h1>
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
            <input type="submit" value="選択" ></form></td>
      <% } }%>
    </table>
      <br/>
      <a href="./SeatingTop"><button align="center" name="regist_top">座席配置メニュートップへ戻る</button></a>
  </body>
</html>