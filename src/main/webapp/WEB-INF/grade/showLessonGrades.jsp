<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="beans.User" %>
<%@ page import="beans.ClassDef" %>
<%@ page import="beans.Student" %>
<%@ page import="beans.Student" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="beans.*" %>
<%@ page import="service.StudentService" %>
<% User User = (User)session.getAttribute("User"); %>
<% List<Student> StudentList = (ArrayList<Student>) session.getAttribute("StudentList"); %>
<% ClassDef ClassDef = (ClassDef)session.getAttribute("ClassDef"); %>
<% List<Grade> GradeList = (List<Grade>) session.getAttribute("GradeList"); %>
<% Lessons Lesson = (Lessons)session.getAttribute("Lesson"); %>
<% SeatingArrangements SeatingArrangement = (SeatingArrangements)session.getAttribute("SeatingArrangements"); %>
<% ClassDef Class = (ClassDef)session.getAttribute("Class"); %>
<% SimpleDateFormat inputdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
<% SimpleDateFormat outputdf = new SimpleDateFormat("MM月dd日");%>
<% SimpleDateFormat outputlessondf = new SimpleDateFormat("yyyy年MM月dd日");%>
<% SimpleDateFormat inputlessondf = new SimpleDateFormat("yyyy-MM-dd");%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/css/modal_gradeshow.js"></script>
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
        line-height:95%;
      }
      .setseatf {
        width: 80px;
        height: 50px;
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        background-color:rgb(240, 134, 134);
        text-align:center;
        line-height:95%;
        text-size-adjust:inherit;
      }
      .setseato {
        width: 80px;
        height: 50px;
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        background-color:gray;
        text-align:center;
        line-height:95%;
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
        background-color:#fcc490;
        text-align:center;
        margin-left: auto;
        margin-right: auto;
      }
      .h1-bottom {
              color: rgb(255, 0, 0);
            }
    .bottom{
      text-align:center;
      padding-left:40px;
    }
    </style>
    <!--  ここまでスタイル  -->
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >

 </head>
  <body>
    <div class="darkblue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">授業評価閲覧</h1>
    </div>
    <br>


    評価を変更したい座席がある場合は、座席をクリックして評価情報を変更できます。
    <a href="./UpdateLessonInfo"><button align="center" name="regist_top">授業情報変更</button></a>
    <a href="./DeleteLessonInfo"><button align="center" name="regist_top">削除</button></a>
    <br>
      
        <table style="">
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
        </table>
        <div class="bottom">
        <p style=" vertical-align: top;margin:20px;border-style:solid;width:240px;height:100px;display: inline-block;text-align: center;border-color: #000;">授業コメント</br><%=Lesson.getComment()%></p>
        <div style="display: inline-block;"class="kyoutaku">教卓</div>
        <p style=" vertical-align: top;margin:20px;border-style:solid;width:240px;height:100px;display: inline-block;text-align: center;border-color: #000;">年度：<%=Class.getClass_year()%>　クラス：<%=Class.getClass_name()%></br>
          期間：<%=outputdf.format(inputdf.parse(SeatingArrangement.getStartDate()))%>～
          <% if(SeatingArrangement.getEndDate()!=null){ %>
            <%=outputdf.format(inputdf.parse(SeatingArrangement.getEndDate()))%>
            <% }else{%>
              終了日未定
            <% }%>
          
         </br>
          授業日：<%=outputlessondf.format(inputlessondf.parse(Lesson.getLessonDate()))%></p>
        </div>
        <br />
      
      
      

