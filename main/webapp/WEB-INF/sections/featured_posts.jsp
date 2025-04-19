<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/featured_posts.css">

<section class="featured-posts section" id="featured">
    <div class="container">
        <div class="section-header">
            <h2 class="section-title">Featured Stories</h2>
            <p class="section-subtitle">Handpicked articles from our editors to inspire and inform.</p>
        </div>

        <div class="featured-posts-grid">
            <div class="post-card">
                <jsp:include page="../components/BlogCard.jsp" />
            </div>
            <div class="post-card">
                <jsp:include page="../components/BlogCard.jsp" />
            </div>
            <div class="post-card">
                <jsp:include page="../components/BlogCard.jsp" />
            </div>
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
</section>