<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="refresh" content="60">
<title>User Management - Admin Dashboard</title>
<link rel="stylesheet" href="${contextPath}/css/user_listing.css">
</head>
<body>
	<jsp:include page="../components/fontawesome.jsp"></jsp:include>
	<jsp:include page="../components/message_handler.jsp"></jsp:include>

	<jsp:include page="../components/admin_navbar.jsp" />

	<!-- Main Content -->
	<div class="content">
		<div class="dashboard-header">
			<h1>User Management</h1>
			<div>
				<p>
					Current Date and Time (UTC):
					<fmt:parseDate value="${currentDateTime}"
						pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both" />
					<fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm:ss" />
				</p>
				<p>Current User's Login: ${currentUser.firstName}</p>
			</div>
		</div>

		<!-- Action Bar -->
		<div class="action-bar">
			<a href="${contextPath}/admin/users/add" class="add-user-link"> <i
				class="fas fa-plus"></i> Add New User
			</a>
		</div>

		<!-- Users Table Card -->
		<div class="card section-card">
			<div class="section-header">
				<h2>Users List</h2>
			</div>
			<div class="table-container">
				<table id="usersTable">
					<thead>
						<tr>
							<th>ID</th>
							<th>Profile</th>
							<th>Name</th>
							<th>Email</th>
							<th>Phone</th>
							<th>Country</th>
							<th>Role</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<!-- Dynamically generated user rows -->
						<c:choose>
							<c:when test="${empty users}">
								<tr>
									<td colspan="8" class="text-center">No users found</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${users}" var="user">
									<tr>
										<td>${user.userId}</td>
										<td>
											<div class="user-avatar">
												<c:choose>
													<c:when test="${not empty user.profilePictureUrl}">
														<img src="${contextPath}${user.profilePictureUrl}"
															alt="${user.firstName} ${user.lastName}">
													</c:when>
													<c:otherwise>
														<i class="fas fa-user"></i>
													</c:otherwise>
												</c:choose>
											</div>
										</td>
										<td>${user.firstName}${user.lastName}</td>
										<td>${user.email}</td>
										<td>${not empty user.phone ? user.phone : 'Not provided'}</td>
										<td>${not empty user.country ? user.country : 'Not specified'}</td>
										<td><span
											class="role-badge ${user.userRole == 'admin' ? 'admin' : 'user'}">${user.userRole}</span>
										</td>
										<td>
											<div class="action-buttons">
												<c:choose>
													<c:when test="${user.userId == currentUser.userId}">
														<span class="action-disabled"
															title="Cannot delete yourself"> <i
															class="fas fa-trash-alt"></i> Delete
														</span>
													</c:when>
													<c:otherwise>
														<form method="post"
															action="${contextPath}/admin/users/delete"
															class="delete-form"
															onsubmit="return confirmDelete('${user.firstName} ${user.lastName}');">
															<input type="hidden" name="userId" value="${user.userId}">
															<button type="submit" class="action-link delete"
																title="Delete User">
																<i class="fas fa-trash-alt"></i> Delete
															</button>
														</form>
													</c:otherwise>
												</c:choose>
											</div>
										</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script>
		// Confirm deletion
		function confirmDelete(userName) {
			return confirm("Are you sure you want to delete user '" + userName
					+ "'? This action cannot be undone.");
		}
	</script>
</body>
</html>