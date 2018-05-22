<%@ include file = "/WEB-INF/views/commons/header.jspf" %>

<%@ include file = "/WEB-INF/views/commons/navbar.jspf" %>

<div class="container">
<h1><i>${message}</i></h1>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
	  <input class = "btn" type="submit" value="Log out" />
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</div>

<%@ include file = "/WEB-INF/views/commons/footer.jspf" %>