<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<!-- <div class="background-image"></div> -->
<head>
<title>Healthy Menu Planner</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"
	type="text/javascript"></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.js "
	type="text/javascript"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.timeago/1.4.1/jquery.timeago.min.js"
	type="text/javascript"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"
	type="text/javascript"></script>

<c:url var="cssHref" value="/css/main.css" />
<link rel="stylesheet" type="text/css" href="${cssHref}">

<script type="text/javascript">
	$(document).ready(function() {
		$("time.timeago").timeago();

		$("#logoutLink").click(function(event) {
			$("#logoutForm").submit();
		});

		var pathname = window.location.pathname;
		$("nav a[href='" + pathname + "']").parent().addClass("active");

	});
</script>

</head>
<body class="background-image"> 
	<header>
		<c:url var="homePageHref" value="/" />
		<c:url var="recipePageHref" value="/recipes" />

		<%-- <a href="${homePageHref}"><img src="${imgSrc}" class="img-responsive" /></a> --%>
	</header>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<c:url var="homePageHref" value="/" />
				<li class="nav-item"><a href="${homePageHref}">Home</a></li>
				<li class="nav-item"><a href="${recipePageHref}">Recipes</a></li>
				

				<c:if test="${not empty currentUser}">
					<c:choose>
						<c:when test="${userName.admin == false}">
							<c:url var="createRecipeHref" value="recipeCreationPage" />
							<li><a href="${createRecipeHref}">Create Recipe</a></li>

						</c:when>
						<c:otherwise>
							<c:url var="createRecipeHref" value="recipeAdminCreationPage" />
							<li><a href="${createRecipeHref}">Create Recipe</a></li>
						</c:otherwise>
					</c:choose>
					<c:url var="changePasswordHref" value="/passwordReset" />
					<li><a href="${changePasswordHref}">Change Password</a></li>
					<c:if test="${userName.admin == true}">
						<c:url var="approveRecipesHref" value="approveRecipes" />
						<li><a href="${approveRecipesHref}">Approve Recipes</a></li>
					</c:if>
				</c:if>
				<c:if test="${not empty currentUser}">
		<p id="currentUser"
			style="text-align: center; font-size: 30px; color: #4a4847;">Hello
			${currentUser}</p>
	</c:if>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${empty currentUser}">

						<c:url var="newUserHref" value="/users/new" />
						<li><a href="${newUserHref}">Sign Up</a></li>

						<c:url var="loginHref" value="/login" />
						<li><a href="${loginHref}">Log In</a></li>

					</c:when>
					<c:otherwise>

						<c:url var="logoutAction" value="/logout" />
						<%-- <li><a href="${loginHrefout}">Log Out</a></li> --%>
						<!--Ask about fixing the log out text styling  -->
						<form id="logoutForm" action="${logoutAction}" method="POST">
							<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
							<button class="btn btn-link logout-button" role="link" type="submit" name="op"
								value="Log Out">Log Out</button>
						</form>
					</c:otherwise>
				</c:choose>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav navbar-right">
						<li><a
							href="http://cancersupportohio.org/programs-and-services/cooking-for-wellness-recipe-archives/">Cancer
								Support Ohio</a></li>
					</ul>
				</div>
			</ul>
		</div>
	</nav>


	<!-- transparent -->
</body>
</html>