<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    <h1 align="center">児童・生徒登録</h1>
    <br>
    <form action="./RegistStudentHand" method="post">
        番号　<font color="red">＊</font>：
        <input type="text" name="stu_id" placeholder="半角英数字(6~15文字)" maxlength="15" minlength="6" pattern="^[0-9A-Za-z]+$"/>
        
        名前　<font color="red">＊</font>：
        <input type="text" name="stu_name" placeholder="(1~20文字)" maxlength="20" minlength="1" pattern="^[ぁ-ん]+$ , [\u3041-\u309F]*+^[ァ-ンヴー]+$ , [\u30A1-\u30FF]*+"/>
        
        性別　：<select name="stu_gender">
            <option value="0">男</option>
            <option value="1">女</option>
            <option value="2">その他</option>
            </select><br />
        <br />
        <h5><font color="red">＊は必須項目です。すべて入力し、確認を押してください</font></h5>
        <input type="submit" value="確認" name="OK" />
      </form>
      <br>
      <a href="./student/registStudentTop.jsp"><button align="center" name="return_regist">児童・生徒登録へ戻る</button></a>
      <br />
  </body>
</html>