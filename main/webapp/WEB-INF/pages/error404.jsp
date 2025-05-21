<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Page Not Found</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/error404.css">
</head>
<body>
	<div class="error-container">
		<h1 class="error-code">404</h1>
		<h2 class="error-message">Page Not Found</h2>
		<p class="error-description">The page you are looking for might
			have been removed, had its name changed, or is temporarily
			unavailable.</p>
		<a href="${pageContext.request.contextPath}/">
			<button class="home-button">Go to Home Page</button>
		</a>
	</div>
</body>
</html>