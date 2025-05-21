<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Search Results: ${searchQuery}</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/search_page.css">
</head>
<body>
	<jsp:include page="../components/navbar.jsp"></jsp:include>
	<div class="search-container">
		<header class="search-header">
			<h1 class="search-title">
				<i class="fas fa-search search-icon"></i> Search Results:
				"${searchQuery}"
			</h1>

			<!-- Simple search form -->
			<form class="search-form"
				action="${pageContext.request.contextPath}/search" method="get">
				<input type="text" name="query" value="${searchQuery}"
					placeholder="Search blogs..." required>
				<button type="submit">
					<i class="fas fa-search"></i>
				</button>
			</form>
		</header>

		<div class="blog-grid">
			<c:choose>
				<c:when test="${empty searchResults}">
					<div class="no-results-message">
						<i class="fas fa-search-minus no-results-icon"></i>
						<h3>No blogs found matching "${searchQuery}"</h3>
						<p>Try different keywords or browse our categories instead.</p>
						<div class="suggestion-links">
							<a href="${pageContext.request.contextPath}/"
								class="btn btn-outline">Home</a> <a
								href="${pageContext.request.contextPath}/user/blog/create"
								class="btn btn-primary">Create Blog</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${searchResults}" var="blog">
						<jsp:include page="../components/blog_card.jsp">
							<jsp:param name="blog_id" value="${blog.blogId}" />
							<jsp:param name="thumbnailUrl" value="${blog.thumbnailUrl}" />
							<jsp:param name="title" value="${blog.title}" />
							<jsp:param name="content" value="${blog.content}" />
							<jsp:param name="genre" value="${blog.genre}" />
							<jsp:param name="formattedDate"
								value="${authorDetails[blog.blogId].formattedDate}" />
							<jsp:param name="readTime"
								value="${authorDetails[blog.blogId].readTime}" />
							<jsp:param name="authorName" value="${blog.authorName}" />
							<jsp:param name="authorRole"
								value="${authorDetails[blog.blogId].authorRole}" />
							<jsp:param name="authorProfileUrl"
								value="${authorDetails[blog.blogId].profilePicture}" />
						</jsp:include>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>