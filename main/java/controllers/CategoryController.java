package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.BlogModel;
import service.CategoryService;

/**
 * Servlet implementation for the CategoryController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/category" })
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryService categoryService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
        super();
        this.categoryService = new CategoryService();
    }

    /**
     * Handles GET requests to serve the category page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get category parameter
        String category = request.getParameter("cat");
        
        // Redirect to home if category is null or empty
        if (category == null || category.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        
        // Get blogs for the category
        Map<String, Object> result = categoryService.getBlogsByCategory(category);
        List<BlogModel> blogs = (List<BlogModel>) result.get("blogs");
        Map<Integer, Map<String, String>> authorDetails = (Map<Integer, Map<String, String>>) result.get("authorDetails");
        
       
        
        // Set attributes for JSP
        request.setAttribute("category", category);
        request.setAttribute("categoryDescription", categoryService.getCategoryDescription(category));
        request.setAttribute("categoryIcon", categoryService.getCategoryIcon(category));
       
        request.setAttribute("categoryBlogs", blogs);
        request.setAttribute("authorDetails", authorDetails);
        
        // Forward to JSP
        request.getRequestDispatcher("/WEB-INF/pages/category_page.jsp").forward(request, response);
    }
}