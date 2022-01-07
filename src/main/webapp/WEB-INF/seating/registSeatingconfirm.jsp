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
<% SeatingArrangements seatingArrangements = (SeatingArrangements)session.getAttribute("SeatingArrangements"); %>
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
        background-color:#fcc490;
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
        background-color:rgb(240, 134, 134);
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
        background-color:#fcc490;
        text-align:center;
        margin-left: auto;
        margin-right: auto;
      }
      .right_div{/*座席配置情報を教卓の横に置くクラス*/
        width: 260px;                /* 横幅を900pxに指定 */
        height: 90px;               /* 横幅を230pxに指定 */
        right: 20px;
        top: 400px;
        position: fixed;
        /*align-items: flex-end;*/
      }

    </style>
<link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >

 </head>
  <body>
    <div class="blue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">座席配置新規作成確認</h1>
    </div>
    <a href="./RegistSeatingInfo"><button align="center" class="button_grey" id="btn_left" style="position:fixed" name="regist_top">入力画面へ戻る</button></a>
    <br>

    <div class="center">以下の情報で登録します</div>
      <form action="./RegistSeatingAll" method="post">
        <table align="center">
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
        <div class="right_div">
        クラス：<%=seatingArrangements.getClassId() %>: <%=ClassDef.getClass_year()%>年<%=ClassDef.getClass_name() %><br>
        期間：<%=seatingArrangements.getStartDate() %>～<%=seatingArrangements.getEndDate() %>
        <Br>
        席名：<%=seatingArrangements.getName()%><br/></div>
        <input type="submit" class = "btn" id="btn_right" style="position: fixed;" value="座席配置を登録">

      <br />


      <% if(studentSeatingArrList!=null && studentSeatingArrList.size() > 0) {
                  for(StudentSeatingArr studentSeatingArr : studentSeatingArrList ){ //座席に登録されている生徒。
                    //座席配置されている生徒IDから生徒情報を取得
                    StudentService StudentService = new StudentService();
                    Student student = new Student();
                    student.setStudent_id(studentSeatingArr.getStudentId());
                    Student setStudent = StudentService.searchStudent(student);
                    //TODO : 数値じゃないので応急措置(今後変える)
                    //if(setStudent.getStudent_gender().equals("男")){
                     // setStudent.setStudent_gender("1");
                    //}else if(setStudent.getStudent_gender().equals("その他")){
                    //  setStudent.setStudent_gender("3");
                    //}else if(setStudent.getStudent_gender().equals("女")){
                    //  setStudent.setStudent_gender("2");
                    //}
                    if(setStudent.getStudent_gender() == 1){//男
                    %>
                    <script>
                      $(function () {
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('setseatm');
                        $("#<%=studentSeatingArr.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %>");
                      });
                    </script>
                    <% }else if(setStudent.getStudent_gender() == 2){//女
                    %>
                  <script>
                      $(function () {
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('setseatf');
                        $("#<%=studentSeatingArr.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %>");
                      });
                  </script>
                    <%
                    }else if(setStudent.getStudent_gender() == 3){//その他
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
  </body>
</html>