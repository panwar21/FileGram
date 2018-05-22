<%@ include file = "/WEB-INF/views/commons/header.jspf" %>

<%@ include file = "/WEB-INF/views/commons/navbar.jspf" %>

<div class="container">


	<h1>User in session - ${userName}</h1>
	<h1>Filegram upload a file</h1>
	
	<form action="upload" method="POST"  enctype="multipart/form-data">
		<fieldset class="form-group">
		    Choose File<input type="file" name="fileName" /><br/>
		    Tags<input type="text" name="tagsString" /><br/>
		</fieldset>    
		    <input class ="btn btn-success"type="submit" value="Submit" />
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
	</form>
	
	<br/>
	
	<c:url var="logoutUrl" value="/logout"/>
	<form action="${logoutUrl}" method="post">
	  <input type="submit" value="Log out" />
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

</div>


<%@ include file = "/WEB-INF/views/commons/footer.jspf" %>