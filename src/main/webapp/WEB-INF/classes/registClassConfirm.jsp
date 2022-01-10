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
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
    <style>
    .student {
        width: 900px;                /* 横幅を200pxに指定 */
        height: 230px;               /* 横幅を200pxに指定 */
        border: 1px solid #000;      /* わかりやすくボーダーを引く */
        overflow-y: scroll;          /* 縦方向にスクロール可能にする */
      }
      .bar{
       background-color: #F8AB74;
      }
    </style>
  <body>
    <div class="rightblue">
     <p align="right">ユーザーID　${User.id}</p>
     <h1 align="center">クラス登録確認</h1>
    </div>
    <h2 align="center">以下の内容を登録します</h2>
    <form action="./RegistClassConfirm" method="post">
      <!--% ClassDef l:list %-->
      <table align="center">
        <tr>
          <th>年度　</th>
          <td><input type="hidden"/ name="class_year" value=${ClassDef.class_year}>${ClassDef.class_year}　年度</td>
        </tr>
        <tr>
          <th>クラス名　</th>
          <td><input type="hidden"/ name="class_name" value=${ClassDef.class_name}>${ClassDef.class_name}</td>
        </tr>
        <tr>
          <th>メンバー　</th>
          <td>
            <div class="student">
            <table>
                <tr><th></th><th class="bar">番号　　　　　　　　　　　</th><th class="bar">名前　　　　　　　　　　　　　</th><th class="bar">性別　　　　　　　　　</th><th class="bar">登録者　　　　　　　　　</th></tr>

                <% if(student.size()==0){%>
                  </table>
                  <h4 align="center">なし</h4></div>
                  <input type="hidden"/ name="student_member" value="">
                  <%} else{
                for(Student s:student){ %>
                    <tr>
                    <td><!--lavel--><input type="hidden"/ name="student_member" value=<%=s.getStudent_id() %>></td>
                    <td><%=s.getStudent_id() %></td>
                    <td><%=s.getStudent_name() %></td>
                    <td><!--%=s.getStudent_gender() %-->
                      <% if(s.getStudent_gender() == 1){ %>
                        男
                      <% }else if(s.getStudent_gender() == 2){ %>
                          女
                      <% }else{ %>
                        その他
                      <% } %>
                    </td>
                    <td><%=s.getStudent_user() %></td>
                    <!--/label-->
                    </tr>
                <%} %>

            </table>
            </div></td><% } %>
        </tr>
      </table>
        <input type="submit" value="確定" name="hand" class="btn" id="btn_right">
      </form>
      <br />
      <!--a href="./WEB-INF/classes/classTop.jsp"><button align="center" name="class_top">キャンセル</button></a-->
      <form action='./ClassTop' method='get'>
        <input type='submit' value='キャンセル' class="button_grey" id="btn_left">
      </form>
  </body>
</html>