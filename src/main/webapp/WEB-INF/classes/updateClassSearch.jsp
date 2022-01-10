<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import= "beans.Student" %>
<%@ page import= "beans.ClassDef" %>
<%
    List<Student> stu_list=(List<Student>) session.getAttribute("Stu_list");
    List<Student> stu_classlist=(List<Student>) session.getAttribute("Stu_classlist");
    ClassDef ClassDef=(ClassDef) session.getAttribute("ClassDef");
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
    .bar{
      background-color: #F8AB74;
    }
    </style>
    <head>
      <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
    </head>
  <body>
    <div class="rightblue">
     <p align="right">ユーザーID　${User.id}</p>
     <h1 align="center">クラス変更</h1>
    </div>
    <form action="./UpdateClassCheck" method="post">
        <!--年度　<font color="red">＊</font>
        <input type="text" name="class_year" value="<%=ClassDef.getClass_year()%>" maxlength="4" minlength="4" pattern="^[0-9]+$"/>　年度<font color="red">＊</font>
        <br>
        クラス名　<font color="red">＊</font>
        <input type="text" name="class_name" value="<%=ClassDef.getClass_name()%>" maxlength="20" minlength="1" pattern="[ぁ-んァ-ヶｦ-ﾟ一-龠a-zA-Z0-9０-９\-\u30FC]+">　1~20文字<font color="red">＊</font>
        <br>
        メンバー　　-->
        <!--form action="../RegistStudentGet" method="post">
            <button text-align="center" name="regist_class">一覧表示</button>
        </form-->
        <table align="center">
        <tr><th>年度　</th>
          <td><input type="text" name="class_year" value="<%=ClassDef.getClass_year()%>" placeholder="半角数字(4文字)" maxlength="4" minlength="4" pattern="^[0-9]+$"/>　年度<font color="red">＊</font></td></tr>
          <tr><th>クラス名　</th>
          <td><input type="text" name="class_name" value="<%=ClassDef.getClass_name()%>" placeholder="(1~20文字)" maxlength="20" minlength="1" pattern="^[ぁ-ん]+$ , [\u3041-\u309F]*+^[ァ-ンヴー]+$ , [\u30A1-\u30FF]*+[A-Za-z]"/>　1~20文字<font color="red">＊</font></td></tr>
          <tr><th>メンバー　</th>
          <td>
        <div class="student">
        <table>
            <tr><th>　　</th><th class="bar">番号　　　　　　　　　　　</th><th class="bar">名前　　　　　　　　　　　　　</th><th class="bar">性別　　　　　　　　　</th><th class="bar">登録者　　　　　　　　　</th></tr>
           <% if(stu_list.size()==0){ %>
           </table>
              <h4 align="center">児童が登録されていません</h4>
              </div>
              <!--input type="submit" value="変更" name="hand" /-->
              <!--input type="hidden" name="ClassId" value="<%=ClassDef.getClass_id()%>">
              <input type="hidden" name="class_user" value="<%=ClassDef.getClass_user()%>">
            </form-->
          <% }else if(stu_classlist.size() == 0){ %>
              <tr><td></td><td align="center">検索結果に当てはまる児童が</td><td>いませんでした</td></tr>
              <input type="submit" value="登録" name="hand" class="btn" id="btn_right">
          <% }else{
            for(Student s:stu_classlist){
              int flag=0; %>
                <tr>
                <td><lavel>
                  <% for(int i=0; i < stu_classlist.size(); i++ ) { 
                    Student sc = stu_classlist.get(i);
                    if(s.getStudent_id().equals(sc.getStudent_id())){ %>
                      <input type="checkbox"/ name="student_member" checked value=<%=s.getStudent_id() %>>
                    <% flag=1;} } %>
                      <!--input type="checkbox"/ name="student_member" value=<%=s.getStudent_id() %>-->
                    <% if(flag==0){ %>
                      <input type="checkbox"/ name="student_member" value=<%=s.getStudent_id() %>>
                    <% } %>
                  <!--input type="checkbox"/ name="student_member" value=<%=s.getStudent_id() %>-->
                </td></label>
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
                </label>

                </tr></label><!--/table></div-->
            <% } %>
              <!--/table></div-->
          <% } %>
        </table></div>
        
        <!--/table>
      </div--></td></tr>
        <input type="submit" value="変更" name="hand" class="btn" id="btn_right" style="width:110px;">
        <input type="hidden" name="ClassId" value="<%=ClassDef.getClass_id()%>">
        <input type="hidden" name="class_user" value="<%=ClassDef.getClass_user()%>">
      </form>
      <tr><th>　</th><td>
      <font size="2px">メンバー検索：</font>
        <form action="./SearchStudent" method="post">
            <input type="text" name="stu_search" maxlength="20" minlength="1" pattern="[ぁ-んァ-ヶｦ-ﾟ一-龠a-zA-Z0-9\-\u30FC]+"/>
            <input type="radio" name="radiobutton" value="number"> <font size="2px">番号</font>
            <input type="radio" name="radiobutton" value="name"> <font size="2px">名前</font>
            <input type="hidden" value="update" name="type">
            <button text-align="center" name="regist_class" class="button" style="width: 70px; height:30px; padding:0px;">検索実行</button>
            <!--button text-align="center" name="regist_class" class="button" style="width: 70px; height:30px; padding:0px;">一覧表示</button-->
        </form>
        <form action="./UpdateClass" method="post">
            <input type="hidden" name="ClassId" value="<%=ClassDef.getClass_id()%>">
            <input type="hidden" name="class_user" value="<%=ClassDef.getClass_user()%>">
          <button text-align="center" name="search_stu" value="all" class="button" style="width: 70px; height:30px; padding:0px;">一覧表示</button>
        </form>
      </td></tr></table>
      <form action='./ClassTop' method='get'>
        <input type='submit' value='キャンセル' class="button_grey" id="btn_left">
      </form>
      <!--a href="./WEB-INF/classes/classTop.jsp"><button align="center" name="class_top">キャンセル</button></a-->
  </body>
</html>