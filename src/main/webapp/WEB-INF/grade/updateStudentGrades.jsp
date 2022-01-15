<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.*" %>
<% 
    Grade Grade = (Grade)session.getAttribute("Grade");
%>
<% String id = (String)session.getAttribute("studentid"); %>
<% String Name = (String)session.getAttribute("studentname"); %>


<% User User = (User)session.getAttribute("User"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/css/modal_grade.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/modal.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
<html>
    <style>
    .student {
                       /* 横幅を200pxに指定 */
        height: 230px;               /* 横幅を200pxに指定 */
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        overflow-y: scroll;          /* 縦方向にスクロール可能にする */
      }
      th{background-color:#F8AB74;}
    </style>
    <head>
      <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
    </head>
  <body>
    <div class="darkblue">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">個人評価変更</h1>
    </div>
        <!--form action="../RegistStudentGet" method="post">
            <button text-align="center" name="regist_class">一覧表示</button>
        </form-->
        <br />
       <p>番号<%=id%>　<%=Name%>さんの情報を変更します。<br/>
        新しい評価の情報を入力してください。</p> 
        <script type="text/javascript">
          function ShowHideDiv() {
          var attend = document.getElementById("attend");
          var dvPassport = document.getElementById("dvPassport");
          dvPassport.style.display = attend.checked ? "block" : "none";
          $("#red").val(<%=Grade.getRed()%>);
          $("#blue").val(<%=Grade.getBlue()%>);
          $("#green").val(<%=Grade.getGreen()%>);
            

          }
          </script>
        <form action="./UpdateStudentGradeComplete" method="post">
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
                    <select name="red" id="red">
                      <option value='1'>A</option>
                      <option value='2'>B</option>
                      <option value='3'>C</option>
                    </select>
                  </li>
                   </ul>
                </li>
              </ul>
              <br/>
              <ul class="ddmenu2">
  
                <li><a href="#">思考・判断・表現</a>
                   <ul>
                    <select name="green" id="green">
                     
                      <option value='1'>A</option>
                      <option value='2'>B</option>
                      <option value='3'>C</option>
                    </select>
                   </ul>
                </li>
              </ul>
                <br/>
              <ul class="ddmenu3">
  
                <li><a href="#">学びに向かう人間性</a>
                   <ul>
                    <select name="blue" id="blue">
                      <option value='1'>A</option>
                      <option value='2'>B</option>
                      <option value='3'>C</option>
                    </select>
                   </ul>
                </li>
              </ul>
  
              個人コメント(100文字まで)
              <textarea class="textarea-grade" rows="6" cols="20" maxlength="100" name="comment"><%=Grade.getComment()%></textarea>
              <input type="hidden" value='<%=Grade.getId()%>' align="center" name="id"/>
              <script>
                $(function () {

                      
                });
            </script>
                        
             </div><br/>

             <input type="submit" value="評価を確定する" align="center" class="btn" id="btn_right"/>
          </form>
          
      

      <br />
      <a href="./GradeTop"><button align="center" class="button_grey" id="btn_left">キャンセル</button></a>
  </body>
</html>