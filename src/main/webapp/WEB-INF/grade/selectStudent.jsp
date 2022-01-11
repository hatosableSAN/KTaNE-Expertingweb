<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.*" %>
<% User User = (User)session.getAttribute("User"); %>
<%
    List<Student> stu_list=(List<Student>) session.getAttribute("Stu_list");
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
        <font size="2px">検索：</font>
        <form action="./SearchStudent" method="post">
          <input type="text" name="stu_search" maxlength="20" minlength="1" pattern="[ぁ-んァ-ヶｦ-ﾟ一-龠a-zA-Z0-9\-\u30FC]+"/>
          <input type="radio" name="radiobutton" value="number" checked> <font size="2px">番号</font>
          <input type="radio" name="radiobutton" value="name"> <font size="2px">名前</font>
          <input type="hidden" value="stu_search_grade" name="type">
          <button text-align="center" name="show_class" class="button" style="width: 70px; height:30px; margin: 5px;padding: 0px;">検索実行</button>
          <button text-align="center" name="regist_class" class="button" style="width: 70px; height:30px; margin:5px; margin: 5px;padding: 0px;">一覧表示</button>
      </form>
        <div class="student">
          <table>
              <tr><th>番号　　　　　　　　　　　</th><th>名前　　　　　　　　　　　　　</th><th>性別　　　　　</th><th>登録者　　　　　　　　　</th></tr>
            <!--tr><td><input type="checkbox"/></td><td>E195406</td><td>鈴木有里</td><td>女</td><td>ABC</td></tr-->
            
            
                <%if(stu_list==null||stu_list.size()==0){ %>
                    <tr><td></td><td align="center">検索結果に当てはまる児童が</td><td>いませんでした</td></tr>
                <% }else{
                  for(Student s:stu_list){
                    int flag=0; %>
                      <tr>
                      <td><label><%=s.getStudent_id() %></label></td>
                      <td><label><%=s.getStudent_name() %></label></td>
                      <td><label>
                        <% if(s.getStudent_gender() == 1){ %>
                          男
                        <% }else if(s.getStudent_gender() == 2){ %>
                            女
                        <% }else{ %>
                          その他
                        <% } %>
                        </label></td>
                      <td><label><%=s.getStudent_user() %></label></td>
                      <td><form action="./ShowStudentGrades" method="get">
                        <input type="hidden" name="studentid" value="<%=s.getStudent_id() %>">
                        <input type="hidden" name="studentname" value="<%=s.getStudent_name() %>">
                        <% if(s.getStudent_gender() == 1){ %>
                          <input type="hidden" name="studentgender" value="男">
                        <% }else if(s.getStudent_gender() == 2){ %>
                          <input type="hidden" name="studentgender" value="女">
                        <% }else{ %>
                          <input type="hidden" name="studentgender" value="その他">
                        <% } %>
                        <input type="submit" class="btn"value="選択">
                        </form>
                        </td>
      
                      </tr></label><!--/table></div-->
                  <% } %>
                    <!--/table></div-->
                <% } %>
              </table></div>

      <br />
      <a href="./GradeTop"><button align="center" class="button_grey" id="btn_left" >キャンセル</button></a>
  </body>
</html>