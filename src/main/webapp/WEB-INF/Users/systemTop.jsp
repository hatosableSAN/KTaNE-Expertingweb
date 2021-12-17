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
    <a href="./WEB-INF/student/studentTop.jsp"><button>
        児童生徒管理
      </button></a>
      <a href="./WEB-INF/classes/classTop.jsp"><button>
        クラス管理
      </button><br/><br/><br/><br/>
      <a href="./SeatingTop"><button>
        座席配置管理
      </button></a>
        <a href="./GradeTop"><button>
          評価管理
      </button>
    </p>
  </body>
</html>