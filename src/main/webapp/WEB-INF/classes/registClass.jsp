<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.Student" %>
<%
    List<Student> list=(List<Student>) request.getAttribute("List");
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
    <h1 align="center">クラス登録</h1>
    <form action="./RegistClass" method="post">
        年度　
        <input type="text" name="class_year" placeholder="半角数字(4文字)" maxlength="4" minlength="4" pattern="^[0-9]+$"/>　年度<font color="red">＊</font>
        <br>
        クラス名　
        <input type="text" name="class_name" placeholder="(1~20文字)" maxlength="20" minlength="1" pattern="^[ぁ-ん]+$ , [\u3041-\u309F]*+^[ァ-ンヴー]+$ , [\u30A1-\u30FF]*+[A-Za-z]"/>　1~20文字<font color="red">＊</font>
        <br>
        メンバー　　
        <!--form action="../RegistStudentGet" method="post">
            <button text-align="center" name="regist_class">一覧表示</button>
        </form-->
        <br />
        <div class="student">
        <table>
            <tr><th>　　</th><th>番号　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　</th><th>性別　　　　　</th><th>登録者　　　　　　　　　</th></tr>
            <!--tr><td><input type="checkbox"/></td><td>E195406</td><td>鈴木有里</td><td>女</td><td>ABC</td></tr-->
            <% if(list.size() == 0){ %>
              <tr><td></td><td align="center">検索結果に当てはまる児童が</td><td>いませんでした</td></tr>
              <% 
            }else{
              for(Student s:list){ %>
                <tr>
                <td><!--lavel--><input type="checkbox"/ name="student_member" value=<%=s.getStudent_id() %>></td>
                <td><%=s.getStudent_id() %></td>
                <td><%=s.getStudent_name() %></td>
                <td><!--%=s.getStudent_gender() %-->
                  <% if(s.getStudent_gender() == 1){ %>
                    男
                  <% }else if(s.getStudent_gender() == 2){ %>
                      女
                  <% }else{ %>
                    その他
                  <% } %></td>
                <td><%=s.getStudent_user() %></td>
                <!--/label-->
                </tr>
                <%} }%>

        </table>
        </div>
        <input type="submit" value="登録" name="hand" />
      </form>
      <font size="2px">検索：</font>
        <form action="./SearchStudent" method="post">
            <input type="text" name="stu_search" maxlength="20" minlength="1" pattern="^[ぁ-ん]+$ , [\u3041-\u309F]*+^[ァ-ンヴー]+$ , [\u30A1-\u30FF]*+[A-Za-z]"/>
            <input type="radio" name="radiobutton" value="number" required> <font size="2px">番号</font>
            <input type="radio" name="radiobutton" value="name"> <font size="2px">名前</font>
            <button text-align="center" name="search_stu" value="search">検索実行</button>
            <button text-align="center" name="search_stu" value="all">一覧表示</button>
        </form>
      <br />
      <!--a href="./WEB-INF/classes/classTop.jsp"><button align="center" name="class_top">キャンセル</button></a-->
      <form action='./ClassTop' method='get'>
        <input type='submit' value='キャンセル'>
      </form>
  </body>
</html>