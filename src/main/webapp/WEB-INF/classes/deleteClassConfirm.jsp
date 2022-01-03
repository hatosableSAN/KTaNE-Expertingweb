<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<% User User = (User)session.getAttribute("User"); %>
<%@ page import= "beans.Student" %>
<%@ page import= "beans.ClassDef" %>
<%@ page import= "beans.User" %>
<%
    List<Student> list=(List<Student>) request.getAttribute("List");
    ClassDef ClassDef =(ClassDef) request.getAttribute("ClassDef");

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
    <p align="right">ユーザーID　${User.id}</p>
      <a href="./ClassTop"><button align="center" name="class_top">クラスメニュートップへ戻る</button></a>
      <br>
    <h1 align="center">クラス削除</h1>
        年度　
        <%= ClassDef.getClass_year()%>年度
        <br>
        クラス名　<%= ClassDef.getClass_name()%>
        <br>
        メンバー　　
        <!--form action="../RegistStudentGet" method="post">
            <button text-align="center" name="regist_class">一覧表示</button>
        </form-->
        <br />
        <div class="student">
        <table>
            <tr><th>番号　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　</th><th>性別　　　　　</th><th>登録者　　　　　　　　　</th></tr>
            <!--tr><td><input type="checkbox"/></td><td>E195406</td><td>鈴木有里</td><td>女</td><td>ABC</td></tr-->

            <% for(Student s:list){ %>
                <tr>
                <%-- <td><!--lavel--><input type="checkbox"/ name="student_member" value=<%=s.getStudent_id() %>></td> --%>
                <td><%=s.getStudent_id() %></td>
                <td><%=s.getStudent_name() %></td>
                <td><% if(s.getStudent_gender()==1){ %>
                    男
                    <% }else if(s.getStudent_gender()==2){ %>
                    女
                    <% }else if(s.getStudent_gender()==3){ %>
                    その他
                    <%} %>
                </td>
                <td><%=s.getStudent_user() %></td>
                <!--/label-->
                </tr>
                <%} %>

        </table>
        </div>
        <font color="red">以上のクラスを削除しますか？</font><br>
        <form action="./ClassTop" method="post">
                <input type="hidden" name="ClassId" value="<%=ClassDef.getClass_id()%>">
                <input type="submit" value="キャンセル" name="hand" />
        </form>
        <form action="./DeleteClassComplete" method="post">
          <input type="hidden" name="ClassId" value="<%=ClassDef.getClass_id()%>">
          <input type="submit" value="削除" name="hand" />
        </form>
      <br />
  </body>
</html>