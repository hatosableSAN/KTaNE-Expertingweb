<%@ page isErrorPage="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
<html>
<section class="page_500">

    
      <h1 style="text-align: center;">500 Internal Server Error</h1>
    
    <p style="text-align: center;"><br><br>サーバー上でエラーが発生しました。<br/><br/>
      <a href="./CheckLogout" class="btn">ログアウトしてトップページへ</a>
      <button onclick=window.history.back(); return false; class="btn">一つ前のページへ戻る</a></button>
    </p>
    <div style="font-size: 50%;color: gray;text-align: center;visibility: hidden;">
      [エラーの詳細]</p>
    <%=exception%>
   <% exception.printStackTrace(new java.io.PrintWriter(out));  %>
   <br/>
    </div>
    <br/>
    
 
  </div>
    </div>
    </div>
    </div>
  </div>
</section>

</html>


