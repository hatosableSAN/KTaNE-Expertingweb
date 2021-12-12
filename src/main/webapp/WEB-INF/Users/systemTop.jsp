<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- beans.Userをimportする -->
<!--%@ page import= "beans.User" %-->
<html>
    <head>
        <link rel="stylesheet" href="../style.css">
    </head>
  <body>
    <p align="right">ID ${User.id}</p>
    <div class="darkgray"><h2 style="text-align:center">児童・生徒評価システムトップ</h2></div>
    <br/><br/><br/><br/>
    <p align="center">
    <a href="../student/studentTop.jsp"><button>
        児童生徒管理
      </button></a>
      <a href="../classes/classTop.jsp"><button>
        クラス管理
      </button><br/><br/><br/><br/>
      <a href="./RegistSeatingClass"><button>
        座席配置管理
      </button></a>
      <button>
        このボタンは<big><b><font color="red">サンプル</font></b></big>のため動作しません
      </button>
    </p>
  </body>
</html>