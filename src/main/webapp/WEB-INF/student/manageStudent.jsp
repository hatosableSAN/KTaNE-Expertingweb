<%@page contentType= "text/html; charset=UTF-8" session="true"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.Student" %>
<%@ page import= "beans.User" %>
<%
    List<Student> list=(List<Student>) session.getAttribute("List");
    User user = (User) session.getAttribute("User");
%>
<html>
      <head>
        <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
      </head>
      <style>
        .student {
            width: 1000px;                /* 横幅を900pxに指定 */
            height: 280px;               /* 横幅を230pxに指定 */
            border: 1px solid #000;      /* わかりやすくボーダーを引く */
            border-collapse:collapse;
            overflow-y: scroll;          /* 縦方向にスクロール可能にする */
          }
        .bar{
          background-color: #F8AB74;
        }
        tr{
          margin: 0;
        }
      </style>
  <body>
      <div class="green">
      <p align="right">ユーザーID　${User.id}</p>
      <h1 align="center">児童・生徒一覧</h1>
    </div><br><br><br>
    <div class="student">
    <table align="center">
        <tr class='bar'><th>番号　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　</th><th>性別　　　　　　　　　</th><th>登録者　　　　　　　　　</th><th>　　　　　</th><th>　　　　　</th></tr>
        <% if (list.size() == 0) { %>
            </table>
            <h4 align='center'>児童が登録されていません</h4>
        <% } else {
            for(Student s:list){ %>
            <tr><td><%=s.getStudent_id() %></td>
            <td><%=s.getStudent_name() %></td>
            <td>
<% if( s.getStudent_gender()==1){ %>
  男
<%} else if(s.getStudent_gender()==2){ %>
  女
<%} else{ %>
  その他
<% } %>
</td>
        <td><%=s.getStudent_user() %></td>
        <% if(s.getStudent_user().equals(user.getId())){ %>
            <form action='./UpdateStudent' method='post'>
                <td><input type='submit' name='student_update' value='変更' class='button' style="width:80px; height: 20px;padding: 0;">
                    <input type='hidden' name='update_hidden' value=<%=s.getStudent_id()%>>
                </td>
            </form>
            <form action='./DeleteStudent' method='post'>
                <td><input type='submit' name='student_delete' value='削除' class='button_grey' style="color: red; width:80px; height: 20px;padding: 0;">
                    <input type='hidden' name='delete_hidden' value=<%=s.getStudent_id()%>>
                </td>
            </form>
        <% }else{ %>
                <td>　　</td>
                <td>　　</td>
        <% } %>
      </tr><% } %>
    </table><% } %>
    
  <!--form action="./GoStudentTop" method="get"-->
   <!--input type="submit" value="児童生徒一覧へ戻る" class="backbtn"-->
   <a href="./GoStudentTop"><button align="center" name="class_top" class="backbtn">児童・生徒メニュー<br>トップへ戻る</button></a>
  <!--/form-->
    </body>
</html>