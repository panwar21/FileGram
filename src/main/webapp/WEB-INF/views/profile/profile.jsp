<%@ include file = "/WEB-INF/views/commons/header.jspf" %>

<%@ include file = "/WEB-INF/views/commons/navbar.jspf" %>

<div class="container">
<h1><i>${message}</i></h1>
<h1>Profile details for : <%= request.getSession().getAttribute("userName") %></h1>
<h3>${emailId}</h3></br>
<h3>${firstName}</h3></br>
<h3>${lastName}</h3></br>
<h3>${phoneNo}</h3></br>
<h3>${address}</h3></br>

<a class = "btn" href="edit">Edit profile</a>

<c:url var="deleteProfile" value="delete"/>
<form action="${deleteProfile}" method="post">
	  <input class = "btn" type="submit" value="delete profile" />
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
	  <input class = "btn" type="submit" value="Log out" />
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</div>

<%@ include file = "/WEB-INF/views/commons/footer.jspf" %>