<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<style>
.box-size1 {
    height:150px;
    width:500px;
    font-size:12pt;
    border-radius: 7px; 
}
</style>



<script type="text/javascript">
	$(document).ready(function() {

		$("#error-input1").validate({

			rules : {
				recipeName : {
					required : true
				},
				servingSize : {
					required : true,
					number : true
				},
				prepTime : {
					required : true,
					number : true
				},
				ingredients : {
					required : true
				},
				instructions : {
					required : true
				}
			},
			messages : {

			},
			errorClass : "error"
		});
	});
</script>

<section class="bg-1">

	<c:url value="/recipeCreationPage" var="recipePage" />

	<form:form class="container" id="error-input1" action="${recipePage}"
		method="POST" modelAttribute="recipe">
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
		<!-- have this for every form we have!!!! -->
		<div class="row">
			<div class="col-sm-4">
								<br><br><br><br>
				<label class="white-font" for="recipeName">Recipe Name</label>
				<div>
					<form:input path="recipeName" placeHolder="Recipe name"
						class="form-control" id="recipeName" />
					<!--name=?  -->
					<form:errors path="recipeName" cssClass="error" style="color:red" />
				</div>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-4">
				<label class="white-font" for="servingSize">Serving Size</label>
				<div>
					<form:input path="servingSize" placeHolder="Number of servings."
						class="form-control" id="servingSize" />
					<form:errors path="servingSize" cssClass="error" style="color:red" />
				</div>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-sm-4">
				<label class="white-font" for="prepTime">Prep Time</label>
				<div>
					<form:input path="prepTime" placeHolder="Time in minutes."
						class="form-control" id="prepTime" />
					<form:errors path="prepTime" cssClass="error" style="color:red" />
				</div>
			</div>
		</div>
		<br>
		<div>
			<label class="white-font" for="ingredients">Ingredients Needed</label>
			<div>
			<div>

					<form:textarea path="ingredients"
					placeHolder="Ingredients separated by a comma" id="ingredients"
					class="box-size1 grey-font" rows="5"></form:textarea>
					</div>
				<form:errors path="ingredients" cssClass="error" style="color:red" />
				<%-- <form:errors path="passwordMatching" cssClass="error" style="color:red"/> --%>
			</div>
		</div>
		<br>
		<div>
			<label class="white-font" for="instructions">Cooking Instructions</label>
			<div>
					
					<form:textarea path="instructions"
					placeHolder="Ingredients separated by a comma" id="instructions"
					class="box-size1 grey-font" rows="5"></form:textarea>
				<form:errors path="instructions" cssClass="error" style="color:red" />
			</div>
		</div>
		<br>
		<!--///////////uplaod photo code  -->
<!-- 		<input class="white-font" type="file" onchange="previewFile()">
		<br>
		<img src="" height="200" class="grey-font" alt="Image preview...">
		<script id="submit-button" type="text/javascript">
			function previewFile() {
				var preview = document.querySelector('img'); //selects the query named img
				var file = document.querySelector('input[type=file]').files[0]; //sames as here
				var reader = new FileReader();

				reader.onloadend = function() {
					preview.src = reader.result;
				}

				if (file) {
					reader.readAsDataURL(file); //reads the data as a URL
				} else {
					preview.src = "";
				}
			}

			previewFile(); //calls the function named previewFile()
		</script>
 -->
		<!-- 	///////tag box -->
		<div class="form-group">

			<div id="tagsDiv">
				<br />
			</div>
			<div class="form-group">
				<label class="white-font" > Add Tags</label> <input type="text" name="tags"
					id="testInput" placeholder="Tags"
					class="typeahead tm-input form-control tm-input-info"
					onchange="appendTagsToDiv(this)" />
			</div>

			<!-- ///////tag box end -->

		</div>
		<div>
			<input type="submit" value="Submit!" id="submit-button" />
		</div>
		<div class="col-sm-4"></div>
		<br>
		<br>
		<br>
	</form:form>
</section>
<br><br><br><br><br><br><br><br><br><br><br><br>
<c:import url="/WEB-INF/jsp/footer.jsp" />