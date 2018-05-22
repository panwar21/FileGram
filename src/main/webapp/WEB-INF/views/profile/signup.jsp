<%@ include file = "/WEB-INF/views/commons/header.jspf" %>

<%@ include file = "/WEB-INF/views/commons/navbar.jspf" %>

<div class="container">
	
	<h3>${errorMessage}</h3>  
	<form class="form-horizontal" action="" method = "post">
	
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="userName">UserName:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="userName" placeholder="User Name">
	    </div>
	  </div>
	
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="email">Email:</label>
	    <div class="col-sm-10">
	      <input type="email" class="form-control" name="emailId" placeholder="Email Id">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="firstName">FirstName:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="firstName" placeholder="First Name">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="lastName">LastName:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="lastName" placeholder="Last Name">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="phoneNo">PhoneNo:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="phoneNo" placeholder="PhoneNo">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="address">Address:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="address" placeholder="Address">
	    </div>
	  </div>
	  
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="pwd">Password:</label>
	    <div class="col-sm-10"> 
	      <input type="password" class="form-control" name="passWord" placeholder="Password">
	    </div>
	  </div>
	
	<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	
	  <div class="form-group"> 
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">Submit</button>
	    </div>
	  </div>
	</form>

</div>

<%@ include file = "/WEB-INF/views/commons/footer.jspf" %>