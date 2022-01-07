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
    }
    tr{
      text-align:center;
      border: 1px solid #000;
    }
    </style>
</head>
  <body>
    <a href="./SeatingTop"><button align-items="center" name="regist_stu">座席配置メニュートップへ戻る</button></a>
     <p align="right">ユーザーID　${User.id}</p>
    <h1 align="center">座席配置一覧</h1>
    <%-- <form action="./RegistStudentGet" method="post">
      <button text-align="center" name="regist_class">検索実行</button>
    </form> --%>
    <br>
   <font size="2px">検索：</font>
        <form action="./SearchSeating" method="post">
            <input type="text" name="seating_search" value="<%if(word != null){%><%=word%><%}%>"maxlength="20" minlength="1" pattern="^[ぁ-ん]+$ , [\u3041-\u309F]*+^[ァ-ンヴー]+$ , [\u30A1-\u30FF]*+[A-Za-z]"/>
            <label><input type="radio" name="radiobutton" value="class" required <%if(index.equals("class")||index ==null){%>checked<%}%>> <font size="2px">クラス</font></label>
            <label><input type="radio" name="radiobutton" value="name" <%if(index.equals("name")){%>checked<%}%>> <font size="2px">座席配置名</font></label>
            <!--<label><input type="radio" name="radiobutton" value="startdate"> <font size="2px">開始期間</font></label>
            <label><input type="radio" name="radiobutton" value="enddate"> <font size="2px">終了期間</font>-->
            <button text-align="center" name ="searchbutton" value="search_seatingArr">検索実行</button></label>
            <button text-align="center" name ="searchbutton" value="searchReset">一覧表示</button></label>
        </form>
      <br />
    自身の作成した座席配置情報<br>
    <table>
      <tr>
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
              <td><%=SeatingArrangements.getStartDate()%>~
              <%-- 終了期間や座席配置名がないときにnullではなく空白で示すようにする --%>
              <% if(SeatingArrangements.getEndDate() == null){SeatingArrangements.setEndDate("終了日未定"); } %>
              <%=SeatingArrangements.getEndDate()%></td>
              <% if(SeatingArrangements.getName() == null){SeatingArrangements.setName(""); } %>
              <td><%=SeatingArrangements.getName()%></td>
              <td>
              <form action="./manageSeatingTop" method="post">
                <input type="hidden" name="SeatingId" value="<%=SeatingArrangements.getId()%>">

                <input type="submit" value="座席配置詳細" name="hand" />
              </form></td>
            </tr>
          <% } }else{%>
            <tr><td>なし</td></tr>
          <% }%>
    </table>
    座席配置一覧<br>
    <table>
      <tr>
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
              <td><%=SeatingArrangements.getStartDate()%>~
              <% if(SeatingArrangements.getEndDate() == null){SeatingArrangements.setEndDate(""); } %>
              <%=SeatingArrangements.getEndDate()%></td>
              <% if(SeatingArrangements.getName() == null){SeatingArrangements.setName(""); } %>
              <td><%=SeatingArrangements.getName()%></td>
              <td>
              <form action="./manageSeatingTop" method="post">
                <input type="hidden" name="SeatingId" value="<%=SeatingArrangements.getId()%>">

                <input type="submit" value="座席配置詳細" name="hand" />
              </form></td>
            </tr>
          <% } }else{%>
            <tr><td>なし</td></tr>
          <% }%>
    </table>
  </body>
</html>