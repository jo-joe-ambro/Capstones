<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<h1>Survey Results</h1>
<c:forEach var="result" items="${surveyResults}">
		<p>${result.key}</p>
		<p>${result.value}</p>
</c:forEach>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />