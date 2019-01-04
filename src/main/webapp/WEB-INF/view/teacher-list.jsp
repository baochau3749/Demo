<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher List</title>
</head>
<body>
	<h1>Teacher List</h1>
	<hr>
	<a href="${pageContext.request.contextPath}/addTeacher">New Teacher</a>
	<br><br>
	<table>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Phone Number</th>
			<th>Action</th>
		</tr>
		
		<c:forEach var="teacher" items="${teachers}">
			<c:url var="updateLink" value="/updateTeacher/${teacher.teacherId}"/>
			<c:url var="deleteLink" value="/deleteTeacher">
				<c:param name="teacherId" value="${teacher.teacherId}"/>
			</c:url>
			<tr>
				<td>${teacher.firstName}</td>
				<td>${teacher.lastName}</td>
				<td>${teacher.email}</td>
				<td>${teacher.phoneNumber}</td>
				<td>
					<a href="${updateLink}">Update</a> | 
					<a href="${deleteLink}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>