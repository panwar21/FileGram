<%@ include file = "/WEB-INF/views/commons/header.jspf" %>

<%@ include file = "/WEB-INF/views/commons/navbar.jspf" %>

<div class="container">
<h2><i>${editMessage}</i></h2>
<h3> fileName : ${file.fileName}</h3></br>
<h3> fileType : ${file.fileType}</h3></br>
<h3> Date Uploaded : ${file.dateCreated}</h3></br>
<h3> tags : <c:forEach items="${file.tagsList}" var="tag">
<a href="${pageContext.request.contextPath}/tag/${tag}">${tag}</a>
</c:forEach>
</h3>

<h3> No Of Likes : file.noOfLikes</h3></br>

<h3> uploader: <a href="${pageContext.request.contextPath}/profile/${uploader}/viewProfile">${uploader}</a>
</h3>
</br>
</br>
<c:choose>
  <c:when test="${isFileLikedByUser}">
    <h3><i>Liked</i></h3>
        <c:url var="unlikeUrl" value="${file.id}/unlike"/>
		<form action="${unlikeUrl}" method="post">	
		  <input type="submit" value="Unlike" />
		  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
  </c:when>
  <c:otherwise>
    <c:url var="likeUrl" value="${file.id}/like"/>
	<form action="${likeUrl}" method="post">	
	  <input type="submit" value="Like" />
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
  </c:otherwise>
</c:choose>


</br>
<h3><a href="editDetails/${file.id}">Edit File</a></h3></br>
<h3><a href="download/${file.id}">Download File</a></h3></br> 

<c:url var="deleteUrl" value="delete/${file.id}"/>
<form action="${deleteUrl}" method="post">
  <input type="submit" value="Delete File" />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</br>



<div>
		<table class="table table-striped">
					<caption>Comments</caption>
		
					<thead>
						<tr>
							
						</tr>
					</thead>
		
					<tbody>
						<c:forEach items="${file.commentsList}" var="comment">
					    <tr>
					        <td>
					            ${comment.commentContent} - 
					            <a href="${pageContext.request.contextPath}/profile/${comment.user}/viewProfile">${comment.user}</a>
					              <i>${comment.dateCreated}</i>
					            
					        </td>
					    </tr>
					    </c:forEach>
					</tbody>
		</table>
	</div>


<form class="form-horizontal" action="${file.id}/comment" method = "post">
	
		<h2>add comment</h2>
	  <div class="form-group">
	    <label class="control-label col-sm-2" for="commentContent">Comment:</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="commentContent" placeholder="commentContent">
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