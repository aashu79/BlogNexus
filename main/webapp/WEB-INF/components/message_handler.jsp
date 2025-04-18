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
  width: 300px;
}

.toast-message {
  background-color: white;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 6px;
  margin-bottom: 10px;
  overflow: hidden;
  transform: translateX(100%);
  opacity: 0;
  animation: slide-in 0.3s forwards;
}

.toast-message.hide {
  animation: slide-out 0.3s forwards;
}

.toast-content {
  display: flex;
  align-items: center;
  padding: 12px 15px;
}

.toast-success {
  border-left: 4px solid #4CAF50;
}

.toast-error {
  border-left: 4px solid #F44336;
}

.toast-icon {
  margin-right: 12px;
  font-size: 18px;
  width: 20px;
  text-align: center;
}

.toast-success .toast-icon {
  color: #4CAF50;
}

.toast-error .toast-icon {
  color: #F44336;
}

.toast-message-text {
  flex-grow: 1;
  font-family: Arial, sans-serif;
  font-size: 14px;
}

.toast-close {
  background: none;
  border: none;
  color: #888;
  cursor: pointer;
  font-size: 16px;
  margin-left: 10px;
  padding: 0;
}

.toast-close:hover {
  color: #333;
}

.toast-progress {
  height: 3px;
  width: 100%;
  background-color: #e0e0e0;
}

.toast-progress-bar {
  height: 100%;
  width: 100%;
  animation: progress 5s linear forwards;
}

.toast-success .toast-progress-bar {
  background-color: #4CAF50;
}

.toast-error .toast-progress-bar {
  background-color: #F44336;
}

@keyframes slide-in {
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

@keyframes slide-out {
  to {
    transform: translateX(100%);
    opacity: 0;
  }
}

@keyframes progress {
  to {
    width: 0;
  }
}
</style>
</head>
<body>

<jsp:include page="./fontawesome.jsp"></jsp:include>

<div class="toast-container">
  <c:if test="${not empty success}">
    <div id="successMessage" class="toast-message toast-success">
      <div class="toast-content">
        <div class="toast-icon">
          <i class="fas fa-check-circle"></i>
        </div>
        <div class="toast-message-text">
          <c:out value="${success}" />
        </div>
        <button class="toast-close" onclick="closeToast('successMessage')" aria-label="Close">
          <i class="fas fa-times"></i>
        </button>
      </div>
      <div class="toast-progress">
        <div class="toast-progress-bar"></div>
      </div>
    </div>
  </c:if>

  <c:if test="${not empty error}">
    <div id="errorMessage" class="toast-message toast-error">
      <div class="toast-content">
        <div class="toast-icon">
          <i class="fas fa-exclamation-circle"></i>
        </div>
        <div class="toast-message-text">
          <c:out value="${error}" />
        </div>
        <button class="toast-close" onclick="closeToast('errorMessage')" aria-label="Close">
          <i class="fas fa-times"></i>
        </button>
      </div>
      <div class="toast-progress">
        <div class="toast-progress-bar"></div>
      </div>
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
      
      // Remove from session
      <% 
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

// Auto-dismiss after 5 seconds
document.addEventListener('DOMContentLoaded', function() {
  const toasts = document.querySelectorAll('.toast-message');
  toasts.forEach(toast => {
    setTimeout(() => {
      if (toast && toast.id) {
        closeToast(toast.id);
      }
    }, 5000);
  });
});
</script>

</body>
</html>