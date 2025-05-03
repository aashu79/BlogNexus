package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.BlogModel;
import model.CommentModel;
import model.UserModel;
import service.BlogDetailService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controller for displaying blog detail page with comments, favourites, and likes
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/blog/*", "/blog-detail", "/blog/comment", "/blog/favourite", "/blog/like" })
public class BlogDetail extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BlogDetailService blogDetailService;
       
    /**
     * Initialize controller with services
     */
    public BlogDetail() {
        super();
        this.blogDetailService = new BlogDetailService();
    }

    /**
     * Handle GET requests to display blog detail page
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String servletPath = request.getServletPath();
        
        // Check if it's one of the action paths
        if ("/blog/comment".equals(servletPath) || "/blog/favourite".equals(servletPath) || "/blog/like".equals(servletPath)) {
            response.sendRedirect(request.getContextPath() + "/blogs");
            return;
        } else if ("/blog-detail".equals(servletPath)) {
            // Legacy URL support
            String blogIdParam = request.getParameter("id");
            if (blogIdParam != null && !blogIdParam.trim().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/blog/" + blogIdParam);
                return;
            } else {
                response.sendRedirect(request.getContextPath() + "/blogs");
                return;
            }
        }
        
        // Extract blog ID from path
        int blogId = -1;
        if (pathInfo != null && pathInfo.length() > 1) {
            try {
                blogId = Integer.parseInt(pathInfo.substring(1));
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/blogs");
                return;
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/blogs");
            return;
        }
        
        // Get the blog details
        BlogModel blog = blogDetailService.getBlogWithAuthorById(blogId);
        
        if (blog == null) {
            // Blog not found
            response.sendRedirect(request.getContextPath() + "/blogs");
            return;
        }
        
        // Get author profile picture URL
        String authorProfilePictureUrl = blogDetailService.getAuthorProfilePictureUrl(blogId);
        request.setAttribute("authorProfilePictureUrl", authorProfilePictureUrl);
        
        // Get author role based on genre
        String authorRole = blogDetailService.getAuthorRoleByGenre(blog.getGenre());
        request.setAttribute("authorRole", authorRole);
        
        // Get comments for this blog
        List<CommentModel> comments = blogDetailService.getCommentsByBlogId(blogId);
        
        // Get comment count
        int commentCount = blogDetailService.getCommentCount(blogId);
        
        // Calculate read time
        int readTimeMinutes = blogDetailService.calculateReadTime(blog.getContent());
        
        // Get like count
        int likeCount = blogDetailService.getLikeCount(blogId);
        
        // User-specific data (if logged in)
        boolean isLoggedIn = false;
        boolean isFavourite = false;
        boolean isLiked = false;
        UserModel currentUser = null;
        
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            isLoggedIn = true;
            currentUser = (UserModel) session.getAttribute("user");
            
            if (currentUser != null) {
                isFavourite = blogDetailService.isFavourite(blogId, currentUser.getUserId());
                isLiked = blogDetailService.isLiked(blogId, currentUser.getUserId());
            }
        }
        
        // Set attributes for JSP
        request.setAttribute("blog", blog);
        request.setAttribute("comments", comments);
        request.setAttribute("commentCount", commentCount);
        request.setAttribute("readTime", readTimeMinutes);
        request.setAttribute("likeCount", likeCount);
        request.setAttribute("isLoggedIn", isLoggedIn);
        request.setAttribute("isFavourite", isFavourite);
        request.setAttribute("isLiked", isLiked);
        request.setAttribute("currentUser", currentUser);
        
        // Add current date/time for display
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        request.setAttribute("currentDateTime", now.format(formatter));
        
        // Format the blog date in a friendly way
        if (blog.getCreatedAt() != null) {
            DateTimeFormatter blogFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
            request.setAttribute("formattedDate", blog.getCreatedAt().format(blogFormatter));
        }
        
        // Forward to JSP
        request.getRequestDispatcher("/WEB-INF/pages/blog_detail.jsp").forward(request, response);
    }

    /**
     * Handle POST requests for comments, favourites, and likes
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        
        if ("/blog/comment".equals(servletPath)) {
            blogDetailService.processCommentSubmission(request, response);
        } else if ("/blog/favourite".equals(servletPath)) {
            blogDetailService.processFavouriteToggle(request, response);
        } else if ("/blog/like".equals(servletPath)) {
            blogDetailService.processLikeToggle(request, response);
        } else {
            // Default fallback
            doGet(request, response);
        }
    }
}