<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<section class="section">
	<h1 id="detail-title">${park.parkName}</h1>

	<div class="detail-pic-and-quote">
		<div class="park-image-detail">
			<img
				src="<c:url value="img/parks/${fn:toLowerCase(park.parkCode)}.jpg"/>" />
		</div>

		<p id="inspirational-quote">
			<c:out value="${park.quote}" />
		</p>

		<p id="inspirational-quote-source">
			-
			<c:out value="${park.quoteSource}" />
		</p>


	</div>
	<div class="park-detail-description">

		<p id="park-text">
			Founded in <span id="year-founded">${park.yearFounded}</span>, <span
				id="park-name">${park.parkName}</span> is located in the state of <span
				id="park-state">${park.state}</span> and has <span
				id="mile-of-trail">${park.milesOfTrail}</span> miles of trails
			spread out in its <span id="park-acreage">${park.acreage}</span>
			acres of natural landscapes. With a typical <span id="park-climate">
				${park.climate}</span> climate and an elevation of <span
				id="park-elevation">${park.elevation}</span> feet, <span
				id="number-of-animals">${park.numOfSpecies}</span> different species
			of animals can be spotted in the park. This park attracts around <span
				id="annual-visitors">${park.annualVisitors}</span> visitors every
			year, who can choose from <span id="number-of-campsites">${park.numOfCampsites}</span>
			different camp sites.
		</p>
		

		<div id="entry-fee">
			<c:if test="${park.entryFee == 0}">
				<c:out value="Entry is free!" />
			</c:if>
			<c:if test="${park.entryFee > 0}">
				<c:out value="Entry fee: $${park.entryFee}/person" />
			</c:if>
		</div>
	
	<table class="five-day-forecast">
		<c:forEach var="day" items="${parkWeather}">
			<tr>
				
			<th id="Temps-Recommendations">
				<img class="weatherImgSrc"
					src="<c:url value="img/weather/${fn:toLowerCase(day.forecastImage)}.png"/>" />
					
				<p id="lowtemp">Low Temperature of ${day.lowTemp}</p>

				<p id="hightemp">High Temperature of ${day.highTemp}</p>
			
				
				<p id="recommendations">Recommendations: <br/><span>${day.recommendation} </span></p>
				</th>
			</tr>
		</c:forEach>
	</table>
</section>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />