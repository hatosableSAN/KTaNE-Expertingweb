<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.Student" %>
<%@ page import= "beans.ClassDef" %>
<%
    List<Student> student=(List<Student>) session.getAttribute("List");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <style>
    .student {
        width: 900px;                /* 横幅を200pxに指定 */
        height: 230px;               /* 横幅を200pxに指定 */
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        overflow-y: scroll;          /* 縦方向にスクロール可能にする */
      }
    </style>
  <body>
    <p align="right">ユーザーID　${Student.student_user}</p>
    <h1 align="center">クラス登録確認</h1>
    <h2 align="center">以下の内容を登録します</h2>
    <form action="./RegistClassConfirm" method="post">
      <!--% ClassDef l:list %-->
      <table>
        <tr>
          <th>年度</th>
          <td><input type="hidden"/ name="class_year" value=${ClassDef.class_year}>${ClassDef.class_year}</td>
        </tr>
        <tr>
          <th>クラス名</th>
          <td><input type="hidden"/ name="class_name" value=${ClassDef.class_name}>${ClassDef.class_name}</td>
        </tr>
        <tr>
          <th>メンバー</th>
          <td><div class="student">
            <table>
                <tr><th>　　</th><th>番号　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　</th><th>性別　　　　　</th><th>登録者　　　　　　　　　</th></tr>

                <% for(Student s:student){ %>
                    <tr>
                    <td><!--lavel--><input type="hidden"/ name="student_member" value=<%=s.getStudent_id() %>></td>
                    <td><%=s.getStudent_id() %></td>
                    <td><%=s.getStudent_name() %></td>
                    <td><%=s.getStudent_gender() %></td>
                    <td><%=s.getStudent_user() %></td>
                    <!--/label-->
                    </tr>
                <%} %>

            </table>
            </div></td>
        </tr>
      </table>
        <input type="submit" value="確定" name="hand" />
      </form>
      <br />
      <a href="./WEB-INF/classes/classTop.jsp"><button align="center" name="class_top">キャンセル</button></a>
  </body>
</html>