<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tag" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style>
.link {
	color: black;
	font-size: 20px;
	display: block;
	text-decoration: none;
	margin-right: 10px;
}

.mb {
	margin-bottom: 30px;
	color: red;
}

.language {
	display: flex;
}

.language:after {
	clear: both;
	float: none;
}
</style>
</head>
<c:url value="/" var="rootpath" />
<body>
	<h1>
		<tag:message code="app.name" />
	</h1>

	<div class="language">
		<a class="link" href="?language=vi_VN">VietNam</a> <br /> <a
			class="link" href="?language=ja_JP">Japan</a> <br /> <a class="link"
			href="?language=en_US">English</a> <br />
	</div>
	<br />
	<a class="link mb" href="add">Spring Form</a>
	<a class="link mb" href="people">List People</a>
	<br />

	<img width="200px" alt="Demo Image"
		src="${rootpath}/public/images/image_demo.jpg" />
</body>
</html>