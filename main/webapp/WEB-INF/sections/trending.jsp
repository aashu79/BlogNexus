<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/trending.css">

<section class="trending-section section">
    <div class="container">
        <div class="trending-header">
            <h2 class="trending-title">Trending This Week</h2>
            <a href="${pageContext.request.contextPath}/trending.jsp" class="btn btn-sm btn-outline">View All</a>
        </div>

        <div class="trending-grid">
            <div class="trending-posts">
                <div class="post-card">
                    <jsp:include page="../components/BlogCard.jsp" />
                </div>
                <div class="post-card">
                    <jsp:include page="../components/BlogCard.jsp" />
                </div>
                <div class="post-card">
                    <jsp:include page="../components/BlogCard.jsp" />
                </div>
            </div>
        </div>
    </div>
</section>