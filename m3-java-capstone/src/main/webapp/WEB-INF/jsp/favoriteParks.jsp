<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="common/header.jspf"%>

<section class="centeredPanel">
	<table>
		<c:forEach var="result" items="${favorites}">
			<tr>
				<td><img src="img/parks/${fn:toLowerCase(result.getParkCode())}.jpg" alt="parkPic"/></td>
				<td><c:out value="${result.getParkName()}"/></td>
				<td><c:out value="${result.getSurveyCount()}"/></td>
			</tr>
		</c:forEach>
	</table>

</section>

<%@include file="common/footer.jspf"%>