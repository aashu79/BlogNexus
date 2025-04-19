<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<style>
/* Simple Message System */
.toast-container {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 9999;
  width: 280px;
}

.toast-message {
  background-color: white;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  border-radius: 4px;
  margin-bottom: 10px;
  padding: 10px 15px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  animation: fade-in 0.3s ease;
}

.toast-message.hide {
  animation: fade-out 0.3s ease forwards;
}

.toast-success {
  border-left: 3px solid #4CAF50;
}

.toast-error {
  border-left: 3px solid #F44336;
}

.toast-content {
  display: flex;
  align-items: center;
}

.toast-icon {
  margin-right: 10px;
  font-size: 16px;
}

.toast-success .toast-icon {
  color: #4CAF50;
}

.toast-error .toast-icon {
  color: #F44336;
}

.toast-message-text {
  font-family: Arial, sans-serif;
  font-size: 14px;
}

.toast-close {
  background: none;
  border: none;
  color: #999;
  cursor: pointer;
  font-size: 14px;
  padding: 0;
  margin-left: 8px;
}

.toast-close:hover {
  color: #333;
}

@keyframes fade-in {
  from {
    opacity: 0;
    transform: translateY(-10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fade-out {
  to {
    opacity: 0;
    transform: translateY(-10px);
  }
}
</style>
</head>
<body>

<jsp:include page="./fontawesome.jsp"></jsp:include>

<div class="toast-container">
  <%-- Check for success message in both request and session scopes --%>
  <c:if test="${not empty success || not empty sessionScope.success}">
    <div id="successMessage" class="toast-message toast-success" data-message-scope="${not empty success ? 'request' : 'session'}">
      <div class="toast-content">
        <div class="toast-icon">
          <i class="fas fa-check-circle"></i>
        </div>
        <div class="toast-message-text">
          <c:out value="${not empty success ? success : sessionScope.success}" />
        </div>
      </div>
      <button class="toast-close" onclick="closeToast('successMessage')" aria-label="Close">
        <i class="fas fa-times"></i>
      </button>
    </div>
  </c:if>

  <%-- Check for error message in both request and session scopes --%>
  <c:if test="${not empty error || not empty sessionScope.error}">
    <div id="errorMessage" class="toast-message toast-error" data-message-scope="${not empty error ? 'request' : 'session'}">
      <div class="toast-content">
        <div class="toast-icon">
          <i class="fas fa-exclamation-circle"></i>
        </div>
        <div class="toast-message-text">
          <c:out value="${not empty error ? error : sessionScope.error}" />
        </div>
      </div>
      <button class="toast-close" onclick="closeToast('errorMessage')" aria-label="Close">
        <i class="fas fa-times"></i>
      </button>
    </div>
  </c:if>
</div>

<script>
function closeToast(id) {
  const toast = document.getElementById(id);
  if (toast) {
    toast.classList.add('hide');
    setTimeout(() => {
      toast.remove();
      
      <% 
      // Clean up both request and session attributes
      if (request.getAttribute("success") != null) {
        request.removeAttribute("success");
      }
      if (request.getAttribute("error") != null) {
        request.removeAttribute("error");
      }
      if (session.getAttribute("success") != null) {
        session.removeAttribute("success");
      }
      if (session.getAttribute("error") != null) {
        session.removeAttribute("error");
      }
      %>
    }, 300);
  }
}

// Auto-dismiss after 4 seconds
document.addEventListener('DOMContentLoaded', function() {
  const toasts = document.querySelectorAll('.toast-message');
  toasts.forEach(toast => {
    setTimeout(() => {
      if (toast && toast.id) {
        closeToast(toast.id);
      }
    }, 4000);
  });
});
</script>

</body>
</html>