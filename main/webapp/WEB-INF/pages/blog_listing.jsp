<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Blog Management - Admin Dashboard</title>
<!-- Add timestamp to prevent caching -->
<link rel="stylesheet" href="${contextPath}/css/blog_listing.css">
</head>
<body>
	<jsp:include page="../components/fontawesome.jsp"></jsp:include>
	<jsp:include page="../components/message_handler.jsp"></jsp:include>

	<jsp:include page="../components/admin_navbar.jsp" />

	<!-- Main Content -->
	<div class="content">
		<div class="dashboard-header">
			<h1>Blog Management</h1>
			<div>
				<p>Current Date and Time (UTC - YYYY-MM-DD HH:MM:SS formatted):
					${currentDateTime}</p>
				<p>Current User's Login: ${currentUser.firstName}</p>
			</div>
		</div>

		<!-- Blogs Table Card -->
		<div class="card section-card">
			<div class="section-header">
				<h2>Blogs List</h2>
			</div>
			<div class="table-container">
				<c:if test="${not empty errorMessage}">
					<div class="error-message">${errorMessage}</div>
				</c:if>

				<table id="blogsTable">
					<thead>
						<tr>
							<th>ID</th>
							<th>Thumbnail</th>
							<th>Title</th>
							<th>Genre</th>
							<th>Created By</th>
							<th>Created At</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty blogs}">
								<tr>
									<td colspan="7" class="no-data">No blogs found</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${blogs}" var="blog">
									<tr>
										<td>${blog.blogId}</td>
										<td>
											<div class="blog-thumbnail">
												<c:choose>
													<c:when test="${not empty blog.thumbnailUrl}">
														<img src="${contextPath}${blog.thumbnailUrl}"
															alt="Blog Thumbnail">
													</c:when>
													<c:otherwise>
														<img src="https://via.placeholder.com/150x100"
															alt="Default Thumbnail">
													</c:otherwise>
												</c:choose>
											</div>
										</td>
										<td class="blog-title"><a
											href="${contextPath}/blog/${blog.blogId}" target="_blank">${blog.title}</a>
										</td>
										<td><span
											class="genre-badge ${fn:toLowerCase(blog.genre)}">${blog.genre}</span></td>
										<td>${authorNames[blog.createdBy]}</td>
										<td><fmt:parseDate value="${blog.createdAt}"
												pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both" />
											<fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd" />
										</td>
										<td>
											<div class="action-buttons">
												<form method="post"
													action="${contextPath}/admin/blogs/delete"
													onsubmit="return confirmDelete(${blog.blogId}, '${blog.title}')">
													<input type="hidden" name="id" value="${blog.blogId}">
													<button type="submit" class="action-link delete"
														title="Delete Blog">
														<i class="fas fa-trash-alt"></i> Delete
													</button>
												</form>
											</div>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script>
        function confirmDelete(blogId, blogTitle) {
            return confirm("Are you sure you want to delete blog '" + blogTitle + "'? This action cannot be undone.");
        }
    </script>
</body>
</html>