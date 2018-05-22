<%@ include file = "/WEB-INF/views/commons/header.jspf" %>

<%@ include file = "/WEB-INF/views/commons/navbar.jspf" %>

<div class="container">


	<h3>welcome <i>${userName}</i></h3>
	
	
	<br/>
	<h3> TagName : ${tag.tagName}</h3></br>
	<h3> Description : ${tag.description}</h3></br>
	<h3> Date Created : ${tag.dateCreation}</h3></br>
	<h3> No Of Files Tagged: ${tag.noOfFiles}</h3></br>
	
	
	</br>
	<c:url var="logoutUrl" value="logout"/>
	<form action="${logoutUrl}" method="post">
	  <input class = "btn" type="submit" value="Log out" />
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	
	</br>

	<div>
		<table class="table table-striped">
					<caption>List of Files Tagged</caption>
		
					<thead>
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Type</th>
							<th>Tags</th>
							<th>Date Uploaded</th>
							<th>File Details</th>
						</tr>
					</thead>
		
					<tbody>
						<c:forEach items="${tag.filesTaggedList}" var="file">
					    <tr>
					        <td>
					            <c:out value="${file.fileName}" />
					        </td>
					        
					        <td>
					            <c:out value="${file.description}" />
					        </td>
					        
					        <td>
					            <c:out value="${file.fileType}" />
					        </td>
					        
					        <td>
					            <c:out value="" />
					        </td>
					        <td>
					            <c:out value="${file.dateCreated}" />
					        </td>
					        <td>
					        
					            <a href="${pageContext.request.contextPath}/file/${file.id}">File Details</a>
					        </td>
					    </tr>
					    </c:forEach>
					</tbody>
		</table>
	</div>




</div>

<%@ include file = "/WEB-INF/views/commons/footer.jspf" %>