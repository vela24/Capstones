<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="common/header.jspf"%>

	<c:forEach var="park" items="${parks}">
		<div class="container">
			<div class="item1">
				<c:url var="parkHref" value="/parkDetail"> 
					<c:param name="parkcode">${park.parkCode}</c:param>
				</c:url>
				
				<a href="${parkHref}">
					<img src="img/parks/${fn:toLowerCase(park.getParkCode())}.jpg" />
				</a>
			</div>
			<div class="item2">
				<h3><c:out value="${park.getParkName()}"/></h3>
				<p><c:out value="${park.getDescription()}" /></p>
			</div>
			
		</div>
		<hr/>
	</c:forEach>

<%@include file="common/footer.jspf"%>