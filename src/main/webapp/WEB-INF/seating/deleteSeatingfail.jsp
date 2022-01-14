<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import= "beans.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="${pagecontext.request.contextpath}/se21g1/style.css" type="text/css" >
  </head>
  <body>
    <div class="blue">
      <p align="right">ユーザーID　${User.id}</p>
      </div>
      <h2 align="center">座席配置の削除に失敗しました</h2>
      <div class="center">
    <%-- <a href="./SeatingTop"><button align="center" class="backbtn_middle" name="regist_top">座席配置メニュートップへ戻る</button></a>
    </div> --%>
    <div class="center">
    <a href="./manageSeatingTop"><button align="center" class="backbtn_middle"　name="regist_top">座席配置一覧へ戻る</button></a>
    </div>
  </body>
</html>