<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Trang đăng nhập</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body class="bg-light">

	<div class="container mt-5">
		<div class="col-md-4 mx-auto">

			<div class="card shadow border-0 rounded-4">
				<div class="card-header text-center bg-transparent border-0 pt-4">
					<h4 class="fw-bold text-primary">Đăng nhập</h4>
				</div>

				<div class="card-body p-4">

					<c:if test="${not empty tb}">
						<div class="alert alert-danger text-center p-2 mb-3" role="alert">
							${tb}</div>
					</c:if>

					<form action="dangnhapController" method="post">

						<div class="mb-3">
							<label class="form-label fw-semibold">Tên đăng nhập</label> <input
								type="text" name="tenDangNhap" class="form-control"
								placeholder="Nhập username..." required>
						</div>

						<div class="mb-3">
							<label class="form-label fw-semibold">Mật khẩu</label> <input
								type="password" name="matKhau" class="form-control"
								placeholder="Nhập mật khẩu..." required>
						</div>

						<button class="btn btn-primary w-100 mb-3 py-2 fw-bold">Đăng
							nhập</button>

					</form>

					<div class="text-center mt-3">
						<span class="text-muted">Chưa có tài khoản?</span> <a
							href="dangkyController" class="fw-bold text-decoration-none ms-1">Đăng
							ký ngay</a>
					</div>

				</div>
			</div>

		</div>
	</div>

</body>
</html>