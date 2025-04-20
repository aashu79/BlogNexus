<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        </div>
        
   
        
        <!-- Blogs Table Card -->
        <div class="card section-card">
            <div class="section-header">
                <h2>Blogs List</h2>
            </div>
            <div class="table-container">
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
                        <!-- Sample blog data rows -->
                        <tr>
                            <td>1</td>
                            <td>
                                <div class="blog-thumbnail">
                                    <img src="https://via.placeholder.com/150x100" alt="Blog Thumbnail">
                                </div>
                            </td>
                            <td class="blog-title">The Future of Web Development</td>
                            <td><span class="genre-badge tech">Technology</span></td>
                            <td>John Doe</td>
                            <td>2025-04-15</td>
                            <td>
                                <div class="action-buttons">
                                
                                    <a href="${contextPath}/admin/blogs/delete/1" class="action-link delete" title="Delete Blog">
                                        <i class="fas fa-trash-alt"></i> Delete
                                    </a>
                            
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>
                                <div class="blog-thumbnail">
                                    <img src="https://via.placeholder.com/150x100" alt="Blog Thumbnail">
                                </div>
                            </td>
                            <td class="blog-title">Healthy Living: 10 Tips for a Better Lifestyle</td>
                            <td><span class="genre-badge health">Health</span></td>
                            <td>Jane Smith</td>
                            <td>2025-04-12</td>
                            <td>
                                <div class="action-buttons">
                                 
                                    <a href="${contextPath}/admin/blogs/delete/2" class="action-link delete" title="Delete Blog">
                                        <i class="fas fa-trash-alt"></i> Delete
                                    </a>
                                
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>
                                <div class="blog-thumbnail">
                                    <img src="https://via.placeholder.com/150x100" alt="Blog Thumbnail">
                                </div>
                            </td>
                            <td class="blog-title">Travel Chronicles: Exploring the Hidden Gems of Asia</td>
                            <td><span class="genre-badge travel">Travel</span></td>
                            <td>Robert Johnson</td>
                            <td>2025-04-10</td>
                            <td>
                                <div class="action-buttons">
                                 
                                    <a href="${contextPath}/admin/blogs/delete/3" class="action-link delete" title="Delete Blog">
                                        <i class="fas fa-trash-alt"></i> Delete
                                    </a>
                                
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>