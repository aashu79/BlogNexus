<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Logging Out</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/logout.css">
<script>
	window.onload = function() {
		// Simply redirect after a short delay
		setTimeout(function() {
			window.location.href = '${pageContext.request.contextPath}/';
		}, 2000);
	};
</script>
</head>
<body>
	<div class="logout-container">
		<h1 class="logout-title">Logging Out</h1>
		<p class="logout-message">You are being signed out...</p>
		<p class="logout-success">Redirecting to homepage</p>
	</div>
</body>
</html>