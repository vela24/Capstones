<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />
<br><br><br><br><br><br><br>
	<div class="container">
		<div class="jumbotron bg-1">
			<h3>Recipes Approval Page</h3>
			<c:forEach var="recipe" items="${recipes}">
				<table>
					<tr>
					<td>
							<ul>
								
								<li><b>Recipe Name</b><br></li>
								<li>${recipe.recipeName}</li>
								<li><b>Serving Size</b><br></li>
								<li>${recipe.servingSize}</li>
								<li><b>Preparation Time</b><br></li>
								<li>${recipe.prepTime}</li>
								<li><b>Ingredients</b><br></li>
								<c:forTokens items="${recipe.ingredients}" delims=","
									var="mySplit">
									<c:out value="${mySplit}"/>
									<br/>
								</c:forTokens>
								<li><b>Instructions</b></li>
								<li>${recipe.instructions}</li>
							</ul> <c:url value="/approveRecipes" var="approve" />
							<form action="${approve}" method="POST">
							<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
								<input type="hidden" id="recipeId" name="recipeId"
									value="${recipe.recipeId}">
								<button class="btn btn-default" type="submit" name="approve"
									value="approve">Approve<br/></button><br/><br>
									</form>
						</td>
				</table>
			</c:forEach>
		</div>
	</div>
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	
	

	

	<!-- Third Container (Grid) -->
	<div class="container-fluid bg-3 text-center">
		<h3 class="margin"> ""~<o>~"" </h3>
		<br>
		<div class="row">
			<div class="col-sm-4">
				<p>Are you getting enough fiber?</p>
				<img src="img/lowcarbpic.jpg" class="img-responsive margin"
					style="width: 100%" alt="Image">
			</div>
			<div class="col-sm-4">
				<p>Keep track of your albumin!</p>
				<img src="img/meatketopic.jpg" class="img-responsive margin"
					style="width: 100%" alt="Image">
			</div>
			<div class="col-sm-4">
				<p>Are you getting good sources of protein?</p>
				<img src="img/pexels-photo-370984.jpg" class="img-responsive margin"
					style="width: 100%" alt="Image">
			</div>
		</div>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	 font-size: 20px;
	<c:import url="/WEB-INF/jsp/footer.jsp" />


