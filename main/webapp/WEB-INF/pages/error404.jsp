<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page Not Found</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            color: #343a40;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            text-align: center;
        }
        
        .error-container {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 40px;
            max-width: 500px;
            width: 90%;
        }
        
        .error-code {
            font-size: 72px;
            font-weight: bold;
            color: #dc3545;
            margin: 0;
        }
        
        .error-message {
            font-size: 24px;
            margin: 16px 0 24px;
        }
        
        .error-description {
            color: #6c757d;
            margin-bottom: 32px;
            line-height: 1.5;
        }
        
        .home-button {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 12px 24px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .home-button:hover {
            background-color: #0069d9;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1 class="error-code">404</h1>
        <h2 class="error-message">Page Not Found</h2>
        <p class="error-description">
            The page you are looking for might have been removed, had its name changed, 
            or is temporarily unavailable.
        </p>
        <a href="${pageContext.request.contextPath}/">
            <button class="home-button">Go to Home Page</button>
        </a>
    </div>
</body>
</html>