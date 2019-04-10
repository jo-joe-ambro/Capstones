<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>
		National Parks Weather Service 
	</title>
	<c:url var="stylesheetHref" value="/css/stylesheet.css" />
	<link rel="stylesheet" href="${stylesheetHref}">
</head>
<body>
	<header>
		<c:url var="logoImgSrc" value="/img/logo.png" />
		<img id="header-photo" src="${logoImgSrc}" alt="National Park Geek">
		<p class="motto">Helping you know the weather in your national park </p>
	</header>
	<nav class="topnav">
		<ul>
		
			<c:url var="homePageHref" value="/" />
			<c:url var="surveyHref" value="/survey" />
			
		<li><a href="${homePageHref}">Home</a></li>
		<li><a href="${surveyHref}">Take our survey</a></li>
		
		</ul>
	</nav>
	<div id="container" class="wrapper">