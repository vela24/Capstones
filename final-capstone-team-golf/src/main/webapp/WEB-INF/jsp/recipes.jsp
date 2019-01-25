<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:import url="/WEB-INF/jsp/header.jsp" />


<div class="container-fluid bg-1 text-center jumbotron">
		<!-- First Container -->
		
		<div class="container-fluid bg-1 text-center">
	 <h4>Search for Recipes</h4>
		<div class="container-fluid">
			<div class="right-addon">
	            <c:url var="formAction" value="/categoryAndTagAndNameSearch" />
					<form method="GET" action="${formAction}">
                    <div class="search-bar input-group">
                        <input class="container form-control" placeholder="Search For Recipes" name="search" id="search" type="text"> 
                        <span class="input-group-btn "><button class="btn btn-default" type="submit"><span class="glyphicon glyphicon-search"></span></button></span>
                    </div>
                </form>
			</div>
		</div>
	</div>

		<!-- second Container (Grid) -->
		
		<div class="container">
			
			
			<c:forEach var="recipe" items="${recipes}">
			<table class="col-sm-12  detail-larger-recipes-jsp">
			<tr>
			<td class="">
			<c:url var="imgUrl" value="/image/${recipe.recipeId}" />
			<c:url var="productImageUrl" value="img/${recipe.imagePath}.jpg" />
				<c:url var="productDetailUrl" value="/recipeDetails">
					<c:param name="recipeId" value="${recipe.recipeId}" />
					<c:param name="recipeId" value="${recipe.recipeId}" />
				</c:url>
			
			<c:choose>
					<c:when test="${recipe.userUploadedImage eq true}">
						<div class="grow-pic-recipes-jsp img-size">
						<a href="${productDetailUrl}"><img class="img-responsive margin img-size" src="${imgUrl}" alt="Image" /></a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="grow-pic-recipes-jsp">
							<a href="${productDetailUrl}"><img class="img-responsive margin img-size" src="${productImageUrl}" alt="Image" /></a> 
							<br /> <br /> <br />
						</div>
					</c:otherwise>
					</c:choose>
					</td>
  			<td class="white-font recipe-name-placing"><h4>${recipe.recipeName}</h4></td>	
  			</tr>
  			</table>	
			</c:forEach>
			
		</div>
		<br>
		<br>
		<br>
		<br>
</div>		
		<!-- Third Container (Grid) -->
		
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<!-- pics -->			
	<div class="container-fluid bg-3 text-center">
		<div class="row">
			<div class="col-sm-4">
				<p>You are what you EAT!</p>
				<img src="img/lowcarbpic.jpg" class="img-responsive margin"
					style="width: 100%" alt="Image">
			</div>
			<div class="col-sm-4">
				<p>Don't forget your protein!</p>
				<img src="img/meatketopic.jpg" class="img-responsive margin"
					style="width: 100%" alt="Image">
			</div>
			<div class="col-sm-4">
				<p>Ahh, a nightcap.</p>
				<img src="img/pexels-photo-370984.jpg" class="img-responsive margin"
					style="width: 100%" alt="Image">
			</div>
		</div>
	</div>
<br><br><br><br><br><br><br><br><br><br><br>

<!-- cool pic animation -->
<!-- <div class="container">
  <img src="(name of pic).jgp" alt="Avatar" class="image">
  <div class="overlay">
    <div class="text">Text that shows</div>
  </div>
</div>
<style>
.container {
  position: relative;
  width: 50%;
}

.image {
  display: block;
  width: 100%;
  height: auto;
}

.overlay {
  position: absolute;
  bottom: 100%;
  left: 0;
  right: 0;
  background-color: #008CBA;
  overflow: hidden;
  width: 100%;
  height:0;
  transition: .5s ease;
}

.container:hover .overlay {
  bottom: 0;
  height: 100%;
}

.text {
  white-space: nowrap; 
  color: white;
  font-size: 20px;
  position: absolute;
  overflow: hidden;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
}
</style> -->

<c:import url="/WEB-INF/jsp/footer.jsp" />