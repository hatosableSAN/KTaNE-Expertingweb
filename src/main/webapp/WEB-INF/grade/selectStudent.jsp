<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.*" %>
<%
    List<Student> list=(List<Student>) request.getAttribute("StudentList");
%>
<% User User = (User)session.getAttribute("User"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <style>
    .student {
        width: 900px;                /* 横幅を200pxに指定 */
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
        <form action="./SearchStudentGrade" method="post">
          <font size="2px">検索：</font>
            <input type="text" name="stu_search" maxlength="20" minlength="1" pattern="^[ぁ-ん]+$ , [\u3041-\u309F]*+^[ァ-ンヴー]+$ , [\u30A1-\u30FF]*+[A-Za-z]"/>
            <input type="radio" name="radiobutton" value="number"> <font size="2px">番号</font>
            <input type="radio" name="radiobutton" value="name"> <font size="2px">名前</font>
            <button text-align="center" name="regist_class">検索実行</button>
            <button text-align="center" name="regist_class">一覧表示</button>
        </form>
        <div class="student">
        <table>
            <tr><th>番号　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　</th><th>性別　　　　　</th><th>登録者　　　　　　　　　</th><th></th></tr>
            <!--tr><td><input type="checkbox"/></td><td>E195406</td><td>鈴木有里</td><td>女</td><td>ABC</td></tr-->

            <% for(Student s:list){ %>
                <tr>
                <td><%=s.getStudent_id() %></td>
                <td><%=s.getStudent_name() %></td>
                <td>  <% if(s.getStudent_gender() == 1){ %>
                  男
                <% }else if(s.getStudent_gender() == 2){ %>
                    女
                <% }else{ %>
                  その他
                <% } %></td>
                <td><%=s.getStudent_user() %></td>
                <td><form action="./ShowStudentGrades" method="get">
                  <input type="hidden" name="studentid" value="<%=s.getStudent_id() %>">
                  <input type="submit" value="選択">
                  </form>
                  </td>
                <!--/label-->
                </tr>
                <%} %>

        </table>
        </div>
      

      <br />
      <a href="./GradeTop"><button align="center" class="button_grey" id="btn_left" >キャンセル</button></a>
  </body>
</html>