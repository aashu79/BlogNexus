<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="service.FeaturedService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="model.BlogModel"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
// Get featured blogs with author details
FeaturedService featuredService = new FeaturedService();
Map<String, Object> result = featuredService.getFeaturedBlogsWithAuthors(6);

List<BlogModel> featuredBlogs = (List<BlogModel>) result.get("blogs");
Map<Integer, Map<String, String>> authorDetails = (Map<Integer, Map<String, String>>) result.get("authorDetails");

request.setAttribute("featuredBlogs", featuredBlogs);
request.setAttribute("authorDetails", authorDetails);
%>

<link rel="stylesheet" href="${contextPath}/css/featured_posts.css">

<section class="featured-posts section" id="featured">
	<div class="container">
		<div class="section-header">
			<h2 class="section-title">Featured Stories</h2>
			<p class="section-subtitle">Handpicked articles from our editors
				to inspire and inform.</p>
		</div>

		<div class="featured-posts-grid">
			<c:choose>
				<c:when test="${empty featuredBlogs}">
					<div class="no-blogs-message"
						style="text-align: center; padding: 2rem; background: #f8f8f8; border-radius: 8px; grid-column: 1/-1;">
						<p>No featured stories available at the moment.</p>
					</div>
				</c:when>
				<c:otherwise>
					<c:forEach items="${featuredBlogs}" var="blog">
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
</section>