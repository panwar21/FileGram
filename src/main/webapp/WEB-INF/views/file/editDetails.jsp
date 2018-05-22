<%@ include file = "/WEB-INF/views/commons/header.jspf" %>

<%@ include file = "/WEB-INF/views/commons/navbar.jspf" %>

<div class="container">


<form class="form-horizontal" action="" method = "post">
	
		<h2>Edit file details</h2>
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="fileName">FileName:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="fileName" placeholder="File Name"
	      value="${file.fileName}">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="tags">Tags:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="tags" placeholder="Tags" value="${file.tagsList}">
	    </div>
	  </div>
	  
	  

	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	
	  <div class="form-group"> 
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">Submit</button>
	    </div>
	  </div>
	</form>





<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
  <input type="submit" value="Log out" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>


</div>

<%@ include file = "/WEB-INF/views/commons/footer.jspf" %>