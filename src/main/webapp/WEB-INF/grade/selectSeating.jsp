<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.SeatingArrangements" %>
<% User User = (User)session.getAttribute("User"); %>
<% List<SeatingArrangements> SeatList = (List<SeatingArrangements>)request.getAttribute("SeatList"); %>
  <% List<String> ClassNameList = (List<String>)request.getAttribute("ClassNameList"); %>
    <%@ page import="java.text.SimpleDateFormat"%>
    <% SimpleDateFormat inputdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
    <% SimpleDateFormat outputdf = new SimpleDateFormat("MM月dd日");%>
    <% SimpleDateFormat outputlessondf = new SimpleDateFormat("yyyy年MM月dd日");%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
<style>th{background-color:#F8AB74;}</style>
  <body>
    <div class="darkblue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">授業評価新規作成</h1>
    </div>
    <a href="./GradeTop" class="backbtn">評価メニュートップへ戻る</a>


    <br>
    　座席配置を選択してください。<br>
    <table border="1" style="border-collapse: collapse;width: 800px;text-align: center;">
      <tr>
        <th>クラス</th>
        <th colspan="3">開始期間　終了期間</th>
        <th>クラス名</th>
        <th></th>
        
    </tr>
      <% if(SeatList.size() > 0) {
        int i=0;
        for(SeatingArrangements Seating : SeatList ){ %>
 
        <tr>
            
            <td><%=ClassNameList.get(i)%></td>
            <td colspan="3" ><%=outputdf.format(inputdf.parse(Seating.getStartDate()))%>~
              <% if(Seating.getEndDate()!=null){ %>
                <%=outputdf.format(inputdf.parse(Seating.getEndDate()))%>
                <% }else{%>
                  終了日未定
                <% }%>
            <td><%=Seating.getName() %></td>
            <td style="padding-left: 10px;padding-right: 10px;"><form action="./RegistGradeInfo" method="post">
              <input type="hidden" name="id" value=<%=Seating.getId()%>>
            <input type="submit" value="選択" class="btn" style="height: 10%;padding: 5px;"></form></td>
      <% i++;} }%>
    </table>
      <br/>
  </body>
</html>

