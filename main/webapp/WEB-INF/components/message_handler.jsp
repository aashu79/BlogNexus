<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet"
	href="${contextPath}/css/message_handler.css?v=<%=System.currentTimeMillis()%>">
</head>
<body>

	<jsp:include page="./fontawesome.jsp"></jsp:include>

	<div class="toast-container">
		<%-- Check for success message in both request and session scopes --%>
		<c:if test="${not empty success || not empty sessionScope.success}">
			<div id="successMessage" class="toast-message toast-success"
				data-message-scope="${not empty success ? 'request' : 'session'}">
				<div class="toast-content">
					<div class="toast-icon">
						<i class="fas fa-check-circle"></i>
					</div>
					<div class="toast-message-text">
						<c:out
							value="${not empty success ? success : sessionScope.success}" />
					</div>
				</div>
				<button class="toast-close" onclick="closeToast('successMessage')"
					aria-label="Close">
					<i class="fas fa-times"></i>
				</button>
			</div>
		</c:if>

		<%-- Check for error message in both request and session scopes --%>
		<c:if test="${not empty error || not empty sessionScope.error}">
			<div id="errorMessage" class="toast-message toast-error"
				data-message-scope="${not empty error ? 'request' : 'session'}">
				<div class="toast-content">
					<div class="toast-icon">
						<i class="fas fa-exclamation-circle"></i>
					</div>
					<div class="toast-message-text">
						<c:out value="${not empty error ? error : sessionScope.error}" />
					</div>
				</div>
				<button class="toast-close" onclick="closeToast('errorMessage')"
					aria-label="Close">
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
      
      <%// Clean up both request and session attributes
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
}%>
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