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
<% SeatingArrangements seatingArrangements = (SeatingArrangements)request.getAttribute("SeatingArrangements"); %>
<% List<Student> StudentList = (ArrayList<Student>) request.getAttribute("StudentList"); %>
<% ClassDef ClassDef = (ClassDef)request.getAttribute("ClassDef"); %>
<% List<StudentSeatingArr> studentSeatingArrList = (ArrayList<StudentSeatingArr>) request.getAttribute("StudentSeatingArrList"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <%-- <script type="text/javascript" src="<%=request.getContextPath()%>/css/modal.js"></script> --%>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/modal.css">


    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >

 </head>
  <body>

    <div class="blue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">座席配置詳細閲覧</h1>
    </div>

      <a href="./manageSeatingTop"><button align="center" class = "backbtn" name="regist_top">座席配置一覧へ戻る</button></a>

    <br><br/>
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

        <div class="right_div">
        クラス：<!--<%=seatingArrangements.getClassId() %>:--> <%=ClassDef.getClass_year()%>年<%=ClassDef.getClass_name() %><br>
        期間：<%=seatingArrangements.getStartDate() %>～
        <%-- 終了期間や座席配置名がないときにnullではなく空白で示すようにする --%>
              <% if(seatingArrangements.getEndDate() == null){seatingArrangements.setEndDate("終了日未定"); } %>
        <%=seatingArrangements.getEndDate() %>
        <Br>
        席名：
        <% if(seatingArrangements.getName() == null){seatingArrangements.setName(""); } %>
        <%=seatingArrangements.getName()%>
        </div>

        <% if(seatingArrangements.getUserId().equals(User.getId())){ %>

        <form formmethod="POST" method="post">
          <p align = "right"><input type="hidden" name="SeatingId" value="<%=seatingArrangements.getId() %>">
          <input type="submit"  class = "button button_grey" id="btn_left_seat" name="deleteSeating" value="座席配置削除" formaction="./DeleteSeatingconfirm" style="color:red;"/>
          <input type="submit" class = "button" id="btn_right_seat2" name="updateSeating" value="座席配置変更" formaction="./UpdateSeating"/>　　　</p>
        </form>

        <% } %>
        <form action="./RegistGradeInfo" method="post">
        <input type="hidden" name="id" value="<%=seatingArrangements.getId() %>" >
        <input type="submit" value="授業評価作成" name="registGrade" class="button" id="btn_right_seat"/>
        </form>

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
                    //  setStudent.setStudent_gender("1");
                    //}else if(setStudent.getStudent_gender().equals("その他")){
                    //  setStudent.setStudent_gender("3");
                    //}else if(setStudent.getStudent_gender().equals("女")){
                    //  setStudent.setStudent_gender("2");}
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