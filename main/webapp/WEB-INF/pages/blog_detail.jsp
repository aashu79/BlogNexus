<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${blog.title}|BlogNexus</title>

<!-- Custom CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/blog_detail.css">
</head>
<body>
	<jsp:include page="../components/fontawesome.jsp"></jsp:include>
	<jsp:include page="../components/navbar.jsp"></jsp:include>
	<jsp:include page="../components/message_handler.jsp"></jsp:include>

	<!-- Blog Content -->
	<main class="blog-main">
		<div class="container">


			<!-- Blog Header -->
			<header class="blog-header">
				<h1 class="blog-title">${blog.title}</h1>

				<div class="blog-meta">
					<div class="author">
						<c:choose>
							<c:when test="${not empty authorProfilePictureUrl}">
								<img
									src="${pageContext.request.contextPath}${authorProfilePictureUrl}"
									alt="${blog.authorName}" class="author-avatar">
							</c:when>
							<c:otherwise>
								<img src="https://randomuser.me/api/portraits/men/31.jpg"
									alt="${blog.authorName}" class="author-avatar">
							</c:otherwise>
						</c:choose>
						<div class="author-info">
							<span class="author-name">${blog.authorName}</span> <span
								class="author-role">${authorRole}</span>
						</div>
					</div>

					<div class="blog-date">
						<i class="far fa-calendar-alt"></i> <span>${formattedDate}</span>
					</div>

					<div class="blog-readtime">
						<i class="far fa-clock"></i> <span>${readTime} min read</span>
					</div>

					<div class="blog-category">
						<span>${blog.genre}</span>
					</div>
				</div>
			</header>

			<!-- Featured Image -->
			<div class="featured-image-wrapper">
				<div class="featured-image">
					<c:choose>
						<c:when test="${not empty blog.imageUrl}">
							<img src="${pageContext.request.contextPath}${blog.imageUrl}"
								alt="${blog.title}">
						</c:when>
						<c:otherwise>
							<img
								src="https://images.unsplash.com/photo-1465146344425-f00d5f5c8f07?w=1200"
								alt="${blog.title}">
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<!-- Blog Actions -->
			<div class="blog-actions">
				<div class="blog-actions-left">
					<c:if test="${isLoggedIn}">
						<form action="${pageContext.request.contextPath}/blog/favourite"
							method="post" class="inline-form">
							<input type="hidden" name="blogId" value="${blog.blogId}">
							<input type="hidden" name="action"
								value="${isFavourite ? 'remove' : 'add'}">
							<button type="submit"
								class="action-btn favorite-btn ${isFavourite ? 'active' : ''}">
								<i class="${isFavourite ? 'fas' : 'far'} fa-heart"></i> <span>${isFavourite ? 'Remove from Favourites' : 'Add to Favourites'}</span>
							</button>
						</form>
					</c:if>


				</div>

				<div class="blog-actions-right">
					<c:if test="${isLoggedIn}">
						<form action="${pageContext.request.contextPath}/blog/like"
							method="post" class="inline-form">
							<input type="hidden" name="blogId" value="${blog.blogId}">
							<input type="hidden" name="action"
								value="${isLiked ? 'remove' : 'add'}">
							<button type="submit"
								class="action-btn like-btn ${isLiked ? 'active' : ''}">
								<i class="${isLiked ? 'fas' : 'far'} fa-thumbs-up"></i> <span>${likeCount}
									${likeCount == 1 ? 'Like' : 'Likes'}</span>
							</button>
						</form>
					</c:if>
					<c:if test="${!isLoggedIn}">
						<div class="action-btn like-btn disabled">
							<i class="far fa-thumbs-up"></i> <span>${likeCount}
								${likeCount == 1 ? 'Like' : 'Likes'}</span>
						</div>
					</c:if>
				</div>
			</div>

			<!-- Blog Content -->
			<article class="blog-content">
				<p class="article-content">${blog.content}</p>
			</article>

			<!-- Comments Section -->
			<section class="comments-section">
				<h2 class="section-title">
					Comments <span class="comment-count">(${commentCount})</span>
				</h2>

				<c:if test="${isLoggedIn}">
					<div class="add-comment">
						<div class="comment-user">
							<c:choose>
								<c:when test="${not empty currentUser.profilePictureUrl}">
									<img
										src="${pageContext.request.contextPath}${currentUser.profilePictureUrl}"
										alt="${currentUser.firstName}" class="comment-avatar">
								</c:when>
								<c:otherwise>
									<img src="https://randomuser.me/api/portraits/men/42.jpg"
										alt="${currentUser.firstName}" class="comment-avatar">
								</c:otherwise>
							</c:choose>
							<span>${currentUser.firstName} ${currentUser.lastName}</span>
						</div>
						<form class="comment-form"
							action="${pageContext.request.contextPath}/blog/comment"
							method="post">
							<input type="hidden" name="blogId" value="${blog.blogId}">
							<textarea name="commentContent"
								placeholder="Share your thoughts..." rows="3" required></textarea>
							<button type="submit" class="btn-primary">Post Comment</button>
						</form>
					</div>
				</c:if>

				<div class="comments-list">
					<c:choose>
						<c:when test="${empty comments}">
							<div class="no-comments">
								<p>No comments yet. Be the first to share your thoughts!</p>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${comments}" var="comment">
								<div class="comment">
									<div class="comment-header">
										<div class="comment-user">
											<c:choose>
												<c:when
													test="${not empty comment.commenterProfilePictureUrl}">
													<img
														src="${pageContext.request.contextPath}${comment.commenterProfilePictureUrl}"
														alt="${comment.commenterName}" class="comment-avatar">
												</c:when>
												<c:otherwise>
													<img src="https://randomuser.me/api/portraits/women/33.jpg"
														alt="${comment.commenterName}" class="comment-avatar">
												</c:otherwise>
											</c:choose>
											<div>
												<span class="comment-author">${comment.commenterName}</span>
												<span class="comment-date"> <fmt:parseDate
														value="${comment.commentedAt}"
														pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate"
														type="both" /> <fmt:formatDate value="${parsedDate}"
														pattern="MMMM d, yyyy • HH:mm" />
												</span>
											</div>
										</div>
									</div>
									<div class="comment-body">
										<p>${comment.commentContent}</p>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</section>
		</div>
	</main>

	<jsp:include page="../components/footer.jsp"></jsp:include>


</body>
</html>