<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.ClassDef" %>
<%
    List<ClassDef> myClassDefList=(List<ClassDef>) request.getAttribute("myClassDefList");
    List<ClassDef> otherClassDefList=(List<ClassDef>) request.getAttribute("otherClassDefList");
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
    <!--h1 align="center">Hello SE21G1!</h1-->
    <h1 align="center">クラスメニュートップ</h1>
    <form action="./RegistStudentGet" method="post">
      <button text-align="center" name="regist_class">クラス新規登録</button>
    </form>
    <br>
    自身の作成したクラス<br>
    <table>
      <tr>
        <th>年度</th>
        <th>クラス名</th>
      </tr>

          <% if(myClassDefList.size() > 0) {
            for(ClassDef ClassDef : myClassDefList){ %>
            <tr>
              <td><%=ClassDef.getClass_year()%></td>
              <td><%=ClassDef.getClass_name()%></td>
              <%-- <td><%=ClassDef.getClass_user()%></td> --%>
              <td>
              <form action="./ClassTop" method="post">
                <input type="hidden" name="ClassId" value="<%=ClassDef.getClass_id()%>">
                <input type="submit" value="クラス情報詳細表示" name="hand" />
              </form></td>
            </tr>
          <% } }%>
    </table>
    クラス一覧<br>
    <table>
      <tr>
        <th>年度</th>
        <th>クラス名</th>
      </tr>
        <% if(otherClassDefList.size() > 0) {
            for(ClassDef ClassDef : otherClassDefList ){ %>
            <tr>
              <td><%=ClassDef.getClass_year()%></td>
              <td><%=ClassDef.getClass_name()%></td>
              <%-- <td><%=ClassDef.getClass_user()%></td> --%>
              <td>
              <form action="./ClassTop" method="post">
                <input type="hidden" name="ClassId" value="<%=ClassDef.getClass_id()%>">
                <input type="submit" value="クラス情報詳細表示" name="hand" />
              </form></td>
              </tr>
          <% } }%>
    </table>


    <a href="../WEB-INF/Users/systemTop.jsp"><button align-items="center" name="regist_stu">トップへ戻る</button></a>
    <!--input type="button" value="新規登録" align="center" name="regist_stu"-->
  </body>
</html>