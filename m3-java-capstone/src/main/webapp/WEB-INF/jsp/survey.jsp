<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@include file="common/header.jspf"%>

	<h1>Today's Survey</h1>
	<section class="centeredPanel">
		
		<p>Bacon ipsum dolor amet prosciutto filet mignon pork loin, corned beef bacon buffalo boudin pork chop. Landjaeger flank tail burgdoggen, biltong doner porchetta leberkas 
			chuck short ribs. Turkey ground round kevin, pastrami buffalo alcatra turducken brisket venison capicola landjaeger ham t-bone. Hamburger rump chuck salami cupim meatloaf 
			leberkas cow, beef pancetta pork chop turkey bresaola.</p>

	
		<c:url var="surveyUrl" value="/survey" />
		<form action="${surveyUrl}" method="post">
		
			<div>
				<label for="parkCode"> Favorite National Park:</label>
				<select id="parkCode" name="parkCode">
					<option value="CVNP">Cuyahoga Valley National Park</option>
					<option value="ENP">Everglades National Park</option>
					<option value="GCNP">Grand Canyon National Park</option>
					<option value="GNP">Glacier National Park </option>
					<option value="GSMNP">Great Smoky Mountains National Park</option>
					<option value="GTNP">Grand Teton National Park</option>
					<option value="MRNP">Mountain Rainier National Park</option>
					<option value="RMNP">Rocky Mountain National Park</option>
					<option value="YNP">Yellowstone National Park</option>
					<option value="YNP2">Yosemite National Park</option>
				</select>
			</div>
			<div>
			    <label for="email">Email Address:</label>
				    <input type="text" id="email" name="email">
			</div>
			
			<div>	    
				<label for="stateOfRes">State of Residence:</label>
				    <select id="stateOfRes" name="stateOfRes">
						<option value="AL">Alabama</option>
						<option value="AK">Alaska</option>
						<option value="AZ">Arizona</option>
						<option value="AR">Arkansas</option>
						<option value="CA">California</option>
						<option value="CO">Colorado</option>
						<option value="CT">Connecticut</option>
						<option value="DE">Delaware</option>
						<option value="DC">District Of Columbia</option>
						<option value="FL">Florida</option>
						<option value="GA">Georgia</option>
						<option value="HI">Hawaii</option>
						<option value="ID">Idaho</option>
						<option value="IL">Illinois</option>
						<option value="IN">Indiana</option>
						<option value="IA">Iowa</option>
						<option value="KS">Kansas</option>
						<option value="KY">Kentucky</option>
						<option value="LA">Louisiana</option>
						<option value="ME">Maine</option>
						<option value="MD">Maryland</option>
						<option value="MA">Massachusetts</option>
						<option value="MI">Michigan</option>
						<option value="MN">Minnesota</option>
						<option value="MS">Mississippi</option>
						<option value="MO">Missouri</option>
						<option value="MT">Montana</option>
						<option value="NE">Nebraska</option>
						<option value="NV">Nevada</option>
						<option value="NH">New Hampshire</option>
						<option value="NJ">New Jersey</option>
						<option value="NM">New Mexico</option>
						<option value="NY">New York</option>
						<option value="NC">North Carolina</option>
						<option value="ND">North Dakota</option>
						<option value="OH">Ohio</option>
						<option value="OK">Oklahoma</option>
						<option value="OR">Oregon</option>
						<option value="PA">Pennsylvania</option>
						<option value="RI">Rhode Island</option>
						<option value="SC">South Carolina</option>
						<option value="SD">South Dakota</option>
						<option value="TN">Tennessee</option>
						<option value="TX">Texas</option>
						<option value="UT">Utah</option>
						<option value="VT">Vermont</option>
						<option value="VA">Virginia</option>
						<option value="WA">Washington</option>
						<option value="WV">West Virginia</option>
						<option value="WI">Wisconsin</option>
						<option value="WY">Wyoming</option>
					</select>
				</div>
				
				<div>
					<label for="level">Activity Level:</label>
					
					<input type="radio" id="inactive" name="activityLevel" value="inactive"/>
					<label for="inactive">Inactive</label>
					
					<input type="radio" id="sedentary" name="activityLevel" value="sedentary"/>
					<label for="sedentary">Sedentary</label>
					
					<input type="radio" id="active" name="activityLevel" value="active"/>	
					<label for="active">Active</label>
						
					<input type="radio" id="extremely active" name="activityLevel" value="extremely active"/>	
					<label for="extremely active">Extremely Active</label>	
		    	</div>
		    	
		    	<button type="submit">Submit</button>
		</form>
	</section>
<%@include file="common/footer.jspf"%>

