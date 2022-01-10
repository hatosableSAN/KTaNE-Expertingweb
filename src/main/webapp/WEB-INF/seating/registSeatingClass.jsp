<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.ClassDef" %>
<% User User = (User)session.getAttribute("User"); %>
<% List<ClassDef> ClassDefList = (ArrayList<ClassDef>)request.getAttribute("ClassDefList"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
  <body>
    <div class="blue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">座席配置新規作成</h1>
    </div>
    <a href="./SeatingTop"><button align="center" name="regist_top" class = "backbtn">座席配置メニュー<br/>トップへ戻る</button></a>
    <br>
    <form action="./RegistSeatingClass" method="post">
    　クラスを選択してください。<br>
    <div class="center">
        クラス：<select name="classId">
            <% if(ClassDefList.size() > 0) {
              for(ClassDef ClassDef : ClassDefList ){ %>
              <option value="<%=ClassDef.getClass_id() %>">
              <!--<%=ClassDef.getClass_id() %>:--><%=ClassDef.getClass_year()%>年<%=ClassDef.getClass_name() %>
              </option>
            <% } }%></select><br />
        <br />
        <% if(ClassDefList.size() > 0){%>
        <p align="right"><input type="submit" value="座席配置新規作成" class = "btn"/></p>
        <%}else{%>
        <font color='red'>※先にクラスを登録してください。</font>
        <p align="right"><input type="submit" value="座席配置新規作成" class = "btn" disabled></p>
        <%}%>
      </form>
    </div>
      <br />
  </body>
</html>