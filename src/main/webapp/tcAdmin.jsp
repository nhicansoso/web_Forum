<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Trang chủ Admin - Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>
	<c:set var="activeMenu" value="dashboard" />
	<%@ include file="sidebarAdmin.jsp"%>

	<div class="main-content">
		<div class="d-flex justify-content-between align-items-center mb-4">
			<div>
				<h3 class="fw-bold text-dark m-0">Tổng quan</h3>
				<p class="text-muted small m-0">Chào mừng trở lại, Admin</p>
			</div>

			<form action="tcAdminController" method="post"
				class="filter-container">
				<div class="d-flex align-items-center text-muted">
					<i class="fa-solid fa-filter me-2"></i> <span
						class="fw-bold small text-uppercase me-2">Lọc:</span>
				</div>

				<select name="thang"
					class="form-select form-select-sm border-0 bg-light fw-bold text-dark"
					style="width: 110px;">
					<c:forEach var="i" begin="1" end="12">
						<option value="${i}"
							<c:if test="${i == selectedThang}">selected</c:if>>Tháng
							${i}</option>
					</c:forEach>
				</select> <select name="nam"
					class="form-select form-select-sm border-0 bg-light fw-bold text-dark"
					style="width: 90px;">
					<c:forEach var="i" begin="${nowYear - 5}" end="${nowYear}" step="1">
						<option value="${i}"
							<c:if test="${i == selectedNam}">selected</c:if>>${i}</option>
					</c:forEach>
				</select>

				<button type="submit"
					class="btn btn-primary btn-sm px-3 rounded-pill shadow-sm">
					<i class="fa-solid fa-pen-to-square"></i> Xem
				</button>
			</form>
		</div>

		<div class="row g-4 mb-4">
			<div class="col-md-6">
				<div
					class="stat-card p-4 d-flex align-items-center justify-content-between">
					<div>
						<h6 class="text-muted text-uppercase small fw-bold mb-2">Người
							đăng ký (T${selectedThang})</h6>
						<h2 class="fw-bold text-dark mb-0">${userInMonth}</h2>
						<div
							class="badge bg-success bg-opacity-10 text-success mt-2 rounded-pill px-3"></div>
					</div>
					<div class="stat-icon bg-success bg-opacity-10 text-success">
						<i class="fa-solid fa-users"></i>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div
					class="stat-card p-4 d-flex align-items-center justify-content-between">
					<div>
						<h6 class="text-muted text-uppercase small fw-bold mb-2">Bài
							viết mới (T${selectedThang})</h6>
						<h2 class="fw-bold text-dark mb-0">${postInMonth}</h2>
						<div
							class="badge bg-primary bg-opacity-10 text-primary mt-2 rounded-pill px-3"></div>
					</div>
					<div class="stat-icon bg-primary bg-opacity-10 text-primary">
						<i class="fa-solid fa-newspaper"></i>
					</div>
				</div>
			</div>
		</div>

		<div class="row g-4 mb-4">
			<div class="col-md-6">
				<div
					class="stat-card p-4 d-flex align-items-center justify-content-between">
					<div>
						<h6 class="text-muted text-uppercase small fw-bold mb-2">
							Tổng người dùng</h6>
						<h2 class="fw-bold text-dark mb-0">${totalUsers}</h2>
						<div
							class="badge bg-success bg-opacity-10 text-success mt-2 rounded-pill px-3"></div>
					</div>
					<div class="stat-icon bg-success bg-opacity-10 text-success">
						<i class="fa-solid fa-user-plus"></i>
					</div>
				</div>
			</div>

			<div class="col-md-6">
				<div
					class="stat-card p-4 d-flex align-items-center justify-content-between">
					<div>
						<h6 class="text-muted text-uppercase small fw-bold mb-2">
							Tổng bài viết</h6>
						<h2 class="fw-bold text-dark mb-0">${totalPosts}</h2>
						<div
							class="badge bg-primary bg-opacity-10 text-primary mt-2 rounded-pill px-3"></div>
					</div>
					<div class="stat-icon bg-primary bg-opacity-10 text-primary">
						<i class="fa-solid fa-file-invoice"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
