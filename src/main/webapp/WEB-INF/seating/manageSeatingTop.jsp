<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.ClassDef" %>
<%@ page import= "beans.User" %>
<%@ page import= "beans.SeatingArrangements" %>
<%@ page import= "service.ClassService" %>

<% User User = (User)session.getAttribute("User"); %>
<%
    List<SeatingArrangements> mySeatingArrangementsList=(List<SeatingArrangements>) request.getAttribute("mySeatingArrangementsList");
    List<SeatingArrangements> otherSeatingArrangementsList=(List<SeatingArrangements>) request.getAttribute("otherSeatingArrangementsList");
    String word = (String) request.getAttribute("Word");
    String index = (String)request.getAttribute("index");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
    table{
      width:80%;
      border-collapse:collapse;
     border: 1px solid #000;      /* わかりやすくボーダーを引く */
     /*overflow-y: scroll;          /* 縦方向にスクロール可能にする */
    }tr{
      text-align:left;
      border: 1px solid #000;
    }
    th,td{
      padding-top:10px;
      padding-bottom:10px;
      padding-left:30px;
      padding-right:10px;
    }
    .text{
      margin-left:8%;
    }
    .bar{
      background-color: #F8AB74;
    }
    </style>
  <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >

</head>
  <body>
    <div class="blue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">座席配置一覧</h1>
    </div>

    <a href="./SeatingTop"><button align-items="center" class = "backbtn" name="regist_stu">座席配置メニュー<br/>トップへ戻る</button></a>

    <%-- <form action="./RegistStudentGet" method="post">
      <button text-align="center" class="button" name="regist_class">検索実行</button>
    </form> --%>
    <br>

    <p class="text" style="display: inline; margin-right:30px;">自身の作成した座席配置情報</p>
    <font size="2px">検索：</font>
        <form action="./SearchSeating" method="post" style="display: inline">
            <input type="text" name="seating_search" value="<%if(word != null){%><%=word%><%}%>"maxlength="20" minlength="1" pattern="[ぁ-んァ-ヶｦ-ﾟ一-龠a-zA-Z0-9\s\-\u30FC]+"/>
            <label><input type="radio" name="radiobutton" value="class" required <%if(index.equals("class")||index ==null){%>checked<%}%>> <font size="2px">クラス</font></label>
            <label><input type="radio" name="radiobutton" value="name" <%if(index.equals("name")){%>checked<%}%>> <font size="2px">座席配置名</font></label>
            <label><input type="radio" name="radiobutton" value="startdate"<%if(index.equals("startdate")||index ==null){%>checked<%}%>><font size="2px">開始期間(yyyy/mm/dd) </font></label>
            <label><input type="radio" name="radiobutton" value="enddate"<%if(index.equals("enddate")||index ==null){%>checked<%}%>> <font size="2px">終了期間(yyyy/mm/dd)</font></label>
            <button text-align="center" name ="searchbutton" value="search_seatingArr" class="button" style="width: 70px; height:30px; padding:0px;">検索実行</button></label>
            <button text-align="center" name ="searchbutton" value="searchReset" class="button" style="width: 70px; height:30px; padding:0px;">一覧表示</button></label>
        </form>
      <br>
    <table>
      <tr class="bar">
        <th>クラス</th>
        <th>開始期間~終了期間</th>
        <th>座席配置名</th>
        <th></th>
      </tr>

          <% if(mySeatingArrangementsList.size() > 0) {
            for(SeatingArrangements SeatingArrangements : mySeatingArrangementsList){ %>
            <tr>
              <td>
              <%-- IDじゃなくてクラス名で表示する --%>
              <% ClassService ClassService = new ClassService();
                    ClassDef classdef = new ClassDef();
                    classdef.setClass_id(SeatingArrangements.getClassId());
                    classdef = ClassService.searchClass(classdef); %>
              <%=classdef.getClass_name()%></td>
              <td><%=SeatingArrangements.getStartDate().substring(0, SeatingArrangements.getStartDate().length() - 9)%>~
              <%-- 終了期間や座席配置名がないときにnullではなく空白で示すようにする --%>
              <% if(SeatingArrangements.getEndDate() == null){SeatingArrangements.setEndDate("終了日未定");%><%=SeatingArrangements.getEndDate()%><% }else{ %>
              <%=SeatingArrangements.getEndDate().substring(0, SeatingArrangements.getEndDate().length() - 9)%><%}%></td>
              <% if(SeatingArrangements.getName() == null){SeatingArrangements.setName(""); } %>
              <td><%=SeatingArrangements.getName()%></td>
              <td>
              <form action="./manageSeatingTop" method="post">
                <input type="hidden" name="SeatingId" value="<%=SeatingArrangements.getId()%>">

                <input type="submit" class="button" value="座席配置詳細" name="hand"/>
              </form></td>
            </tr>
          <% } }else{%>
            <tr><td>なし</td></tr>
          <% }%>
    </table><br>
    <p class="text">座席配置一覧</p><br>
    <table style="margin-bottom:30px;">
      <tr class="bar">
        <th>クラス</th>
        <th>開始期間~終了期間</th>
        <th>座席配置名</th>
        <th></th>
      </tr>
        <% if(otherSeatingArrangementsList.size() > 0) {
            for(SeatingArrangements SeatingArrangements : otherSeatingArrangementsList ){ %>
            <tr>
              <td>
              <% ClassService ClassService = new ClassService();
                    ClassDef classdef = new ClassDef();
                    classdef.setClass_id(SeatingArrangements.getClassId());
                    classdef = ClassService.searchClass(classdef); %>
              <%=classdef.getClass_name()%></td>
              <%-- TODO:IDじゃなくてクラス名で表示する --%>
              <td><%=SeatingArrangements.getStartDate().substring(0, SeatingArrangements.getStartDate().length() - 9)%>~
              <% if(SeatingArrangements.getEndDate() == null){SeatingArrangements.setEndDate("終了日未定");%><%=SeatingArrangements.getEndDate()%><% }else{ %>
              <%=SeatingArrangements.getEndDate().substring(0, SeatingArrangements.getEndDate().length() - 9)%><%}%></td>
              <% if(SeatingArrangements.getName() == null){SeatingArrangements.setName(""); } %>
              <td><%=SeatingArrangements.getName()%></td>
              <td>
              <form action="./manageSeatingTop" method="post">
                <input type="hidden" name="SeatingId" value="<%=SeatingArrangements.getId()%>">

                <input type="submit" class="button" value="座席配置詳細" name="hand" />
              </form></td>
            </tr>
          <% } }else{%>
            <tr><td>なし</td></tr>
          <% }%>
    </table>
  </body>
</html>