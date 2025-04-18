<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <style>
    .msg-success { 
      background: #e0f9e0; 
      color: #2d662d; 
      border: 1px solid #2d662d; 
      padding: 10px; 
      margin: 10px 0;
    }
    .msg-error { 
      background: #f9e0e0; 
      color: #662d2d; 
      border: 1px solid #662d2d; 
      padding: 10px; 
      margin: 10px 0;
    }
  </style>
</head>
<body>

  <c:if test="${not empty success}">
    <div class="msg-success">
      <c:out value="${success}" />
    </div>
  </c:if>

  <c:if test="${not empty error}">
    <div class="msg-error">
      <c:out value="${error}" />
    </div>
  </c:if>

 

</body>
</html>