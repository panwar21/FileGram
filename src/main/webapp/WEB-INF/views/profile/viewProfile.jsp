<%@ include file = "/WEB-INF/views/commons/header.jspf" %>

<%@ include file = "/WEB-INF/views/commons/navbar.jspf" %>

<div class="container">

<h1>profile details of ${otherUserName}</h1></br>
<h3>Name : ${firstName} ${lastName}</h3></br>

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
					        
					            <a href="${pageContext.request.contextPath}/file/${file.id}">File Details</a>
					        </td>
					    </tr>
					    </c:forEach>
					</tbody>
		</table>
	</div>

</div>

<%@ include file = "/WEB-INF/views/commons/footer.jspf" %>