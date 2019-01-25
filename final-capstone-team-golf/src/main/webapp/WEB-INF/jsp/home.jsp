<!-- GOOD CSS MB-->



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/header.jsp" />


<br> <br> <br>	<br><br><br>

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
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<div class="container-fluid bg-1 ">
<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
				<li data-target="#myCarousel" data-slide-to="4"></li>
				<li data-target="#myCarousel" data-slide-to="5"></li>
				<li data-target="#myCarousel" data-slide-to="6"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<div class="item active">
					<img class="img-size-carosel" src="img/food-salad-healthy-vegetables.jpg"
						alt="food-salad-healthy-vegetables">
				</div>

				<div class="item">
					<img class="img-size-carosel" src="img/photo-grapefruit.jpg" alt="photo-grapefruit">
				</div>

				<div class="item">
					<img class="img-size-carosel" src="img/photo-good.jpg" alt="photo-good">
				</div>

				<div class="item">
					<img class="img-size-carosel" src="img/veggiepic 5.09.00 PM.jpg" alt="veggiepic">
				</div>

				<div class="item">
					<img class="img-size-carosel" src="img/photo-darkskillet-3.jpg" alt="darkskillet">
				</div>

				<div class="item">
					<img class="img-size-carosel" src="img/plated-smoked-salmon_4460x4460.jpg"
						alt="plated-smoked-salmon">
				</div>

				<div class="item">
					<img class="img-size-carosel" src="img/kids-flour-2.jpg" alt="kids-flour">
				</div>
			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
</div>		
		
	
		

	
	
					
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
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	


<!-- info -->
<!-- <div class="head">
</div>
<div id="wrapper">
   
    ">
        <div id="bodyi">
            <div id="bodyj">
                <div id="sidebar">
                    <div class="content">
                        <h2><strong>Cancer Info</strong></h2>
                        <p><strong>Nutrition is an important part of the health of all children, 
                        but it is especially important for children getting cancer treatment. 
                        This guide can help you learn about your child's nutritional 
                        needs and how cancer and its treatment may affect them. 
                        We also offer suggestions and recipes to help you ensure your child, 
                        is getting the nutrition he or she needs. 
                        <img src="img/food-salad-dinner-eating-longways.jpg" width="150" height="122" alt="" />
                        is getting the nutrition he or she needs.</strong></p>
                        
                    </div>
                    <div class="content">
                        <h2><strong>Nutrition!</strong></h2>
                        <h3><strong>Dec 18, 2018</strong></h3>
                        <h4><strong>Low Fiber Foods</strong></h4>
                        <p><strong>Your doctor may recommend a low fiber diet for diarrhea, cramping, 
                        trouble digesting food, or after some types of surgery. 
                        <img src="img/kids-flour-2.jpg" width="95" height="73" alt="pic 3" />
                        Here you'll find 
                        lists of low fiber foods, along with lists of foods to avoid.</strong></p>
                        <h3><strong>Dec 18, 2018</strong></h3>
                        <h4><strong>Cancer Fighting Foods</strong></h4>
                        <p><strong>No single food can prevent cancer, but the right combination of foods 
                        may help make a difference. At mealtimes, strike a balance of at least 
                        two-thirds plant-based foods and no more than one-third animal protein.
                         This "New American Plate" is an important cancer fighting tool, according 
                         to the American Institute for Cancer Research. Check out better and worse 
                         choices for your plate.</strong><p><strong>
                        - Good rule of thumb is eat foods with COLOR!!</strong><p><strong>
                        - Eat More Folate-Rich Foods</strong></p>
                    </div>
                </div>
                <div id="content">
                    <div class="content">
                        <h2><strong>Nutrition for the Person with Cancer</strong></h2>
                        <img src="img/photo-good.jpg" width="82" height="80" alt="Unwired album cover" class="left" />
                        <p><strong>Good nutrition is especially important if you have cancer because both the illness and its treatment can affect your appetite.
                         Cancer and cancer treatments can also affect your body's ability to tolerate certain foods and use nutrients. This guide can help 
                         you and your loved ones learn about your nutrition needs and cope with treatment side effects that may affect how well you can eat.</strong></p>
                        <div class="divider"></div>
                        <h2><strong>Eat well, Live free</strong></h2>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
   
</div> -->
<br><br><br><br><br><br><br><br><br>
	<!-- First Container -->
	
</div>

	<!-- Second Container -->

<c:import url="/WEB-INF/jsp/footer.jsp" />