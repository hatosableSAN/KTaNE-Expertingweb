<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.Student" %>
<%
    List<Student> list=(List<Student>) request.getAttribute("List");
    List<Student> list_all=(List<Student>) request.getAttribute("List_all");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
    <style>
    .student {
        width: 900px;                /* 横幅を900pxに指定 */
        height: 280px;               /* 横幅を230pxに指定 */
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
     <h1 align="center">クラス登録</h1>
    </div>
    <form action="./RegistClass" method="post">
      <table align="center">
        <tr><th>年度　</th>
        <td><input type="text" name="class_year" placeholder="半角数字(4文字)" maxlength="4" minlength="4" pattern="^[0-9]+$"/>　年度<font color="red">＊</font></td></tr>
        <tr><th>クラス名　</th>
        <td><input type="text" name="class_name" placeholder="(1~20文字)" maxlength="20" minlength="1" pattern="[ぁ-んァ-ヶｦ-ﾟ一-龠a-zA-Z0-9０-９\-\u30FC]+"/>　1~20文字<font color="red">＊</font></td></tr>
        <tr><th>メンバー　</th>
        <td>
        <div class="student">
        <table>
            <tr><th>　　</th><th class="bar">番号　　　　　　　　　　　</th><th class="bar">名前　　　　　　　　　　　　　</th><th class="bar">性別　　　　　　　　　</th><th class="bar">登録者　　　　　　　　　</th></tr>
            <% if(list_all.size() == 0){ %>
            </table>
            <br>
            <h4 align="center">児童が登録されていません</h4>
            </div>
          </form>
              <% }else{
            if(list.size()==0){ %>
              <tr><td></td><td align="center">検索結果に当てはまる児童が</td><td>いませんでした</td></tr>            
        <% }else{
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
                <%} } }%>

        <!--/table-->
        </div>
      </td></tr>
        <input type="submit" value="登録" name="hand" class="btn" id="btn_right">
      </form>
      <tr><th>　</th>
      <td><font size="2px">児童生徒検索：</font><!--div class="center"-->
        <div style="display:inline-flex">
        <form action="./SearchStudent" method="post">
            <input type="text" name="stu_search" maxlength="20" minlength="1" pattern="[ぁ-んァ-ヶｦ-ﾟ一-龠a-zA-Z0-9\-\u30FC]+"/>
            <input type="radio" name="radiobutton" value="number" required checked> <font size="2px">番号</font>
            <input type="radio" name="radiobutton" value="name"> <font size="2px">名前</font>
            <input type="hidden" value="regist" name="type">
            <button text-align="center" name="search_stu" value="search" class="button" style="width: 70px; height:30px; padding:0px;">検索実行</button>
        </form>　
            <form action="./RegistStudentGet" method="post">
             <button text-align="center" name="search_stu" value="all" class="button" style="width: 70px; height:30px; padding:0px;">一覧表示</button>
        </form></div>
      </td></tr></table>
      <!--a href="./WEB-INF/classes/classTop.jsp"><button align="center" name="class_top">キャンセル</button></a-->
      <form action='./ClassTop' method='get'>
        <input type='submit' value='キャンセル' class="button_grey" id="btn_left">
      </form>
  </body>
</html>