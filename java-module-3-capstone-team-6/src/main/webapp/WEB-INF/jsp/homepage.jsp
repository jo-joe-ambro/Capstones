<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h1>National Parks</h1>
<c:url var="tempURL" value="/temp" />
<form method="POST" action="${tempURL}">
	<div>
		<label for="temp">Choose your temp</label> <select name="temp">
			<option value="f">Fahrenheit</option>
			<option value="c">Celsius</option>
		</select>
	</div>
	<div>
		<input class="formSubmitButton" type="submit" />
	</div>

</form>

<div id="park-list-div">
	<div id="park-list">
	<c:url var="detailsURL" value="/details" />
	<c:forEach var="park" items="${parks}">

		
		
		<a href="${detailsURL}?parkCode=${park.parkCode}">
		<img id="park-image" src="<c:url value="img/parks/${fn:toLowerCase(park.parkCode)}.jpg"/>" />	
		</a>
		<div id="info-desc">
		<h2>${park.parkName}</h2>
		<p>Located: <span>${park.state}</span>
		<p>About the park: <br/> <span>${park.description}</span></p>
		</div>

	</c:forEach>
	</div>

</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />