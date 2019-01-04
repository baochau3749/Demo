<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher Information</title>
	<style>
		.error {color:red}
	</style>
</head>
<body>
	<h1>Teacher Form</h1>
	<hr>
	<form:form action="${pageContext.request.contextPath}/saveTeacher" modelAttribute="teacher">
	<form:errors path="*" cssClass="error" element="div"/>
		<c:if test="${teacher != null}">
			<input type="hidden" id="id" name="teacherId" value="${teacher.teacherId}"/>
		</c:if>
		
		<c:choose>
			<c:when test= "${teacher != null}"> 
				First name: <form:input path="firstName"/>
				<form:errors path="firstName" cssClass="error"></form:errors>
				<br></br>
				Last Name: <form:input path="lastName"/>
				<form:errors path="lastName" cssClass="error"></form:errors>
				<br></br>
				Email: <form:input path="email"/>
				<form:errors path="email" cssClass="error"></form:errors>
				<br><br>
				Phone: <form:input path="phoneNumber" />
				<form:errors path="phoneNumber" cssClass="error"></form:errors>
				<br></br>
			</c:when>	
			
			<c:otherwise>
				First name: <input type="text" name="firstName" value=""/>
				<form:errors path="firstName" cssClass="error"></form:errors>
				<br></br>
				Last Name: <input type="text" name="lastName" value=""/>
				<form:errors path="lastName" cssClass="error"></form:errors>
				<br></br>
				Email: <input type="text" name="email" value=""/>
				<form:errors path="email" cssClass="error"></form:errors>
				<br><br>
				Phone: <input type="text" name="phoneNumber" value="" />
				<form:errors path="phoneNumber" cssClass="error"></form:errors>
				<br></br>
			 </c:otherwise>
		</c:choose>
		
		<input type="submit" value="Save" />
		
	</form:form>
	
</body>
</html>