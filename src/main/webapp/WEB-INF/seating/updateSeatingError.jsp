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
<% List<Student> setStudentList = (ArrayList<Student>) session.getAttribute("setStudentList"); %>
<% ClassDef ClassDef = (ClassDef)session.getAttribute("ClassDef"); %>
<% List<StudentSeatingArr> studentSeatingArrList = (ArrayList<StudentSeatingArr>) session.getAttribute("StudentSeatingArrList"); %>
<% SeatingArrangements seatingArrangements = (SeatingArrangements) session.getAttribute("SeatingArrangements"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/css/modal.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/modal.css">

    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
 </head>
  <body>
    <div class="blue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">座席配置変更</h1>
    </div>
    <br>

    ・座席を選択し、児童・生徒の座席を作成してください。<br>
      <form>
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
        <div class="right_div">
        クラス： <%=ClassDef.getClass_year()%>年<%=ClassDef.getClass_name() %><br>
        期間：<input type="date" value="<%=seatingArrangements.getStartDate()%>" name="startdate"  value="<%=ClassDef.getClass_year()%>-04-01" min="<%=ClassDef.getClass_year()%>-04-01" max="<%=ClassDef.getClass_year()+1%>-03-31" required><font color="red">＊</font>
        ～
        <input type="date" value="<%if(seatingArrangements.getEndDate() != null){%><%=seatingArrangements.getEndDate()%><% }%>" name="enddate" min="<%=ClassDef.getClass_year()%>-04-01" max="<%=ClassDef.getClass_year()+1%>-03-31">
        <Br>
        席名：<input type="text" value="<%=seatingArrangements.getName()%>"name="seatname" placeholder="(1~20文字)" maxlength="20" minlength="1" pattern="^[ぁ-ん]+$ , [\u3041-\u309F]*+^[ァ-ンヴー]+$ , [\u30A1-\u30FF]*+[A-Za-z],[0-9A-Za-z]+$"/><br>
        <font color="red">＊座席配置開始期間の入力は必須です</font>
      </div>
        <input type="submit" class="btn" id="btn_right_seat" formaction="./UpdateSeatingInfo" formmethod="POST" value="座席配置を確定する" />


        <div id="modal-content">
            <p class="red">「閉じる」か「背景」をクリックするとモーダルウィンドウを終了します。</p>
            生徒選択画面<br>
            生徒：<select name="StudentId">
                <option value="">なし</option>
                <% if(StudentList.size() > 0) {
                  for(Student student : StudentList ){ %>
                  <option value="<%=student.getStudent_id() %>">
                  <%=student.getStudent_id() %>:<%=student.getStudent_name()%>:<%switch(student.getStudent_gender()){case 1:%>男<%break;case 2:%>女<%break;case 3:%>その他<%break;}%><!--:<%=student.getStudent_user()%>-->
                  </option>
                <% } }%>
                </select><br /><br />
            <%-- <textarea class="textarea"></textarea> --%>
            <input type="hidden" name="seatNum" value="-1" id="seatnum">
            <p><input type="submit" formaction="./UpdateSeatingStudent" formmethod="POST" value="座席を確定する" align="center" /></p>
            <%-- TODO:座席を確定するを押したら2重に送信されないようにする --%>

            <p><a id="modal-close" class="button-link"><button align="center">閉じる</button></a></p>
        </div>
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
                    // if(setStudent.getStudent_gender().equals("男")){
                    //  setStudent.setStudent_gender("1");
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
                <% } } %>
                <%-- 座っていない生徒<br> --%>
                <%-- <%  for(Student nosetstudent : StudentList ){ %> --%>
                  <%-- <%=nosetstudent.getStudent_id()%>:<%=nosetstudent.getStudent_name()%><br> --%>
                <%-- <% } %> --%>
                <%-- 座っている生徒<br> --%>
                <%-- <%  for(Student setstudent : setStudentList ){ %> --%>
                  <%-- <%=setstudent.getStudent_id()%>:<%=setstudent.getStudent_name()%><br> --%>
                <%-- <% } %> --%>
      <a href="./manageSeatingTop"><button align="center" class="button_grey" id="btn_left_seat" name="regist_top">キャンセル</button></a>
  </body>
</html>