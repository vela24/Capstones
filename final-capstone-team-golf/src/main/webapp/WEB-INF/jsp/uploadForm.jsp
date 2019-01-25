<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />
<br><br><br><br><br><br>
<div class="container-fluid bg-1 item img-size-box">

	<c:url value="/uploadFile" var="submitPhoto"/>
	 <form method="POST" action="${submitPhoto}" enctype="multipart/form-data" >
	    File to upload: <input type="file" name="file" >
	    <input type="hidden" name="recipeId" value="${param.recipeId}"/>
	    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
	    
	    <br />	
	    <br />
	    <input type="submit" value="Upload" id="submit-button" class="form-control">
	</form>
	<%-- <c:param name="recipeId" value="${param.recipeId}" />
	 --%><c:if test="${not empty message}">
	    ${message} 
	</c:if>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />