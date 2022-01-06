<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.*" %>
<% 
    List<Grade> list=(List<Grade>) session.getAttribute("Grade");
%>

<% User User = (User)session.getAttribute("User"); %>
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
  <body>
    <p align="right">ユーザーID　${User.id}</p>
    <h1 align="center">児童・生徒ごとの評価一覧</h1>
        <!--form action="../RegistStudentGet" method="post">
            <button text-align="center" name="regist_class">一覧表示</button>
        </form-->
        <br />
        <form action="./SearchStudent" method="post">
          <font size="2px">検索：</font>
            <input type="text" name="stu_search" maxlength="20" minlength="1" pattern="^[ぁ-ん]+$ , [\u3041-\u309F]*+^[ァ-ンヴー]+$ , [\u30A1-\u30FF]*+[A-Za-z]"/>
            <input type="radio" name="radiobutton" value="number"> <font size="2px">番号</font>
            <input type="radio" name="radiobutton" value="name"> <font size="2px">名前</font>
            <button text-align="center" name="regist_class">検索実行</button>
            <button text-align="center" name="regist_class">一覧表示</button>
        </form>
          <table>
              <tr><th>授業日</th><th>出欠</th><th>観点1</th><th>観点2</th><th>観点3</th><th>評価コメント</th><th></th></tr>
              <!--tr><td><input type="checkbox"/></td><td>E195406</td><td>鈴木有里</td><td>女</td><td>ABC</td></tr-->
  
              <% for(Grade s:list){ %>
                  <tr>
                  <td><%=s.getStudentId() %></td>
                  <td><% if(s.isAttendance()){%>
                      出席
                        
                    <% }else{ %>
                      欠席
                    <% } %></td>
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
                <%}%>
                  </td>
                  <td><%=s.getComment() %></td>
                  <td><form action="./UpdateStudentGrades" method="get">
                    <input type="hidden" name="gradeid" value="<%=s.getId() %>">
                    <input type="submit" value="選択">
                    </form>
                    </td>
                  <!--/label-->
                  </tr>
                  <%} %>
  
          
          </div>
      

      <br />
      <a href="./GradeTop"><button align="center">キャンセル</button></a>
  </body>
</html>