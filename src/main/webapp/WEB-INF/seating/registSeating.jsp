<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.ClassDef" %>
<%@ page import="beans.Student" %>
<%@ page import="beans.*" %>
<%@ page import="service.StudentService" %>
<% User User = (User)session.getAttribute("User"); %>
<% List<Student> StudentList = (ArrayList<Student>) session.getAttribute("StudentList"); %>
<% ClassDef ClassDef = (ClassDef)session.getAttribute("ClassDef"); %>
<% List<StudentSeatingArr> studentSeatingArrList = (ArrayList<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/css/modal.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/modal.css">
  <style type="text/css">
    .seat {
        width: 80px;
        height: 50px;
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        background-color:#cccccf;
        text-align:center;
      }
      .setseatm {
        width: 80px;
        height: 50px;
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        background-color:blue;
        text-align:center;
      }
      .setseatf {
        width: 80px;
        height: 50px;
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        background-color:red;
        text-align:center;
      }
      .setseato {
        width: 80px;
        height: 50px;
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        background-color:gray;
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
  <p align="right">ID: ${User.id}</p>
    <h1 align="center">座席配置新規作成</h1>
    <br>

    ・座席を選択し、児童・生徒の座席を作成してください。<br>
      <form action="./RegistSeatingInfo" method="get">
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
        <input type="submit" value="座席配置を確定する" />
      </form>


        <div id="modal-content">
          <form action="./RegistSeatingStudent" method="post">
            <p class="red">「閉じる」か「背景」をクリックするとモーダルウィンドウを終了します。</p>
            生徒選択画面<br>
            生徒：<select name="StudentId">
                <option value="">なし</option>
                <% if(StudentList.size() > 0) {
                  for(Student student : StudentList ){ %>
                  <option value="<%=student.getStudent_id() %>">
                  <%=student.getStudent_id() %>:<%=student.getStudent_name()%>:<%=student.getStudent_gender()%><%=student.getStudent_user()%>
                  </option>
                <% } }%>
                </select><br /><br />
            <%-- <textarea class="textarea"></textarea> --%>
            <input type="hidden" name="seatNum" value="-1" id="seatnum">
            <p><input type="submit" value="座席を確定する" align="center" /></p>
            <%-- TODO:座席を確定するを押したら2重に送信されないようにする --%>
          </form>
            <p><a id="modal-close" class="button-link"><button align="center">閉じる</button></a></p>
        </div>

      <br />

      <% if(studentSeatingArrList!=null && studentSeatingArrList.size() > 0) {
                  for(StudentSeatingArr studentSeatingArr : studentSeatingArrList ){ //座席に登録されている生徒。
                    //座席配置されている生徒IDから生徒情報を取得
                    StudentService StudentService = new StudentService();
                    Student student = new Student();
                    student.setStudent_id(studentSeatingArr.getStudentId());
                    Student setStudent = StudentService.searchStudent(student);
                    //TODO : 数値じゃないので応急措置(今後変える)
                    if(setStudent.getStudent_gender().equals("男")){
                      setStudent.setStudent_gender("1");
                    }else if(setStudent.getStudent_gender().equals("その他")){
                      setStudent.setStudent_gender("3");
                    }else if(setStudent.getStudent_gender().equals("女")){
                      setStudent.setStudent_gender("2");
                    }
                    if(Integer.parseInt(setStudent.getStudent_gender()) == 1){//男
                    %>
                    <script>
                      $(function () {
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('setseatm');
                        $("#<%=studentSeatingArr.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %>");
                      });
                    </script>
                    <% }else if(Integer.parseInt(setStudent.getStudent_gender()) == 2){//女
                    %>
                  <script>
                      $(function () {
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('setseatf');
                        $("#<%=studentSeatingArr.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %>");
                      });
                  </script>
                    <%
                    }else if(Integer.parseInt(setStudent.getStudent_gender()) == 3){//その他
                    %>
                    <script>
                      $(function () {
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('setseato');
                        $("#<%=studentSeatingArr.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %>");
                      });
                  </script>
                    <%
                    }
                    %>
                  <%-- <%=studentSeatingArr.getSeat() %>:<%=studentSeatingArr.getStudentId() %><Br> --%>
                <% } }%>
      <a href="./SeatingTop"><button align="center" name="regist_top">座席配置メニュートップへ戻る</button></a>
  </body>
</html>