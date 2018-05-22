<%@ include file = "/WEB-INF/views/commons/header.jspf" %>

<%@ include file = "/WEB-INF/views/commons/navbar.jspf" %>

<div class="container">

<h2>login into filegram</h2>
<!-- Relative Paths 
${pageContext.request.contextPath} = /Filegram - application root
/ - /localhost
"" - controller path
-->
<form action="<c:url value='j_spring_security_check' />" method = "post">
<!-- <form action="${loginUrl}" method = "post"> -->
<fieldset class="form-group">
  User name:<br>
  <input type="text" name="userName">
  <br>
  Password:<br>
  <input type="text" name="passWord">
  <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
  <br><br>
</fieldset>
  <input class ="btn btn-success" type="submit" value="Submit">
</form> 

<p>click submit to login</p>
</br>
</br>
</br>
</br>
New user - <a class="btn" href="profile/signup">sign up</a>
</div>

<%@ include file = "/WEB-INF/views/commons/footer.jspf" %>