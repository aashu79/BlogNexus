<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="service.TrendingService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="model.BlogModel"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
// Get trending blogs with author details
TrendingService trendingService = new TrendingService();
Map<String, Object> result = trendingService.getRandomBlogsWithAuthors(3);

List<BlogModel> trendingBlogs = (List<BlogModel>) result.get("blogs");
Map<Integer, Map<String, String>> authorDetails = (Map<Integer, Map<String, String>>) result.get("authorDetails");

request.setAttribute("trendingBlogs", trendingBlogs);
request.setAttribute("authorDetails", authorDetails);
%>

<link rel="stylesheet" href="${contextPath}/css/trending.css">

<section class="trending-section section">
	<div class="container">
		<div class="trending-header">
			<h2 class="trending-title">Trending This Week</h2>

		</div>

		<div class="trending-grid">
			<div class="trending-posts">
				<c:choose>
					<c:when test="${empty trendingBlogs}">
						<div class="no-blogs-message"
							style="text-align: center; padding: 2rem; background: #f8f8f8; border-radius: 8px;">
							<p>No trending blogs available at the moment.</p>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${trendingBlogs}" var="blog">
							<div class="post-card">
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
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</section>

<style>
.blog-card-link {
	text-decoration: none;
	color: inherit;
	display: block;
}

.blog-card {
	transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.blog-card:hover {
	transform: translateY(-3px);
	box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
}
</style>