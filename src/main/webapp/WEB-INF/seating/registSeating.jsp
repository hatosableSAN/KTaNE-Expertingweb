<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.ClassDef" %>
<%@ page import="beans.Student" %>
<% User User = (User)session.getAttribute("User"); %>
<% List<Student> StudentList = (ArrayList<Student>) request.getAttribute("StudentList"); %>
<% ClassDef ClassDef = (ClassDef)request.getAttribute("ClassDef"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <script type="text/javascript" src="/seating/modal.js"></script>
  <link rel="stylesheet" type="text/css" href="/seating/modal.css">
  <style type="text/css">
    .seat {
        width: 80px;
        height: 50px;
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        background-color:#cccccf;
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
    <form action="../RegistSeatingClass" method="post">
    ・座席を選択し、児童・生徒の座席を作成してください。<br>

        <table>
          <%for(int k = 0;k<7;k++) {%>
          <tr>
          <%for(int i = 0;i<3;i++) {%>
            <td class="left"><div class="seat">空座席</div></td>
            <td><div class="seat">空座席</div></td>
            <%}%>
          </tr>
          <%}%>
          <tr>
            <td colspan="6" class="left"><div class="kyoutaku">教卓</div></td>
          </tr>
        </table>
        <br />
        クラス： <%=ClassDef.getClass_year()%>年<%=ClassDef.getClass_name() %><br>
        <input type="submit" value="座席配置を確定する" name="registSeatingClass" />
      </form>
      <div id="modal-content">
        <p class="red">「閉じる」か「背景」をクリックするとモーダルウィンドウを終了します。</p>
        生徒選択画面<br>
        生徒：<select name="StudentId">
            <% if(StudentList.size() > 0) {
              for(Student student : StudentList ){ %>
              <option value="<%=student.getStudent_id() %>">
              <%=student.getStudent_id() %>:<%=student.getStudent_name()%> <%=student.getStudent_gender()%><%=student.getStudent_user()%>
              </option>
            <% } }%></select><br />
        <br />
      </form>
        <textarea></textarea>
        <p><a id="modal-close" class="button-link"><button align="center">閉じる</button></a>
        <a id="modal-close" class="button-link"><button align="center">座席を確定する</button></a></p>
      </div>
      <br />
      <a href="./seatingTop.jsp"><button align="center" name="regist_top">座席配置メニュートップへ戻る</button></a>
  </body>
</html>