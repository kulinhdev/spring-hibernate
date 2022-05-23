<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Form</title>
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

.error {
	color: red;
}
</style>
<c:url value="/" var="rootpath" />
<c:url value="/insert" var="insert" />
<body>

	<h1>Spring Add Form</h1>

	<a href="${rootpath}">Spring Home</a>

	<c:if test="${message != null}">
		<p class="alert-info">${message}</p>
	</c:if>


	<fm:form action="${insert}" modelAttribute="person" method="POST"
		enctype="multipart/form-data">
		<table border="1" cellspacing="3" cellpadding="5">
			<tr>
				<th>Name :</th>
				<td><fm:input path="name" /> <br /> <fm:errors path="name"
						cssClass="error"></fm:errors></td>
			</tr>
			<tr>
				<th>Image:</th>
				<td><fm:input type="hidden" path="avatar" /> <input
					type="file" name="fileAvatar" /><br /> <fm:errors path="avatar"
						cssClass="error"></fm:errors></td>
			</tr>
			<tr>
				<th>Birthday :</th>
				<td><fm:input type="date" path="birthday" /><br /> <fm:errors
						path="birthday" cssClass="error"></fm:errors></td>
			</tr>
			<tr>
				<th>Gender :</th>
				<td><fm:radiobutton path="gender" value="1" />Male <fm:radiobutton
						path="gender" value="0" />Female</td>
			</tr>
			<tr>
				<th>Phone :</th>
				<td><fm:input type="text" path="phone" /><br /> <fm:errors
						path="phone" cssClass="error"></fm:errors></td>
			</tr>
			<tr>
				<th>Address:</th>
				<td><fm:textarea path="address" /></td>
			</tr>

			<tr>
				<td></td>
				<td><button type="reset">Reset</button>
					<button type="submit">Submit</button></td>
			</tr>
		</table>
	</fm:form>
</body>
</html>