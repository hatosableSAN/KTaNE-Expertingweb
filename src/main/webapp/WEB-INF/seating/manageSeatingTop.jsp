<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.ClassDef" %>
<%@ page import= "beans.User" %>
<%@ page import= "beans.SeatingArrangements" %>
<%@ page import= "service.ClassService" %>
<%
    List<SeatingArrangements> mySeatingArrangementsList=(List<SeatingArrangements>) request.getAttribute("mySeatingArrangementsList");
    List<SeatingArrangements> otherSeatingArrangementsList=(List<SeatingArrangements>) request.getAttribute("otherSeatingArrangementsList");
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
    <h1 align="center">座席配置一覧</h1>
    <%-- <form action="./RegistStudentGet" method="post">
      <button text-align="center" name="regist_class">検索実行</button>
    </form> --%>
    <br>
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
              <% ClassService ClassService = new ClassService();
                    ClassDef classdef = new ClassDef();
                    classdef.setClass_id(SeatingArrangements.getClassId());
                    classdef = ClassService.searchClass(classdef); %>
              <%=classdef.getClass_name()%></td>
              <%-- TODO:IDじゃなくてクラス名で表示する --%>
              <td><%=SeatingArrangements.getStartDate()%>~<%=SeatingArrangements.getEndDate()%></td>
              <td><%=SeatingArrangements.getName()%></td>
              <td>
              <form action="./ClassTop" method="post">
                <input type="hidden" name="ClassId" value="<%=SeatingArrangements.getClassId()%>">
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

              <td><%=SeatingArrangements.getClassId()%></td>
              <%-- TODO:IDじゃなくてクラス名で表示する --%>
              <td><%=SeatingArrangements.getStartDate()%>~<%=SeatingArrangements.getEndDate()%></td>
              <td><%=SeatingArrangements.getName()%></td>
              <td>
              <form action="./manageSeatingTop" method="post">
                <input type="hidden" name="ClassId" value="<%=SeatingArrangements.getClassId()%>">
                <input type="submit" value="座席配置詳細" name="hand" />
              </form></td>
            </tr>
          <% } }else{%>
            <tr><td>なし</td></tr>
          <% }%>
    </table>
  </body>
</html>