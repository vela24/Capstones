<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- <link rel="stylesheet" href="css/wickedcss.min.css">
<jsp:include page="animation.css"/> --%>
<c:import url="/WEB-INF/jsp/header.jsp" />

<style>
.img-responsive1{
 width: 400px;
  height: 300px;
  align: left;
}
.grow-pic-recipes-jsp1 {
  height: 400px;
  width: 400px;

} 
</style>

<div class="container-fluid bg-1 text-center jumbotron">
	<h4 class="white-font">Recipes Page</h4>

		<!-- First Container -->
		<div class="container-fluid bg-1 text-center ">
		<c:url var="formAction" value="/categoryAndTagAndNameSearch" />
<form method="GET" action="${formAction}">

                    <div class="search-bar input-group">
                        <input class="form-control" placeholder="Search For Recipes"
                            name="search" id="search" type="text"> <span
                            class="input-group-btn ">
                            <button class="btn btn-default" type="submit">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </span>
                    </div>

                </form>
		</div>

		<!-- second Container (Grid) -->
       <br>
		<c:url var="productImageUrl" value="/img/${recipe.recipeName}.jpg" />
				<c:url var="productDetailUrl" value="/recipeDetails">
					<c:param name="recipeId" value="${recipe.recipeId}" />
				</c:url>
				<div>
					<c:url var="imgUrl" value="/image/${recipe.recipeId}" />
			<c:url var="productImageUrl" value="img/${recipe.imagePath}.jpg" />
				<c:url var="productDetailUrl" value="/recipeDetails">
					<c:param name="recipeId" value="${recipe.recipeId}" />
					<c:param name="recipeId" value="${recipe.recipeId}" />
				</c:url>
			
			<c:choose>
					<c:when test="${recipe.userUploadedImage eq true}">
					<!-- <div class="grow-pic-recipes-jsp"> //text to take out picture growing--> 
						<div class="text-left">
						<img class="img-responsive1" src="${imgUrl}" alt="Image" />
						</div>
					</c:when>
					<c:otherwise>
						<div class="grow-pic-recipes-jsp1">
							<img class="img-responsive margin img-size" src="${productImageUrl}" alt="Image" /> 
							<br /> <br /> <br />
						</div>
					</c:otherwise>
					</c:choose>
				<%-- <div class="recipe-detail-img ">
				<a href="${productDetailUrl}"><img class="container-fluid margin" 
					 src="${productImageUrl}" alt="Image" /></a>
  					<br>
				</div> --%>
				</div>
				<br>

		
		<div class="white-font">
			<ul class="detail-larger recipe-details ">
<!-- 			I took out the class detail-smaller to make the text smaller
 -->			<li><h4 class="white-font"><strong>Recipe Name:</strong></h4> <h4 class="white-font">${recipe.recipeName}</h4></li>
				<br>
				<li><h4 class="white-font"><strong>Serving size:</strong></h4> <h4 class="white-font">${recipe.servingSize} person(s)</h4></li>
				<br>
				<li><h4 class="white-font"><strong>Preparation time:</strong></h4> <h4 class="white-font">${recipe.prepTime} minutes</h4></li>
				<br>
				<li><h4 class="white-font"><strong>Ingredients:</strong></h4> <ul><h4 class="white-font">${recipe.ingredients}</h4></ul></li>
				<br>
				<li><h4 class="white-font"><strong>Cooking instructions:</strong></h4> <ul><h4 class="white-font">${recipe.instructions}</h4></ul></li>		
				<br>			
				<li><h4 class="white-font"><strong>Tags:</strong></h4></li>
				<br>
			<c:forEach var="tag" items="${tags}">
				<li> <h4 class="white-font">${tag.tagName}</h4></li>
			</c:forEach>
				
				
				
				
			</ul>
					<c:if test="${recipe.adminCreated eq true}">
					<div class="logo-detail">
						<img src="img/approved-logo.png" class="img-responsive margin" style="width: 40%; height: 40%; padding-top: -300px; color: #fff;" alt="Image">
					</div>
					</c:if>
		</div>
		<br>
		<br>
		<br>
		<br>
		<!-- Third Container (Grid) -->
		<div class="container-fluid bg-3 text-center"
			id="extended-content-container">
			
			<br>
			<div class="row">
				<div class="col-sm-4">
					<p>Anthony what you feelin?</p>
					<img src="img/lowcarbpic.jpg" class="img-responsive margin"
						style="width: 100%" alt="Image">
				</div>
				<div class="col-sm-4">
					<p>Tad what you feelin bruh?</p>
					<img src="img/meatketopic.jpg" class="img-responsive margin"
						style="width: 100%" alt="Image">
				</div>
				<div class="col-sm-4">
					<p>Matt what you feelin bruh?</p>
					<img src="img/pexels-photo-370984.jpg"
						class="spinner img-responsive margin" style="width: 100%" alt="Image">
				</div>
			</div>
		</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />