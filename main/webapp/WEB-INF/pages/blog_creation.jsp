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
<jsp:include page="../components/message_handler.jsp"></jsp:include>
<jsp:include page="../components/navbar.jsp"></jsp:include>
    <div class="blog-container">
        <h1>Create a New Blog Post</h1>

   
            
        <form action="${pageContext.request.contextPath}/user/blog/create" method="post" enctype="multipart/form-data">
            <!-- Blog ID is auto-generated, no need for manual entry -->
            
            <div class="form-group">
                <label for="title">Blog Title</label>
                <input type="text" id="title" name="title" placeholder="Enter your blog title" required>
            </div>
           
            <div class="form-group">
                <label for="content">Blog Content</label>
                <textarea id="content" name="content" placeholder="Write your blog here..." required></textarea>
            </div>

            <div class="form-group">
                <label for="thumbnail">Upload Thumbnail Image</label>
                <input type="file" id="thumbnail" name="thumbnail" accept="image/*" required>
                <small class="form-text">Max file size: 5MB</small>
            </div>

            <div class="form-group">
                <label for="image">Upload Featured Image</label>
                <input type="file" id="image" name="image" accept="image/*" required>
                <small class="form-text">Max file size: 5MB</small>
            </div>

            <div class="form-group">
                <label for="genre">Genre</label>
                <select id="genre" name="genre" required>
                    <option value="">Select a genre</option>
                    <option value="Technology">Technology</option>
                    <option value="Health">Health</option>
                    <option value="Lifestyle">Lifestyle</option>
                    <option value="Travel">Travel</option>
                    <option value="Food">Food</option>
                    <option value="Fashion">Fashion</option>
                    <option value="Business">Business</option>
                    <option value="Education">Education</option>
                    <option value="Entertainment">Entertainment</option>
                    <option value="Sports">Sports</option>
                    <option value="Other">Other</option>
                </select>
            </div>

            <!-- User ID is obtained from session, no need for manual entry -->
            <!-- Created_at is automatically set on the server -->

            <button type="submit" class="submit-btn">Publish Blog</button>
        </form>
    </div>
    <jsp:include page="../components/footer.jsp"></jsp:include>
</body>
</html>