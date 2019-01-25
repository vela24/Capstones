<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:import url="/WEB-INF/jsp/header.jsp" />
<br><br><br><br><br><br><br><br><br>
<div class="container-fluid bg-1 text-center jumbotron">

<h1>Recipe Search Results</h1> 
<!-- First Container -->
		<div class="container-fluid bg-1 text-center ">
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

<div class="container">
		<div class="container">
			<div class="jumbotron bg-1">
				
			</div>
			
			<c:forEach var="recipe" items="${recipes}">
				<table class="col-sm-12 navbar detail-larger">
					<tr>
					<td>
						<c:url var="productImageUrl" value="/img/${recipe.recipeName}.jpg" />
						<c:url var="productDetailUrl" value="/recipeDetails">
							<c:param name="recipeId" value="${recipe.recipeId}" />
						</c:url>
						<div class="grow-pic-recipes-jsp">
						<a href="${productDetailUrl}"><img class="img-responsive margin img-size" 
							 src="${productImageUrl}" alt="Image" /></a>
		  					<br>
		  					<br>
		  					<br>
		  				</div>
		  			</td>
		  			<td class="white-font" style="font-size: 18px;">${recipe.recipeName}</td>
		  			</tr>	
	  			</table>
			</c:forEach>
		</div>
 </div>	 
</div>
<br><br><br><br><br><br><br><br><br>

<!-- Third Container (Grid) -->
	<div class="container-fluid bg-3 text-center">
		<div class="row">
			<div class="col-sm-4">
				<p>Pan roasted always better.</p>
				<img src="img/how-to-roast-vegetables.jpg" class="img-responsive margin"
					style="width: 100%" alt="Image">
			</div>
			<div class="col-sm-4">
				<p>Carrots packed with vitamins!</p>
				<img src="img/carrots-vegetables.jpg" class="img-responsive margin"
					style="width: 100%" alt="Image">
			</div>
			<div class="col-sm-4">
				<p>Wine...well it's wine.</p>
				<img src="img/pexels-photo-370984.jpg" class="img-responsive margin"
					style="width: 100%" alt="Image">
			</div>
		</div>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<c:import url="/WEB-INF/jsp/footer.jsp" />
