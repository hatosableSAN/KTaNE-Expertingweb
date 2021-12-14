<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.ClassDef" %>
<%@ page import="beans.Student" %>
<%@ page import="beans.*" %>
<% User User = (User)session.getAttribute("User"); %>
<% List<Student> StudentList = (ArrayList<Student>) session.getAttribute("StudentList"); %>
<% ClassDef ClassDef = (ClassDef)session.getAttribute("ClassDef"); %>
<% List<StudentSeatingArr> studentSeatingArrList = (ArrayList<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <%-- <script type="text/javascript" src="<%=request.getContextPath()%>/css/modal.js"></script> --%>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/modal.css">
  <style type="text/css">
    .seat {
        width: 80px;
        height: 50px;
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        background-color:#cccccf;
        text-align:center;
      }
      .setseat {
        width: 80px;
        height: 50px;
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        background-color:red;
        text-align:center;
      }
      .left{
        padding-left:40px;
      }
      .kyoutaku{
        width: 80px;
        height: 50px;
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        background-color:#cccccf;
        text-align:center;
        margin-left: auto;
        margin-right: auto;
      }

    </style>
 </head>
  <body>
  <%-- <p align="right">ID: ${User.id}</p> --%>
    <h1 align="center">座席配置新規作成</h1>
    <br>

    ・児童・生徒の席を以下に確定しました。期間・席名を入力し、登録してください。<br>
      <form action="./RegistSeatingInfo" method="post">
        <table>
          <%
          int j = 0;
          for(int k = 0;k<7;k++) {%>
          <tr>
          <%for(int i = 0;i<3;i++) {%>
            <td class="left"><div class="seat" id="<%=j++%>">空座席</div></td>
            <td><div class="seat" id="<%=j++%>">空座席</div></td>
            <%}%>
          </tr>
          <%}%>
          <tr>
            <td colspan="6" class="left"><div class="kyoutaku">教卓</div></td>
          </tr>
        </table>
        <br />
        クラス： <%=ClassDef.getClass_year()%>年<%=ClassDef.getClass_name() %><br>
        期間：<input type="date" name="startdate"  value="<%=ClassDef.getClass_year()%>-04-01" min="<%=ClassDef.getClass_year()%>-04-01" max="<%=ClassDef.getClass_year()+1%>-03-31" required><font color="red">＊</font>
        ～
        <input type="date" name="enddate" min="<%=ClassDef.getClass_year()%>-04-01" max="<%=ClassDef.getClass_year()+1%>-03-31">
        <Br>
        席名：<input type="text" name="seatname" placeholder="(1~20文字)" maxlength="20" minlength="1" pattern="^[ぁ-ん]+$ , [\u3041-\u309F]*+^[ァ-ンヴー]+$ , [\u30A1-\u30FF]*+[A-Za-z],[0-9A-Za-z]+$"/>

        <input type="submit" value="座席配置登録" name="registSeatingClass" />
        </form>

      <br />

      <% if(studentSeatingArrList!=null && studentSeatingArrList.size() > 0) {
                  for(StudentSeatingArr studentSeatingArr : studentSeatingArrList ){ %>
                  <%=studentSeatingArr.getSeat() %>:<%=studentSeatingArr.getStudentId() %><Br>
                  </option>
                  <script>
                  $(function () {
                    $("#<%=studentSeatingArr.getSeat() %>").addClass('setseat');
                  });
                  </script>
                <% } }%>
      <a href="./RegistSeatingStudent"><button align="center" name="regist_top">座席配置画面へ戻る</button></a>
  </body>
</html>