<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

<%@include file="common/header.jspf"%>
	
	<img class="detail_img" src="img/parks/${fn:toLowerCase(park.getParkCode())}.jpg" />
	
	<br>
	
	<section class="centeredPanel">
		<h1>${park.parkName}, ${park.state}</h1>
		<table>
			<tr>
				<td>Acreage: ${park.acreage}</td> 
			</tr>
			<tr>
				<td>Elevation: ${park.elevationInFeet}</td>
			</tr>
			<tr>
				<td>Trail miles: ${park.milesOfTrail}</td>
			</tr>
			<tr>
				<td>Number of Campgrounds: ${park.milesOfTrail}</td>
			</tr>
			<tr>
				<td>Park Climate: ${park.climate}</td>
			</tr>
			<tr>
				<td>Year Founded: ${park.yearFounded}</td>
			</tr>
			<tr>
				<td>Visitors per Year: ${park.annualVisitorCount}</td>
			</tr>
			<tr>
				<td>Famous Quote: <br> ${park.quote}  ~ ${park.quoteSource}</td>
			</tr>
			<tr>
				<td>Entry Fee: $${park.entryFee} </td>
			</tr>
			<tr>
				<td>Number of Animal Species in the Park: ${park.numOfAnimalSpecies}</td>
			</tr>
			<tr>
				<td>Brief Description: ${park.description}</td>
			</tr>
		</table>
		
		
		<section id="weather_display">
		
			<ul>
				<c:forEach var="weather" items="${weatherList}">
					
					<div>
						<c:choose>
							<c:when test="${weather.getDay() == 1 }">
								<li><img style="max-height: 250px; max-width:200px; float: left; margin-right:10px;" src="img/weather/${fn:replace(weather.getForecast(), ' c', 'C')}.png" alt="${weather.getForecast()}" /></li>
							</c:when>
							<c:otherwise>
								<li><img class="weather_img" src="img/weather/${fn:replace(weather.getForecast(), ' c', 'C')}.png" alt="${weather.getForecast()}" /></li>
							</c:otherwise>
						</c:choose>
					
						<ul>
							<c:if test="${temp == 'F'}">
								<li><fmt:formatNumber type="number" pattern="#" value="${weather.getLowTemp()}"/>&deg F</li>
								<li><fmt:formatNumber type="number" pattern="#" value="${weather.getHighTemp()}"/>&deg F</li>
							</c:if>	
							
							<c:if test="${temp == 'C'}">
								<li><fmt:formatNumber type="number" pattern="#" value="${weather.getLowTemp() - 32 * 1.8}" />&deg C</li>
								<li><fmt:formatNumber type="number" pattern="#" value="${weather.getHighTemp() - 32 * 1.8}"/>&deg C</li>
							</c:if>	
							
							<c:choose>
								<c:when test="${weather.getForecast() == 'snow' }">
									<li>Pack snowshoes!</li>
								</c:when>
								<c:when test="${weather.getForecast() == 'rain' }">
									<li>Pack rain gear and wear waterproof shoes!</li>
								</c:when>
								<c:when test="${weather.getForecast() == 'thunderstorms' }">
									<li>Seek shelter and avoid hiking on exposed ridges!</li>
								</c:when>
								<c:when test="${weather.getForecast() == 'sunny' }">
									<li>Pack Sunblock!</li>
								</c:when>
							</c:choose>
						
							<c:if test="${weather.getHighTemp()  > 75}">
								<li>Bring an extra gallon of water.</li>
							</c:if>
							
							<c:if test="${weather.getLowTemp()  < 20}">
								<li>Danger: Exposure to Frigid Temperatures.</li>
							</c:if>
							
							<c:if test="${weather.getHighTemp() - weather.getLowTemp() >= 20}">
								<li>Wear breathable layers.</li>
							</c:if>
							
						</ul>
					</div>
				</c:forEach>
			</ul>
			
			<c:url var="celUrl" value="/changeToCelcius"></c:url>
			<c:if test="${temp == 'F' || empty temp}">
			
				<form method="post" action="${celUrl}">
					<button type="submit" name="tempF" value="C">Change to Celsius</button>
					<input type="hidden" name="parkCode"  value="${park.getParkCode()}">
				</form>
			</c:if>
			
			<c:url var="farUrl" value="/changeToFarenheit"></c:url>
			<c:if test="${temp == 'C'}">
				<form method="post" action="${farUrl}">
					<button type="submit" name="tempF" value="F">Change to Farenheit</button>
					<input type="hidden" name="parkCode" value="${park.getParkCode()}">
				</form>
			</c:if>
		</section>
		
	</section>
	
<%@include file="common/footer.jspf"%>