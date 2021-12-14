<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.ClassDef" %>
<%@ page import="beans.Student" %>
<%@ page import="beans.*" %>
<% User User = (User)session.getAttribute("User"); %>
<% SeatingArrangements seatingArrangements = (SeatingArrangements)request.getAttribute("SeatingArrangements"); %>
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

    ・以下の情報で登録します<br>
      <form action="./RegistSeatingAll" method="post">
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
        <br>
        クラス：<%=seatingArrangements.getClassId() %>: <%=ClassDef.getClass_year()%>年<%=ClassDef.getClass_name() %><br>
        期間：<%=seatingArrangements.getStartDate() %>～<%=seatingArrangements.getEndDate() %>
        <Br>
        席名：<%=seatingArrangements.getName()%>
        <input type="submit" value="座席配置を登録" name="registSeatingClass" />
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
      <a href="./RegistSeatingInfo"><button align="center" name="regist_top">入力画面へ戻る</button></a>
  </body>
</html>