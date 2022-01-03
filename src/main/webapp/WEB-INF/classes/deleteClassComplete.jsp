<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    <p align="right">ユーザーID　${User.id}</p>
    <h2 align="center">クラスを削除しました</h2>
    <!--a href="./student/registStudentTop.jsp"><button align="center" name="regist_top">児童・生徒一覧へ戻る</button></a-->
    <form action="./ClassTop" method="get">
        <input type="submit" value="クラストップへ戻る" class="button-red">
    </form>
  </body>
</html>