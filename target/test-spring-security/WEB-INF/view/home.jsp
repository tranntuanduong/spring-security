<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>luv2code Company Home Page</title>
</head>
<body>
	<h2>luv2code Company Home Page</h2>
	<hr>
	Welcome to the luv2code company home pages!!

	</br>
	User: <security:authentication property="principal.username" />
	</br>
	Roles: <security:authentication property="principal.authorities" />
	<hr>

	<%--only MANAGER roles can see--%>
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders">MANAGER</a>
			(Only for manager peeps)
		</p>
		<hr>
	</security:authorize>

	<%--only ADMIN roles can see--%>
	<security:authorize access="hasRole('ADMIN')">
		<p>
			<a href="${pageContext.request.contextPath}/systems">ADMIN</a>
			(Only for admin peeps)
		</p>
	</security:authorize>

	<form action="${pageContext.request.contextPath}/logout"
			   method="POST">
		<input type="submit" value="Logout"/>
		<input type="hidden"
			   name="${_csrf.parameterName}"
			   value="${_csrf.token}" />
	</form>
</body>
</html>