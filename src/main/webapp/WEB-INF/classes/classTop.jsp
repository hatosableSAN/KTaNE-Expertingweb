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
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
<style type="text/css">
    table{
      width:80%;
      border-collapse:collapse;
     border: 1px solid #000;      /* わかりやすくボーダーを引く */
     /*overflow-y: scroll;          /* 縦方向にスクロール可能にする */
    }
    tr{
      text-align:left;
      border: 1px solid #000;
    }
    th,td{
      padding-top:10px;
      padding-bottom:10px;
      padding-left:30px;
      padding-right:10px;
    }
    p{
      margin-left:8%;
    }
    </style>
</head>
  <body>
    <div class="rightblue">
     <p align="right">ユーザーID　${User.id}</p>
     <h1 align="center">クラスメニュートップ</h1>
    </div>
    <form action="./RegistStudentGet" method="post">
      <button text-align="center" name="regist_class" class="btn" style="font-size: 15px; position:absolute; top:110px; right:30px; width:130px;">クラス新規登録</button>
    </form>
    <br>
    <p>自身の作成したクラス</p><br>
    <table align="center">
      <tr>
        <th style="width:200px;">年度</th>
        <th style="width:800px;">クラス名</th>
        <th style="width:200px;"></th>
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
                <input type="submit" value="クラス情報詳細表示" name="hand" class="button" >
              </form></td>
            </tr>
          <% } }else{%>
            <tr>
              <td>なし</td>
              <td></td>
              <td></td>
            </tr>
          <% }%>
    </table>
    <br><p>クラス一覧</p><br>
    <table align="center"  style="margin-bottom:30px;">
      <tr>
        <th style="width:200px;">年度</th>
        <th style="width:800px;">クラス名</th>
        <th style="width:200px;"></th>
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
                <input type="submit" value="クラス情報詳細表示" name="hand" class="button">
              </form></td>
              </tr>
          <% } }else{%>
            <tr>
              <td>なし</td>
              <td></td>
              <td></td>
            </tr>
          <% }%>
    </table>


    <a href="./SystemTop"><button name="gotop" class="backbtn">トップへ戻る</button></a>
    <!--input type="button" value="新規登録" align="center" name="regist_stu"-->
  </body>
</html>