<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logging Out</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        
        .logout-container {
            background-color: white;
            border-radius: 8px;
            padding: 2rem;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 500px;
            text-align: center;
            animation: fadeIn 0.5s ease;
        }
        
        .logout-title {
            margin-bottom: 0.5rem;
            color: #333;
        }
        
        .logout-message {
            margin-bottom: 1rem;
            color: #666;
        }
        
        .logout-success {
            color: #4caf50;
            font-weight: 600;
            margin-top: 1rem;
        }
        
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
    <script>
        window.onload = function() {
            // Simply redirect after a short delay
            setTimeout(function() {
                window.location.href = '${pageContext.request.contextPath}/';
            }, 2000);
        };
    </script>
</head>
<body>
    <div class="logout-container">
        <h1 class="logout-title">Logging Out</h1>
        <p class="logout-message">You are being signed out...</p>
        <p class="logout-success">Redirecting to homepage</p>
    </div>
</body>
</html>