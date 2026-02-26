<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quản lý Bài viết</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	rel="stylesheet">
</head>
<body>

	<c:set var="activeMenu" value="posts" />
	<%@ include file="sidebarAdmin.jsp"%>

	<div class="main-content">

		<div class="d-flex justify-content-between align-items-center mb-4">
			<h3 class="fw-bold m-0 text-dark">Quản lý Bài viết</h3>

			<form action="baivietAdminController" method="get"
				class="search-box d-flex">
				<input type="text" name="key" class="form-control"
					placeholder="  Tìm người đăng..." value="${key != null ? key : ''}"
					style="width: 250px">
				<button class="btn btn-white bg-white text-primary fw-bold">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
		</div>

		<div class="card p-0 overflow-hidden">
			<div class="table-responsive">
				<table class="table table-hover mb-0 align-middle">
					<thead class="table-light">
						<tr>
							<th class="ps-4" style="width: 120px">ID</th>
							<th style="width: 230px">Người đăng</th>
							<th class="text-center" style="width: 120px">Lượt thích</th>
							<th class="text-center" style="width: 120px">Bình luận</th>
							<th class="text-center" style="width: 140px">Xem nội dung</th>
							<th class="text-center" style="width: 140px">Xem hình ảnh</th>
							<th class="text-center pe-4" style="width: 120px">Hành động</th>
						</tr>
					</thead>

					<tbody>
						<c:choose>
							<c:when test="${not empty dsBaiDang}">
								<c:forEach var="p" items="${dsBaiDang}">
									<tr>
										<!-- ID -->
										<td class="ps-4 fw-bold text-muted">#${p.maBaiDang}</td>

										<td>
											<div class="d-flex align-items-center gap-3">
												<div class="fw-bold">@${p.tenDangNhap}</div>
											</div>
										</td>

										<td class="text-center"><span
											class="badge bg-danger bg-opacity-10 text-danger px-3 py-2 rounded-pill">
												${p.soLike} </span></td>

										<td class="text-center"><span
											class="badge bg-info bg-opacity-10 text-info px-3 py-2 rounded-pill">
												${p.danhSachCmt != null ? p.danhSachCmt.size() : 0} </span></td>

										<td class="text-center">
											<c:if test="${not empty p.noiDung}">
												<button
													class="btn btn-sm btn-outline-primary rounded-pill px-3"
													data-bs-toggle="modal"
													data-bs-target="#noiDung${p.maBaiDang}">
													<i class="fa-solid fa-eye"></i>
												</button>
											</c:if> 
											<c:if test="${empty p.noiDung}">
												<span class="text-muted">—</span>
											</c:if>
										</td>

										<td class="text-center">
											<c:if
												test="${not empty p.hinhAnh}">
												<button
													class="btn btn-sm btn-outline-info rounded-pill px-3"
													data-bs-toggle="modal"
													data-bs-target="#hinhAnh${p.maBaiDang}">
													<i class="fa-solid fa-image"></i>
												</button>
											</c:if> 
											<c:if test="${empty p.hinhAnh}">
												<span class="text-muted">—</span>
											</c:if>
										</td>

										<td class="text-center pe-4">
											<form action="baivietAdminController" method="post"
												style="display: inline;">
												<input type="hidden" name="action" value="delete"> <input
													type="hidden" name="id" value="${p.maBaiDang}">

												<button type="submit"
													class="btn btn-sm btn-outline-danger rounded-pill px-3">
													<i class="fa-solid fa-trash"></i>
												</button>
											</form>
										</td>
									</tr>

									<div class="modal fade" id="noiDung${p.maBaiDang}"
										tabindex="-1">
										<div class="modal-dialog modal-dialog-centered modal-lg">
											<div class="modal-content rounded-4 shadow border-0">
												<div class="modal-body p-4">
													<h5 class="fw-bold mb-3">Nội dung bài viết</h5>
													<p>${p.noiDung}</p>

													<div class="text-center mt-4">
														<button class="btn btn-secondary rounded-pill px-4"
															data-bs-dismiss="modal">Đóng</button>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="modal fade" id="hinhAnh${p.maBaiDang}"
										tabindex="-1">
										<div class="modal-dialog modal-dialog-centered modal-lg">
											<div class="modal-content rounded-4 shadow border-0">
												<div class="modal-body p-4 text-center">

													<img src="${p.hinhAnh}" class="img-fluid rounded"
														style="max-height: 70vh; width: auto; object-fit: contain;">

													<div class="mt-4">
														<button class="btn btn-secondary rounded-pill px-4"
															data-bs-dismiss="modal">Đóng</button>
													</div>

												</div>
											</div>
										</div>
									</div>


								</c:forEach>
							</c:when>

							<c:otherwise>
								<tr>
									<td colspan="8" class="text-center py-5 text-muted"><i
										class="fa-solid fa-inbox fa-2x mb-2"></i><br> Không có
										bài viết</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
