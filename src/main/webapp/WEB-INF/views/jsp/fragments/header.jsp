<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Upload Your Conference Presentations</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/users/add" var="urlAddUser" />

<nav class="navbar navbar-inverse ">
	<div class="container">
	<div class="global-nav">
			<div id="navbar">
    			<ul class="nav navbar-nav navbar-left">
    				<li class='' id="conferences"><a title="Organize A Conference" href="/ConferencePresentations/conferences/add">Organize A Conference</a></li>
    			</ul>
    		</div>
			<div id="navbar">
    			<ul class="nav navbar-nav navbar-left">
    				<li class='' id="find-a-conference"><a title="Find A Conference" href="/ConferencePresentations/conferences/">Find A Conference</a></li>
    			</ul>
    		</div>
			<div id="navbar">
    			<ul class="nav navbar-nav navbar-left">
    				<li class='' id="upload-presentation"><a title="Upload Presentation" href="/ConferencePresentations/presentations/upload">Upload Presentation</a></li>
    			</ul>
    		</div>
			<div id="navbar">
    			<ul class="nav navbar-nav navbar-left">
    				<li class='' id="find-a-presentation"><a title="Find A Presentation" href="/ConferencePresentations/presentations/">Find A Presentation</a></li>
    			</ul>
    		</div>
			<div id="navbar">
    			<ul class="nav navbar-nav navbar-left">
    				<li class='' id="register"><a title="Register" href="/ConferencePresentations/users/add">Register</a></li>
    			</ul>
    		</div>
			<div id="navbar">
    			<ul class="nav navbar-nav navbar-left">
    				<li class='' id="login"><a title="Login" href="/login">Login</a></li>
    			</ul>
    		</div>
			<div id="navbar">
    			<ul class="nav navbar-nav navbar-left">
    				<li class='' id="policy"><a title="Privacy" href="/ConferencePresentations/privacy.jsp">Privacy</a></li>
    			</ul>
    		</div>
			<div id="navbar">
    			<ul class="nav navbar-nav navbar-left">
    				<li class='' id="about-us"><a title="About Us" href="/ConferencePresentations/about.jsp">About Us</a></li>
    			</ul>
    		</div>
    </div>
	</div>
	</div>
</nav>