<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang đăng ký</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">
	<div class="container mt-5" style="max-width: 420px">

		<form action="dangkyController" method="post" enctype="multipart/form-data">

			<div class="text-center mb-3">
				<img id="previewAvatar" src="assets/avatar/default.png"
					width="120" height="120" class="rounded-circle"
					style="object-fit: cover; border: 1px solid #ccc">
			</div>

			<input class="form-control mb-2" name="tenDangNhap"
				placeholder="Tên đăng nhập" required> 
			<input class="form-control mb-2" type="email" name="email"
				placeholder="Email" required> 
			<input class="form-control mb-2" type="password" name="matKhau"
				placeholder="Mật khẩu" required> 
			<input class="form-control mb-2" name="hoTen" placeholder="Họ tên">

			<input class="form-control mb-3" type="file" name="anhDaiDien"
				accept="image/*" onchange="previewImage(this)">

			<button class="btn btn-primary w-100">Đăng ký</button>

			<c:if test="${not empty tb}">
				<div class="text-danger mt-2 text-center">${tb}</div>
			</c:if>

		</form>
		
		<div class="text-center mt-3">
			<span>Bạn đã có tài khoản? </span> <a href="dangnhapController"
				class="fw-bold text-decoration-none"> Đăng nhập </a>
		</div>
		
	</div>
	<script src="assets/js/previewAvatar.js"></script>
</body>
</html>
