<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tag" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List People</title>
<style>
a, table {
	margin: 20px 0px;
}
</style>
</head>
<c:url value="/" var="rootpath" />
<body>
	<h1>List People Hibernate</h1>

	<a href="${rootpath}">Spring Home</a>

	<table border="1" cellspacing="3" cellpadding="5">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Avatar</th>
			<th>Birthday</th>
			<th>Gender</th>
			<th>Phone</th>
			<th>Address</th>
		</tr>
		<c:forEach items="${listPeople}" var="person">
			<tr>
				<td>${person.id}</td>
				<td>${person.name}</td>
				<td>${person.avatar}</td>
				<td><fmt:formatDate dateStyle="MEDIUM"
						value="${person.birthday}" /></td>
				<td>${person.gender ? "Male" : "Female"}</td>
				<td>${person.phone}</td>
				<td>${person.address}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>