<!-- モーダルはここから -->
        <div id="modal-content-grade">
          <form action="./UpdateStudentGradeComplete" method="post">
            「閉じる」か「背景」をクリックするとウィンドウを終了します。<br/>
            <h1 align="center">個人評価変更</h1>
            
            出欠状態：<span id="attendance"></span>
            <br/>
            <p>変更後の評価はこちらに入力してください。</p>
            <br/>
            <input type="radio" id="attend" name="attendance" value="true"/>
            出席
            <input type="radio" id="absent" name="attendance" value="false" checked="checked"/>
            欠席
              <ul class="ddmenu1">

                <li><a href="#" >知識・技能 </a>
                   <ul>
                     <li>
                    <select name="red"id="red">
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
                    <select name="blue"id="blue">
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
                    <select name="green" id="green">
                      <option value="1">A</option>
                      <option value="2">B</option>
                      <option value="3">C</option>
                    </select>
                   </ul>
                </li>
              </ul>
  
              個人コメント(100文字まで)
              <textarea class="textarea-grade" rows="6" cols="20" maxlength="100" name="comment" id="commenttext">
                
              </textarea>
              <input type="hidden" value="-1" align="center" name="id" id="submitid"/>
              
                        
             <br/>
             <input type="submit" value="評価を確定する" align="center" />
             
          </form>
          
            <p><a id="modal-close" class="button-link"><button align="center">閉じる</button></a></p>
        </div>

      <br />

      <% if(GradeList!=null && GradeList.size() > 0) {
                  for(Grade Grade : GradeList ){ //座席に登録されている生徒。
                    //座席配置されている生徒IDから生徒情報を取得
                    StudentService StudentService = new StudentService();
                    Student student = new Student();
                    student.setStudent_id(Grade.getStudentId());
                    Student setStudent = StudentService.searchStudent(student);
                    String Red=new String();
                    String Blue=new String();
                    String Green=new String();
                    if(Grade.getRed()==1){
                      Red="A";
                  }else if(Grade.getRed()==2){
                    Red="B";
                  }else{
                    Red="C";
                  }

                  if(Grade.getBlue()==1){
                    Blue="A";
                }else if(Grade.getBlue()==2){
                  Blue="B";
                }else{
                  Blue="C";
                }
                if(Grade.getGreen()==1){
                  Green="A";
              }else if(Grade.getGreen()==2){
                Green="B";
              }else{
                Green="C";
              }



                    if(setStudent.getStudent_gender() == 1){//男
                    %>
                    <script>
                      $(function () {
                        $("#<%=Grade.getSeat() %>").attr('id',<%=Grade.getSeat() %>);
                        $("#<%=Grade.getSeat() %>").attr('gradeid',<%=Grade.getId() %>);
                        $("#<%=Grade.getSeat() %>").attr('red',<%=Grade.getRed() %>);
                        $("#<%=Grade.getSeat() %>").attr('green',<%=Grade.getGreen() %>);
                        $("#<%=Grade.getSeat() %>").attr('blue',<%=Grade.getBlue() %>);
                        $("#<%=Grade.getSeat() %>").attr('comment',"<%=Grade.getComment() %>");

                        if(<%=Grade.isAttendance() %>==true){
                        $("#<%=Grade.getSeat() %>").attr('attendance',1);
                        $("#<%=Grade.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %><br><font color=red><%=Red %></font> <font color=Green><%=Green %></font> <font color=blue><%=Blue %></font> ");
                        }else{
                        $("#<%=Grade.getSeat() %>").attr('attendance',0);
                        $("#<%=Grade.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %><br>欠席");
                        }
                        
                        $("#<%=Grade.getSeat() %>").addClass('setseatm');
                        $("#<%=Grade.getSeat() %>").addClass('seatall');
                        $("#<%=Grade.getSeat() %>").addClass('seat');
                        $("#<%=Grade.getSeat() %>").removeClass("seatblank");
                        
                      });
                    </script>
                    <% }else if(setStudent.getStudent_gender() == 2){//女
                    %>
                  <script>
                      $(function () {
                        if(<%=Grade.isAttendance() %>==true){
                        $("#<%=Grade.getSeat() %>").attr('attendance',1);
                        $("#<%=Grade.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %><br><font color=red><%=Red %></font> <font color=Green><%=Green %></font> <font color=blue><%=Blue %></font> ");
                        }else{
                        $("#<%=Grade.getSeat() %>").attr('attendance',0);
                        $("#<%=Grade.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %><br>欠席");
                        }

                        $("#<%=Grade.getSeat() %>").attr('id',<%=Grade.getSeat() %>);
                        $("#<%=Grade.getSeat() %>").attr('gradeid',<%=Grade.getId() %>);
                        $("#<%=Grade.getSeat() %>").attr('red',<%=Grade.getRed() %>);
                        $("#<%=Grade.getSeat() %>").attr('green',<%=Grade.getGreen() %>);
                        $("#<%=Grade.getSeat() %>").attr('blue',<%=Grade.getBlue() %>);
                        $("#<%=Grade.getSeat() %>").attr('comment',"<%=Grade.getComment() %>");
                        $("#<%=Grade.getSeat() %>").addClass('seat');
                        $("#<%=Grade.getSeat() %>").addClass('seatall');
                        $("#<%=Grade.getSeat() %>").removeClass("seatblank");
                        $("#<%=Grade.getSeat() %>").addClass('setseatf');
 
                        
                      });
                  </script>
                    <%
                    }else if(setStudent.getStudent_gender() == 3){//その他
                    %>
                    <script>
                      $(function () {
                        if(<%=Grade.isAttendance() %>==true){
                        $("#<%=Grade.getSeat() %>").attr('attendance',1);
                        $("#<%=Grade.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %><br><font color=red><%=Red %></font> <font color=Green><%=Green %></font> <font color=blue><%=Blue %></font> ");
                        }else{
                        $("#<%=Grade.getSeat() %>").attr('attendance',0);
                        $("#<%=Grade.getSeat() %>").html("<%=setStudent.getStudent_id() %><br><%=setStudent.getStudent_name() %><br>欠席");
                        }

                        $("#<%=Grade.getSeat() %>").attr('id',<%=Grade.getSeat() %>);
                        $("#<%=Grade.getSeat() %>").attr('gradeid',<%=Grade.getId() %>);
                        $("#<%=Grade.getSeat() %>").attr('red',<%=Grade.getRed() %>);
                        $("#<%=Grade.getSeat() %>").attr('green',<%=Grade.getGreen() %>);
                        $("#<%=Grade.getSeat() %>").attr('blue',<%=Grade.getBlue() %>);
                        $("#<%=Grade.getSeat() %>").attr('comment',"<%=Grade.getComment() %>");
                        $("#<%=Grade.getSeat() %>").addClass('setseato');
                        $("#<%=Grade.getSeat() %>").addClass('seat');
                        $("#<%=Grade.getSeat() %>").addClass('seatall');
                        $("#<%=Grade.getSeat() %>").removeClass("seatblank");
                        
                      });
                  </script>
                    
                  </script>
                    
               
                <% } %>

              
              
              <%} }%>

              <% if(GradeList.size()!=GradeList.size()) { %>
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
      <a href="./GradeTop"><button align="center" class="backbtn" name="regist_top">評価メニュー<br/>トップへ戻る</button></a>
  </body>
</html>

<style >