<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:import url="/WEB-INF/jsp/header.jsp" />

<br><br><br><br><br>
<script type="text/javascript">
	$(document).ready(function () {
		$.validator.addMethod('capitals', function(thing){
			return thing.match(/[A-Z]/);
		});
		$.validator.addMethod("Email", function(value, index) {
	        return value.toLowerCase().endsWith(".com")||value.toLowerCase().endsWith(".org")||value.toLowerCase().endsWith(".net")||value.toLowerCase().endsWith(".gov")||value.toLowerCase().endsWith(".edu"); 
	        /* return thing.match(/\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b/); */
	        
	    }, "Please enter a valid email address");

		$("form").validate({
			
			rules : {
				userName : {
					required : true,
					email: true,
					Email: true
					
				},
				password : {
					required : true,
					minlength: 15,
					capitals: true,
				},
				confirmPassword : {
					required : true,		
					equalTo : "#password"  
				}
			},
			messages : {			
				password: {
					minlength: "Password too short, make it at least 15 characters",
					capitals: "Field must contain a capital letter",
				},
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	});
</script>

<c:url var="formAction" value="/users" />
<form:form id="form" method="POST" action="${formAction}">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
	<div class="container-fluid bg-1 item img-size-box">
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-10 login-margin-input">
			<div class="form-group">
				<label for="userName" style="color: #fff;">User Name: </label>
				<input type="text" id="userName" name="userName" placeHolder="User Name" class="form-control" />
				<form:errors path="userName" cssClass="error" style="color:red"/>
			</div>
			<div class="form-group">
				<label for="password" style="color: #fff;">Password: </label>
				<input type="password" id="password" name="password" placeHolder="Password" class="form-control" />
				<form:errors path="password" cssClass="error" style="color:red"/> 
			</div>
			<div class="form-group">
				<label for="confirmPassword" style="color: #fff;">Confirm Password: </label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeHolder="Re-Type Password" class="form-control" />
				<form:errors path="confirmPassword" cssClass="error" style="color:red"/>	
			</div>
			<button type="submit" class="btn btn-default">Create User</button>
		</div>
		</div>
		<div class="col-sm-2"></div>
	</div>
</form:form>
<br>
<br>
<br>
<br>
		
<c:import url="/WEB-INF/jsp/footer.jsp" />
