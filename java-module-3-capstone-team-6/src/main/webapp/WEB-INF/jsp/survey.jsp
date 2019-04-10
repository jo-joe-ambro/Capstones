<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h1>Survey</h1>

<c:url var="surveyURL" value="/survey" />
<form:form method="POST" action="${surveyURL}" modelAttribute="survey">
	<div>
		<select required name="parkCode">
			<option style="display: none" value="">What is your favorite National Park</option>
			<option value="CVNP">Cuyahoga Valley National Park</option>
			<option value="ENP">Everglades National Park</option>
			<option value="GCNP">Grand Canyon National Park</option>
			<option value="GNP">Glacier National Park</option>
			<option value="GSMNP">Great Smoky Mountains National Park</option>
			<option value="GTNP">Grand Teton National Park</option>
			<option value="MRNP">Mount Rainier National Park</option>
			<option value="RMNP">Rocky Mountain National Park</option>
			<option value="YNP">Yellowstone National Park</option>
			<option value="YNP2">Yosemite National Park</option>
		</select>
		<form:errors path="parkCode" class="error" />
	</div>
	<div>
		<select required name="activityLevel">
			<option style="display: none" value="">What is your activity level</option>
			<option value="inactive">Inactive</option>
			<option value="sedentary">Sedentary</option>
			<option value="active">Active</option>
			<option value="extremelyActive">Extremely Active</option>
		</select>
		<form:errors path="activityLevel" class="error" />
	</div>
	<div>
		<label for="email">Email Address</label>
		<form:input path="email" />
		<form:errors path="email" class="error" />
	</div>
	<div>
		<select required name="state">
			<option style="display: none" value="">Select Your Home State</option>
			<option value="AL">AL</option>
			<option value="AK">AK</option>
			<option value="AZ">AZ</option>
			<option value="CA">CA</option>
			<option value="CO">CO</option>
			<option value="CT">CT</option>
			<option value="DE">DE</option>
			<option value="DC">DC</option>
			<option value="FL">FL</option>
			<option value="GA">GA</option>
			<option value="HI">HI</option>
			<option value="ID">ID</option>
			<option value="IL">IL</option>
			<option value="IN">IN</option>
			<option value="IA">IA</option>
			<option value="KS">KS</option>
			<option value="KY">KY</option>
			<option value="LA">LA</option>
			<option value="ME">ME</option>
			<option value="MD">MD</option>
			<option value="MA">MA</option>
			<option value="MI">MI</option>
			<option value="MN">MN</option>
			<option value="MS">MS</option>
			<option value="MO">MO</option>
			<option value="MT">MT</option>
			<option value="NE">NE</option>
			<option value="NV">NV</option>
			<option value="NH">NH</option>
			<option value="NM">NM</option>
			<option value="NY">NY</option>
			<option value="NC">NC</option>
			<option value="OH">OH</option>
			<option value="OK">OK</option>
			<option value="OR">OR</option>
			<option value="PA">PA</option>
			<option value="RI">RI</option>
			<option value="SC">SC</option>
			<option value="SD">SD</option>
			<option value="TN">TN</option>
			<option value="TX">TX</option>
			<option value="UT">UT</option>
			<option value="VT">VT</option>
			<option value="VA">VA</option>
			<option value="WA">WA</option>
			<option value="WV">WV</option>
			<option value="WI">WI</option>
			<option value="WY">WY</option>
		</select>
		<form:errors path="state" class="error" />
	</div>
	<div>
		<input class="formSubmitButton" type="submit" />
	</div>
</form:form>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />