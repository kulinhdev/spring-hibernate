<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Form</title>
</head>
<style>
table tr th {
	width: 80px;
	text-align: right;
}

a, table {
	margin: 20px 0px;
}

.alert-info {
	display: block;
	color: green;
	font-size: 20px;
	margin: 20px 0;
}
</style>
<c:url value="/" var="rootpath" />
<body>
	<h1>Show Form Data</h1>

	<a href="${rootpath}">Spring Home</a>

	<c:if test="${message != null}">
		<p class="alert-info">${message}</p>
	</c:if>

	<table border="1" cellspacing="3" cellpadding="5">
		<tr>
			<th>Name:</th>
			<td>${person.name}</td>
		</tr>
		<tr>
			<th>Image:</th>
			<td>${person.avatar}</td>
		</tr>
		<tr>
			<th>Birthday:</th>
			<td><fmt:formatDate type="date" value="${person.birthday}" /></td>
		</tr>
		<tr>
			<th>Gender:</th>
			<td>${person.gender ? "Male" : "Female"}</td>
		</tr>
		<tr>
			<th>Phone:</th>
			<td>${person.phone}</td>
		</tr>
		<tr>
			<th>Address:</th>
			<td>${person.address}</td>
		</tr>
	</table>
</body>
</html>