<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>BlogNexus - Share Your Stories</title>

<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;700&family=Montserrat:wght@400;500;600;700;800&display=swap"
	rel="stylesheet">

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
	<jsp:include page="../components/fontawesome.jsp" />
	<jsp:include page="../components/message_handler.jsp"></jsp:include>

	<!-- Include Navbar -->
	<jsp:include page="../components/navbar.jsp" />

	<!-- Hero Section -->
	<jsp:include page="../sections/hero_section.jsp" />

	<!-- Featured Posts Section -->
	<jsp:include page="../sections/featured_posts.jsp" />

	<!-- Categories Section -->
	<jsp:include page="../sections/categories.jsp" />

	<!-- Trending Section -->
	<jsp:include page="../sections/trending.jsp" />

	<!-- Footer -->
	<jsp:include page="../components/footer.jsp" />
</body>
</html>