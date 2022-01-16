<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	File file=(String)request.getParameter("select");
%>
<%=file%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8" />
    <title>File API 1</title>
    </head>
    <body>
      <form>
      <input type="file" name="select" />
      <input type="submit" value="送信">
        </form>
    </body>
  </html>