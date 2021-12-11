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
  <body>
  <p align="right">ID: ${User.id}</p>
    <h1 align="center">座席配置新規作成</h1>
    <br>
    <form action="../RegistSeatingClass" method="post">
    クラスを選択してください。<br>
        クラス：<select name="class">
            <% if(ClassDefList.size() > 0) {
              for(ClassDef ClassDef : ClassDefList ){ %>
              <option value="<%=ClassDef.getClass_id() %>">
              <%=ClassDef.getClass_year()%>年<%=ClassDef.getClass_name() %>
              </option>
            <% } }%></select><br />
        <br />
        <input type="submit" value="座席配置新規作成" name="registSeatingClass" />
      </form>
      <br />
      <a href="./seatingTop.jsp"><button align="center" name="regist_top">座席配置メニュートップへ戻る</button></a>
  </body>
</html>