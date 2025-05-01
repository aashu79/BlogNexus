<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Blog - BlogNexus</title>
    <link rel="stylesheet" href="${contextPath}/css/blog_editing.css">
    <!-- Add FontAwesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        .image-preview {
            max-width: 200px;
            margin-top: 10px;
            border: 1px solid #ddd;
            padding: 5px;
            border-radius: 4px;
        }
        .image-preview img {
            width: 100%;
        }
        .current-file {
            font-size: 12px;
            color: #666;
            margin-top: 5px;
        }
        .form-hint {
            font-size: 12px;
            color: #666;
            margin-top: 5px;
        }
        .form-actions {
            display: flex;
            margin-top: 20px;
        }
        .btn {
            padding: 8px 16px;
            margin-right: 10px;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
            text-decoration: none;
            display: inline-block;
        }
        .btn-outline {
            border: 1px solid #3498db;
            color: #3498db;
            background: transparent;
        }
        .btn-outline:hover {
            background: #f0f7fc;
        }
    </style>
</head>
<body>
<jsp:include page="../components/navbar.jsp"></jsp:include>
<jsp:include page="../components/message_handler.jsp"></jsp:include>

    <div class="blog-container">
        <h1>Edit Blog Post</h1>
            
        <form action="${contextPath}/user/blog/edit" method="post" enctype="multipart/form-data" id="updateBlogForm">
            <!-- Hidden input for blog ID - this is crucial -->
            <input type="hidden" id="blogId" name="id" value="${blog.blogId}">
            
            <div class="form-group">
                <label for="title">Blog Title</label>
                <input type="text" id="title" name="title" placeholder="Enter your blog title" required value="${blog.title}">
            </div>

            <div class="form-group">
                <label for="content">Blog Content</label>
                <textarea id="content" name="content" placeholder="Write your blog here..." required>${blog.content}</textarea>
            </div>

            <div class="form-group">
                <label for="thumbnail">Update Thumbnail Image (optional)</label>
                <input type="file" id="thumbnail" name="thumbnail" accept="image/*">
                <div class="current-file">Current: ${blog.thumbnail}</div>
                <div class="image-preview">
                    <img src="${contextPath}/resources/imagesblogThumbnails/${blog.thumbnail}" alt="Current Thumbnail" onerror="this.src='${contextPath}/resources/images/placeholder.jpg'">
                </div>
                <p class="form-hint">Leave empty to keep current image</p>
            </div>

            <div class="form-group">
                <label for="image">Update Featured Image (optional)</label>
                <input type="file" id="image" name="image" accept="image/*">
                <div class="current-file">Current: ${blog.image}</div>
                <div class="image-preview">
                    <img src="${contextPath}/resources/imagesblogImages/${blog.image}" alt="Current Image" onerror="this.src='${contextPath}/resources/images/placeholder.jpg'">
                </div>
                <p class="form-hint">Leave empty to keep current image</p>
            </div>

            <div class="form-group">
                <label for="genre">Genre</label>
                <select id="genre" name="genre" required>
                    <option value="" disabled>Select a genre</option>
                    <option value="Technology" ${blog.genre eq 'Technology' ? 'selected' : ''}>Technology</option>
                    <option value="Travel" ${blog.genre eq 'Travel' ? 'selected' : ''}>Travel</option>
                    <option value="Food" ${blog.genre eq 'Food' ? 'selected' : ''}>Food</option>
                    <option value="Health" ${blog.genre eq 'Health' ? 'selected' : ''}>Health</option>
                    <option value="Fashion" ${blog.genre eq 'Fashion' ? 'selected' : ''}>Fashion</option>
                    <option value="Business" ${blog.genre eq 'Business' ? 'selected' : ''}>Business</option>
                    <option value="Entertainment" ${blog.genre eq 'Entertainment' ? 'selected' : ''}>Entertainment</option>
                    <option value="Sports" ${blog.genre eq 'Sports' ? 'selected' : ''}>Sports</option>
                    <option value="Education" ${blog.genre eq 'Education' ? 'selected' : ''}>Education</option>
                    <option value="Other" ${blog.genre eq 'Other' ? 'selected' : ''}>Other</option>
                </select>
            </div>

            <div class="form-group">
                <label>Created At</label>
                <div>2025-05-01 18:13:15</div>
            </div>
            
            <!-- Debug information -->
            <div class="form-group" style="background: #f5f5f5; padding: 10px; border-radius: 4px;">
                <label>Blog ID</label>
                <div><strong>${blog.blogId}</strong></div>
            </div>

            <div class="form-actions">
                <button type="submit" class="submit-btn">Update Blog</button>
                <a href="${contextPath}/user/profile" class="btn btn-outline">Cancel</a>
            </div>
        </form>
    </div>
    
    <!-- JavaScript for form validation and image preview -->
    <script>
        // Validate form before submission
        document.addEventListener('DOMContentLoaded', function() {
            const form = document.getElementById('updateBlogForm');
            const blogIdInput = document.getElementById('blogId');
            
            form.addEventListener('submit', function(event) {
                // Verify blog ID is present
                if (!blogIdInput.value) {
                    event.preventDefault();
                    alert('Blog ID is missing. Please reload the page.');
                    console.error('Blog ID is missing in form submission');
                } else {
                    console.log('Submitting form with blog ID: ' + blogIdInput.value);
                }
            });
            
            // Log blog ID on page load
            console.log('Blog ID on page load: ' + blogIdInput.value);
        });
        
        // Preview thumbnails when selected
        document.getElementById('thumbnail').addEventListener('change', function(e) {
            if (e.target.files && e.target.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    document.querySelector('#thumbnail + .current-file + .image-preview img').src = e.target.result;
                }
                reader.readAsDataURL(e.target.files[0]);
            }
        });
        
        document.getElementById('image').addEventListener('change', function(e) {
            if (e.target.files && e.target.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    document.querySelector('#image + .current-file + .image-preview img').src = e.target.result;
                }
                reader.readAsDataURL(e.target.files[0]);
            }
        });
    </script>
    
    <jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>