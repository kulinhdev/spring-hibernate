<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tag" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List People</title>
<style>
a, table, form {
	margin: 20px 0px;
}

.alert-info {
	display: block;
	color: green;
	font-size: 20px;
	margin: 20px 0;
}
</style>
</head>
<c:url value="/" var="rootpath" />
<body>
	<h1>List Employee Hibernate</h1>

	<a href="${rootpath}">Spring Home</a>

	<c:if test="${message != null}">
		<p class="alert-info">${message}</p>
	</c:if>

	<form action="${rootpath}people" method="GET">
		<label>Name :</label> <input type="text" value="${searchName}"
			name="searchName" /> <label>Gender :</label><select
			name="searchGender">
			<option value="1">Male</option>
			<option value="0">Female</option>
		</select>
		<button type="submit">Search</button>

	</form>

	<table border="1" cellspacing="3" cellpadding="5">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Avatar</th>
			<th>Birthday</th>
			<th>Gender</th>
			<th>Phone</th>
			<th>Address</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${listPeople}" var="person">
			<tr>
				<td>${person.id}</td>
				<td>${person.name}</td>
				<td><img width="250px"
					src="${rootpath}public/uploads/${person.avatar}" alt="Avatar" /></td>
				<td><fmt:formatDate dateStyle="MEDIUM"
						value="${person.birthday}" /></td>
				<td>${person.gender ? "Male" : "Female"}</td>
				<td>${person.phone}</td>
				<td>${person.address}</td>
				<td><a href="${rootpath}edit/${person.id}">Edit</a> <a
					onclick="return confirm('Are you sure?')"
					href="${rootpath}delete/${person.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>