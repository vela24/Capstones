<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<br><br><br><br><br>

<script type="text/javascript" style="color: #838383;">
	$(document).ready(function () {
		$.validator.addMethod('capitals', function(thing){
			return thing.match(/[A-Z]/);
		});
	
		$("#changePasswordForm").validate({
			
			rules : {
				
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
				
				password :{
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

<div class="container-fluid bg-1 item img-size-box">
<h2 style="text-align: center; color: #ffffff; font-size: 20px;">Password Reset</h2>

<c:url var="formAction" value="/passwordReset" />

<div class="row">
	
		<c:if test="${param.success eq true}">
	<div class="alert alert-success">Password update was successful.</div>
	</c:if>
		<c:if test="${param.success eq false}">
	<div class="alert alert-danger">You cannot use the same password, please try again!</div>
	</c:if>
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<form action="${formAction}" method="POST" id="changePasswordForm">
		<input type="hidden" name="destination" value="${param.destination}"/>
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
			<div class="form-group">
				<label for="password" style="color: #ffffff;">New Password: </label>
				<input type="password" id="password" name="password" placeHolder="New Password" class="form-control" />
				<input type="hidden" name="userName" value="${currentUser}"/>	
			</div>
			<div class="form-group">
				<label for="confirmPassword" style="color: #ffffff;">Confirm Password: </label>
				<input type="password" id="confirmPassword" name="confirmPassword" placeHolder="Confirm Password" class="form-control" />	
			</div>
			<button type="submit" class="btn btn-default">Change Password</button>
		</form>
	</div>
	</div>
	<div class="col-sm-2"></div>
</div>

<br>
<br>
<br>
<br>
<br>
<c:import url="/WEB-INF/jsp/footer.jsp" />