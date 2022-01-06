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
<% List<Grade> GradeList = (List<Grade>) session.getAttribute("Grade"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/css/modal_grade.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/modal.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
  <style type="text/css">
    .seatblank {
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
        background-color:#4088CA;
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
      .selectable{
      border-radius: 5px;
      background-color: rgb(255, 218, 0);
      padding: 10px;
      font-size: 20px;
      color: black;
      font:bold;
      }
      .no-selectable{
      border-radius: 5px;
      background-color: rgb(240, 240, 240);
      padding: 10px;
      text-decoration: none;
      color: rgb(172, 172, 172);
      font-size: 20px;
      font:bold;
      }
      .registed {
        width: 80px;
        height: 50px;
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        background-color:rgb(71, 255, 101);
        
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
      .h1-bottom {
              color: rgb(255, 0, 0);
            }
    </style>
    <!--  ここまでスタイル  -->

 </head>
  <body>
  <p align="right">ID: ${User.id}</p>
    <h1 align="center">評価新規作成</h1>
    <br>


    ・評価を登録する座席を選択してください。<br>
      <form action="./RegistClassesInfo" method="post" class="form-grade">
        <table>
          <%
          int j = 0;
          for(int k = 0;k<7;k++) {%>
          <tr>
          <%for(int i = 0;i<3;i++) {%>
            <td class="left"><div class="seatblank" id="<%=j++%>">空座席</div></td>
            <td><div class="seatblank" id="<%=j++%>">空座席</div></td>
            <%}%>
          </tr>
          <%}%>
          <tr>
            <td colspan="6" class="left"><div class="kyoutaku">教卓</div></td>
          </tr>
        </table>
        <br />
        <input type="submit" value="座席配置を確定する" id="submitbtn" class="selectable">
      </form>

<!-- モーダルはここから -->
        <div id="modal-content-grade">
          <form action="./RegistSeatingStudentGrade" method="post">
            「閉じる」か「背景」をクリックするとウィンドウを終了します。<br/>
            個人評価入力画面<br>
          
            <script type="text/javascript">
              function ShowHideDiv() {
              var attend = document.getElementById("attend");
              var dvPassport = document.getElementById("dvPassport");
              dvPassport.style.display = attend.checked ? "block" : "none";
              }
             </script>
             <span>出席・欠席を選択してください。</span>
             <label for="attend">
              <input type="radio" id="attend" name="attendance" onclick="ShowHideDiv()" value="true"/>
              出席
             </label>
             <label for="absent">
              <input type="radio" id="absent" name="attendance" onclick="ShowHideDiv()"value="false" checked="checked"/>
              欠席
             </label>
             <div id="dvPassport" style="display: none">
              <ul class="ddmenu1">

                <li><a href="#">知識・技能</a>
                   <ul>
                     <li>
                    <select name="red">
                      <option value="1">A</option>
                      <option value="2">B</option>
                      <option value="3">C</option>
                    </select>
                  </li>
                   </ul>
                </li>
              </ul>
              <br/>
              <ul class="ddmenu2">
  
                <li><a href="#">思考・判断・表現</a>
                   <ul>
                    <select name="green">
                      <option value="1">A</option>
                      <option value="2">B</option>
                      <option value="3">C</option>
                    </select>
                   </ul>
                </li>
              </ul>
                <br/>
              <ul class="ddmenu3">
  
                <li><a href="#">学びに向かう人間性</a>
                   <ul>
                    <select name="blue">
                      <option value="1">A</option>
                      <option value="2">B</option>
                      <option value="3">C</option>
                    </select>
                   </ul>
                </li>
              </ul>
  
              個人コメント(100文字まで)
              <textarea class="textarea-grade" rows="6" cols="20" maxlength="100" name="comment"></textarea>
              <input type="hidden" value="-1" align="center" name="seatnum" id="seatnum"/>
              <script>
                $(function () {
             
                  var id=window.sessionStorage.getItem("Selected");
                  $('#seatnum').val(id);//hiddenパラメータを座席のidに置き換え
                      
                });
            </script>
                        
             </div><br/>
             <input type="submit" value="評価を確定する" align="center" />
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
                    
                    if(setStudent.getStudent_gender() == 1){//男
                    %>
                    <script>
                      $(function () {
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('setseatm');
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('seatall');
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('seat');
                        $("#<%=studentSeatingArr.getSeat() %>").removeClass("seatblank");
                        $("#<%=studentSeatingArr.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %>");
                      });
                    </script>
                    <% }else if(setStudent.getStudent_gender() == 2){//女
                    %>
                  <script>
                      $(function () {
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('seat');
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('seatall');
                        $("#<%=studentSeatingArr.getSeat() %>").removeClass("seatblank");
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
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('seat');
                        $("#<%=studentSeatingArr.getSeat() %>").addClass('seatall');
                        $("#<%=studentSeatingArr.getSeat() %>").removeClass("seatblank");
                        $("#<%=studentSeatingArr.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %>");
                      });
                  </script>
                    <%
                    }
                    for(Grade Grade : GradeList ){%>
                    <script>
                      $(function () {
                        
                        $("#<%=Grade.getSeat() %>").addClass('registed');
                        $("#<%=Grade.getSeat() %>").removeClass('seat');
                        
          
                      });
                  </script>
                    
               
                <% } %>

              
              
              <%} }%>

              <% if(GradeList.size()!=studentSeatingArrList.size()) { %>
                <script>
                  $(function () {
            
                    $("h2").text('全ての座席に評価を登録してください');
                      $("#submitbtn").get(0).type = 'button';
                      $("#submitbtn").addClass('no-selectable');
                      $("#submitbtn").removeClass('selectable');
                    
                  });
              </script>
            <h2 class="h1-bottom"></h2>

            <%}%>
            <a href="./GradeTop"><button align="center" name="regist_top">評価メニュートップへ戻る</button></a>
  </body>
</html>

<style >