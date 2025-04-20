<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New Blog - BlogNexus</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/blog_creation.css">
</head>
<body>
<jsp:include page="../components/navbar.jsp"></jsp:include>
    <div class="blog-container">
        <h1>Create a New Blog Post</h1>

       
            
		<form action="createBlogProcess.jsp" method="post" enctype="multipart/form-data">
			
			<div class="form-group">
                <label for="blog_id">Blog ID</label>
                <input type="number" id="blog_id" name="blog_id" placeholder="Enter the blog ID" required>
            </div>
    		<div class="form-group">
        	<label for="title">Blog Title</label>
        	<input type="text" id="title" name="title" placeholder="Enter your blog title" required>
    		</div>
           

            <div class="form-group">
                <label for="content">Blog Content</label>
                <textarea id="content" name="content" placeholder="Write your blog here..." required></textarea>
            </div>

            <div class="form-group">
                <label for="thumbnail">Upload Thumbnail Image (optional)</label>
                <input type="file" id="thumbnail" name="thumbnail" accept="image/*">
            </div>

            <div class="form-group">
                <label for="image">Upload Featured Image (optional)</label>
                <input type="file" id="image" name="image" accept="image/*">
            </div>

            <div class="form-group">
                <label for="genre">Genre</label>
                <input type="text" id="genre" name="genre" placeholder="Enter the genre" required>
            </div>

            <div class="form-group">
                <label for="created_by">Created By (User ID)</label>
                <input type="number" id="created_by" name="created_by" placeholder="Enter your user ID" required>
            </div>

            <div class="form-group">
                <label for="created_at">Created At</label>
                <input type="datetime-local" id="created_at" name="created_at" required>
            </div>

            <button type="submit" class="submit-btn">Publish Blog</button>
        </form>
    </div>
    <jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>
