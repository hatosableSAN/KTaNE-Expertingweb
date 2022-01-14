<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.*" %>
<% 
    List<Grade> list=(List<Grade>) session.getAttribute("Grade");
%>
<% 
    List<String> LessonDateList=(List<String>) session.getAttribute("LessonDateList");
%>

<% User User = (User)session.getAttribute("studentid"); %>
<% String id = (String)request.getParameter("studentid"); %>
<% String Name = (String)request.getParameter("studentname"); %>
<% String Gender = (String)request.getParameter("studentgender"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
      <h1 align="center">児童・生徒ごとの評価一覧</h1>
    </div>
        <!--form action="../RegistStudentGet" method="post">
            <button text-align="center" name="regist_class">一覧表示</button>
        </form-->
        <br />
        番号：<%=id%>　氏名：<%=Name%>　性別：<%=Gender%>
        <% if(list==null){%>
          <p style="text-align: center;">評価がありません</p>
          <% }else{%>
          <table>
              <tr><th>授業日</th><th>出欠</th><th>観点1</th><th>観点2</th><th>観点3</th><th>評価コメント</th><th></th></tr>
              <!--tr><td><input type="checkbox"/></td><td>E195406</td><td>鈴木有里</td><td>女</td><td>ABC</td></tr-->
             
              <%int i=0; for(Grade s:list){ %>
                  <tr>
                  <td><%=LessonDateList.get(i)%></td>
                  <% if(s.isAttendance()){%>
                    <td>出席</td>
                        
                  <td><%if(s.getRed()==1){%>
                    A
                    <% }else if(s.getRed()==2){%>
                  B
                  <%}else{%>
                  C
                  <%}%>
              </td>
              <td><%if(s.getGreen()==1){%>
                A
                <% }else if(s.getGreen()==2){%>
              B
              <%}else{%>
              C
              <%}%></td>
                  <td>
                    
                <%if(s.getBlue()==1){%>
                  A
                  <%}else if(s.getBlue()==2){%>
                B
                <%}else{%>
                C
                <%}  %>              
                 <% }else{ %>
                  <td>欠席</td>
                  <td></td>
                  <td></td>
                  <td></td>
                <% } %>
                
                
                  </td>
                  <td><%=s.getComment()%></td>
                  <td><form action="./UpdateStudentGrades" method="get">
                    <input type="hidden" name="gradeid" value="<%=s.getId() %>">
                    <input type="submit" value="変更" class="btn">
                    </form>
                    </td>
                  <!--/label-->
                  </tr>
                  <%
                i++;} 
                }%>
  
          
          </div>
      

      <br />
      <a href="./SelectGradeStudent"><button align="center" class="button_grey" id="btn_left">キャンセル</button></a>
  </body>
</html>