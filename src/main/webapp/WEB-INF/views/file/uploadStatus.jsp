<%@ include file = "/WEB-INF/views/commons/header.jspf" %>

<%@ include file = "/WEB-INF/views/commons/navbar.jspf" %>

<div class="container">


<h1>User in session - ${userName}</h1>
<h2>Upload Status is </h2>
<h2>${uploadStatus}</h2>

<br/>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
  <input type="submit" value="Log out" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>


</div>

<%@ include file = "/WEB-INF/views/commons/footer.jspf" %>