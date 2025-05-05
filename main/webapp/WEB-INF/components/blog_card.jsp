<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="blog-card">
    <div class="blog-card-image">
        <a href="${contextPath}/blog/${param.blog_id}">
            <c:choose>
                <c:when test="${not empty param.thumbnailUrl}">
                    <img src="${contextPath}${param.thumbnailUrl}" alt="${param.title}" />
                </c:when>
                <c:otherwise>
                    <img src="${param.thumbnail}" alt="${param.title}" />
                </c:otherwise>
            </c:choose>
        </a>
        <span class="blog-card-category">${param.genre}</span>
    </div>
    <div class="blog-card-content">
        <div class="blog-card-meta">
            <span class="blog-card-date"><i class="far fa-clock"></i> ${param.formattedDate}</span>
            <span class="blog-card-read-time">${param.readTime}</span>
        </div>
        <h3 class="blog-card-title">
            <a href="${contextPath}/blog/${param.blog_id}">${param.title}</a>
        </h3>
        <p class="blog-card-excerpt">${param.content}</p>
        <div class="blog-card-author">
            <c:choose>
                <c:when test="${not empty param.authorProfileUrl}">
                    <img src="${contextPath}${param.authorProfileUrl}" alt="${param.authorName}" class="author-avatar">
                </c:when>
                <c:otherwise>
                    <img src="https://randomuser.me/api/portraits/men/${30 + param.blog_id % 40}.jpg" alt="${param.authorName}" class="author-avatar">
                </c:otherwise>
            </c:choose>
            <div class="author-info">
                <span class="author-name">${param.authorName}</span>
                <span class="author-role">${param.authorRole}</span>
            </div>
        </div>
    </div>
</div>