<%@ include file = "/WEB-INF/views/commons/header.jspf" %>

<%@ include file = "/WEB-INF/views/commons/navbar.jspf" %>

<div class="container">

	<h3>welcome <i>${userName}</i></h3>
	
	<br/>
	
	login tried by username - <%= request.getSession().getAttribute("userName") %>
	
	
	<br/>
	<a class="btn" href = "file/upload">upload file</a>
	</br>
	</br>
	<a class="btn" href = "profile/profile">view profile</a>
	</br>
	</br>
	<c:url var="logoutUrl" value="logout"/>
	<form action="${logoutUrl}" method="post">
	  <input class = "btn" type="submit" value="Log out" />
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	
	</br>
	<h2><b>files uploaded : ${noOfFiles}</b></h2>
	<div>
		<table class="table table-striped">
					<caption>List of Files</caption>
		
					<thead>
						<tr>
							<th>Name</th>
							<th>Type</th>
							<th>Tags</th>
							<th>Date Uploaded</th>
							<th>File Details</th>
						</tr>
					</thead>
		
					<tbody>
						<c:forEach items="${listOfFiles}" var="file">
					    <tr>
					        <td>
					            <c:out value="${file.fileName}" />
					        </td>
					        
					        <td>
					            <c:out value="${file.fileType}" />
					        </td>
					        
					        <td>
					        <c:forEach items="${file.tagsList}" var="tag">
                				<a href="${pageContext.request.contextPath}/tag/${tag}">${tag}</a>
           					 </c:forEach>
					        </td>
					        
					        <td>
					            <c:out value="${file.dateCreated}" />
					        </td>
					        <td>
					        
					            <a href="file/${file.id}">File Details</a>
					        </td>
					    </tr>
					    </c:forEach>
					</tbody>
		</table>
	</div>
	
	
</div>

<%@ include file = "/WEB-INF/views/commons/footer.jspf" %>



