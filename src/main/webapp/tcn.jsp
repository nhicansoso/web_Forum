<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>MyGram - Trang cá nhân</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/css/tcn.css" rel="stylesheet">
</head>

<body class="bg-light">

	<%@ include file="navbar.jsp"%>

	<div class="container mt-4">

		<c:if test="${not empty tbLoi}">
			<div class="alert alert-danger text-center my-2">${tbLoi}</div>
		</c:if>

		<c:if test="${not empty tbThanhCong}">
			<div class="alert alert-success text-center my-2">
				${tbThanhCong}</div>
		</c:if>
		<div class="row align-items-center">

			<div class="col-md-4 text-center">
				<img src="${profileUser.anhDaiDien}" class="avatar-large mb-3">
			</div>

			<div class="col-md-8">
				<h2 class="fw-bold">${profileUser.tenDangNhap}</h2>

				<div class="d-flex gap-4 my-3">
					<span><strong>${ds.size()}</strong> bài viết</span> <span><strong>${soNguoiTheoDoi}</strong>
						người theo dõi</span> <span><strong>${soDangTheoDoi}</strong> đang
						theo dõi</span>
				</div>

				<p class="text-muted">${profileUser.tieuSu}</p>


				<c:if test="${isOwner}">
					<div class="d-flex gap-2 mt-2">
						<button class="btn btn-outline-secondary btn-sm"
							data-bs-toggle="modal" data-bs-target="#editProfileModal">
							Chỉnh sửa trang cá nhân</button>

						<button class="btn btn-outline-secondary btn-sm"
							data-bs-toggle="modal" data-bs-target="#changePasswordModal">
							Đổi mật khẩu</button>
					</div>
				</c:if>

			</div>
		</div>
	</div>

	<div class="container my-4" style="max-width: 600px">

		<c:if test="${empty ds}">
			<h5 class="text-center text-muted">Người này chưa có bài viết.</h5>
		</c:if>

		<c:forEach var="p" items="${ds}">
			<div class="card post-card">

				<div class="post-header">
					<div class="d-flex align-items-center">
						<img
							src="${pageContext.request.contextPath}/${profileUser.anhDaiDien}"
							width="40" height="40" class="rounded-circle me-3 border">
						<span class="fw-bold">${profileUser.tenDangNhap}</span>
					</div>

					<c:if test="${isOwner}">
						<form method="post" action="xoabaivietController"
							style="display: inline;">
							<input type="hidden" name="maBaiDang" value="${p.maBaiDang}">
							<input type="hidden" name="from" value="tcn">
							<button type="submit" class="btn btn-sm btn-outline-danger ms-2">Xóa</button>
						</form>
					</c:if>


				</div>

				<div class="card-body pt-0 pb-0 px-3">
					<p class="mb-2">${p.noiDung}</p>
				</div>

				<c:if test="${not empty p.hinhAnh}">
					<img src="${p.hinhAnh}"
						class="post-image-minimal" onerror="this.style.display='none'">
				</c:if>

				<div class="post-content-area">

					<div class="d-flex mb-2">

						<button type="button" 
						    class="btn btn-light rounded-pill px-3 like-btn me-2 
						    ${p.daLike ? 'text-danger' : 'text-dark'}" 
						    data-id="${p.maBaiDang}">
						    
						    <i class="bi ${p.daLike ? 'bi-heart-fill' : 'bi-heart'} me-1"></i>
						    <span class="like-count">${p.soLike}</span>
						</button>

						<button class="btn btn-light rounded-pill px-3"
							data-bs-toggle="modal"
							data-bs-target="#viewCommentsModal${p.maBaiDang}">
							<i class="bi bi-chat-text-fill me-1"></i> Bình luận
						</button>

						<div class="modal fade" id="viewCommentsModal${p.maBaiDang}"
							tabindex="-1" aria-hidden="true">

							<div class="modal-dialog modal-dialog-centered modal-lg">
								<div class="modal-content d-flex flex-column"
									style="max-height: 85vh;">

									<div class="modal-header">
										<a href="tcnController?username=${p.tenDangNhap}"
											class="d-flex align-items-center text-decoration-none text-dark">
											<img src="${p.anhDaiDien}" width="40" height="40"
											class="rounded-circle me-2">
											<b>${p.tenDangNhap}</b>
										</a>
										<button class="btn-close" data-bs-dismiss="modal"></button>
									</div>

									<div class="modal-body p-0 flex-grow-1 overflow-auto">

										<div class="card border-0">
											<div class="card-body p-0">
												<p class="px-3 pt-3">${p.noiDung}</p>

												<c:if test="${not empty p.hinhAnh}">
													<div class="text-center bg-light p-2 rounded-3 mx-3 mb-3"
														style="min-height: 300px; display: flex; align-items: center; justify-content: center;">
														<img src="${p.hinhAnh}"
															class="img-fluid rounded shadow-sm"
															style="max-height: 500px; width: auto; object-fit: contain;">
													</div>
												</c:if>
											</div>
										</div>

										<div class="comment-list px-3 pb-2"
											id="comment-list-${p.maBaiDang}">
											<c:forEach var="cmt" items="${p.danhSachCmt}">
												<div class="mb-2">
													<a href="tcnController?username=${cmt.tenNguoiDung}"
														class="fw-bold text-dark text-decoration-none">
														${cmt.tenNguoiDung} </a>: ${cmt.noiDung}
												</div>
											</c:forEach>
										</div>

									</div>

									<div class="modal-footer p-2 border-top">
										<form method="post" class="comment-form w-100"
											data-id="${p.maBaiDang}">
											<input type="hidden" name="maBaiDang" value="${p.maBaiDang}">
											<div class="input-group">
												<input type="text" name="noiDung"
													class="form-control rounded-pill"
													placeholder="Viết bình luận..." required autocomplete="off">
												<button class="btn btn-primary rounded-circle">
													<i class="bi bi-send-fill"></i>
												</button>
											</div>
										</form>
									</div>

								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<c:if test="${isOwner}">
		<div class="modal fade" id="editProfileModal" tabindex="-1"
			aria-labelledby="editProfileModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<form method="post" action="capnhattcnController"
					enctype="multipart/form-data" class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editProfileModalLabel">Chỉnh sửa
							trang cá nhân</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Đóng"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3 text-center">
							<img
								src="${profileUser.anhDaiDien}"
								class="avatar-large mb-2" id="previewAvatar"> <input
								type="file" name="anhDaiDien" class="form-control"
								onchange="previewImage(this)">
						</div>
						<div class="mb-3">
							<label class="form-label">Tên đăng nhập</label> <input
								type="text" name="tenDangNhap"
								value="${profileUser.tenDangNhap}" class="form-control" required>
						</div>

						<div class="mb-3">
							<label class="form-label">Họ và tên</label> <input type="text"
								name="hoTen" value="${profileUser.hoTen}" class="form-control"
								required>
						</div>
						<div class="mb-3">
							<label class="form-label">Email</label> <input type="email"
								name="email" value="${profileUser.email}" class="form-control"
								required>
						</div>
						<div class="mb-3">
							<label class="form-label">Tiểu sử</label>
							<textarea name="tieuSu" class="form-control" rows="3"
								placeholder="Viết tiểu sử...">${profileUser.tieuSu != null ? profileUser.tieuSu : ''}</textarea>
						</div>

					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Lưu thay
							đổi</button>
					</div>
				</form>
			</div>
		</div>
	</c:if>

	<c:if test="${isOwner}">
		<div class="modal fade" id="changePasswordModal" tabindex="-1"
			aria-labelledby="changePasswordModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<form method="post" action="doimatkhauController"
					class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="changePasswordModalLabel">Đổi mật
							khẩu</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Đóng"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label class="form-label">Mật khẩu hiện tại</label> <input
								type="password" name="matKhauCu" class="form-control" required>
						</div>
						<div class="mb-3">
							<label class="form-label">Mật khẩu mới</label> <input
								type="password" name="matKhauMoi" class="form-control" required>
						</div>
						<div class="mb-3">
							<label class="form-label">Xác nhận mật khẩu mới</label> <input
								type="password" name="xacNhanMatKhauMoi" class="form-control"
								required>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">Đổi mật
							khẩu</button>
					</div>
				</form>
			</div>
		</div>
	</c:if>


	<script src="assets/js/previewAvatar.js"></script>
	<script src="assets/js/like.js"></script>
	<script src="assets/js/binhluan.js"></script>
</body>
</html>