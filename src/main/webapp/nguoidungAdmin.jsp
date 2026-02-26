<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quản lý người dùng</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	rel="stylesheet">
</head>
<body>

	<c:set var="activeMenu" value="users" />
	<%@ include file="sidebarAdmin.jsp"%>

	<div class="main-content">
		<div class="d-flex justify-content-between align-items-center mb-4">
			<h3 class="fw-bold m-0 text-dark">Quản lý Người dùng</h3>

			<form action="nguoidungAdminController" method="get"
				class="search-box d-flex">
				<input type="text" name="key" class="form-control"
					placeholder="Tìm người dùng, gmail..."
					value="${key != null ? key : ''}" style="width: 250px;">
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
							<th style="width: 100px" class="ps-4">ID</th>
							<th style="width: 300px">Người dùng</th>
							<th class="text-center" style="width: 120px">Bài viết</th>
							<th class="text-center" style="width: 130px">Theo dõi</th>
							<th class="text-center" style="width: 150px">Đang theo dõi</th>
							<th class="text-center pe-4" style="width: 140px">Hành động</th>
						</tr>
					</thead>

					<tbody>
						<c:choose>
							<c:when test="${not empty dsNguoiDung}">
								<c:forEach var="v" items="${dsNguoiDung}">
									<tr>
										<td class="ps-4 fw-bold text-muted">#${v.nguoiDung.maND}
										</td>

										<td>
											<div class="d-flex align-items-center gap-3">
												<img src="${v.nguoiDung.anhDaiDien}" class="rounded-circle"
													width="42" height="42" style="object-fit: cover">
												<div>
													<div class="fw-bold">@${v.nguoiDung.tenDangNhap}</div>
													<small class="text-muted">${v.nguoiDung.hoTen}</small>
												</div>
											</div>
										</td>

										<td class="text-center">
											<div class="d-flex justify-content-center">
												<span
													class="badge bg-primary bg-opacity-10 text-primary
													px-3 py-2 rounded-pill">
													${v.soBaiViet} </span>
											</div>
										</td>

										<td class="text-center">
											<div class="d-flex justify-content-center">
												<span
													class="badge bg-danger bg-opacity-10 text-danger
													px-3 py-2 rounded-pill">
													${v.soNguoiTheoDoi} </span>
											</div>
										</td>

										<td class="text-center">
											<div class="d-flex justify-content-center">
												<span
													class="badge bg-info bg-opacity-10 text-info
													px-3 py-2 rounded-pill">
													${v.soDangTheoDoi} </span>
											</div>
										</td>

										<td class="text-center pe-4">
											<div class="d-flex justify-content-center gap-2">

												<button
													class="btn btn-sm btn-outline-primary rounded-pill px-3"
													data-bs-toggle="modal"
													data-bs-target="#detail${v.nguoiDung.maND}">
													<i class="fa-solid fa-eye"></i>
												</button>

												<c:choose>
													<c:when test="${v.biKhoa}">
														<form action="nguoidungAdminController" method="post">
															<input type="hidden" name="action" value="unlock">
															<input type="hidden" name="id"
																value="${v.nguoiDung.maND}">

															<button type="submit"
																class="btn btn-sm btn-outline-success rounded-pill px-3"
																title="Mở khoá tài khoản">
																<i class="fa-solid fa-lock-open"></i>
															</button>
														</form>
													</c:when>
													<c:otherwise>
														<form action="nguoidungAdminController" method="post">
															<input type="hidden" name="action" value="lock">
															<input type="hidden" name="id"
																value="${v.nguoiDung.maND}">

															<button type="submit"
																class="btn btn-sm btn-outline-warning rounded-pill px-3"
																title="Khoá tài khoản">
																<i class="fa-solid fa-lock"></i>
															</button>
														</form>
													</c:otherwise>
												</c:choose>

												<form action="nguoidungAdminController" method="post"
													style="display: inline;">
													<input type="hidden" name="action" value="delete">
													<input type="hidden" name="id" value="${v.nguoiDung.maND}">
													<button type="submit"
														class="btn btn-sm btn-outline-danger rounded-pill px-3">
														<i class="fa-solid fa-trash"></i>
													</button>
												</form>

											</div>
										</td>

									</tr>


									<div class="modal fade" id="detail${v.nguoiDung.maND}"
										tabindex="-1">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content rounded-4 shadow border-0">
												<div class="modal-body text-center p-4">

													<img src="${v.nguoiDung.anhDaiDien}"
														class="rounded-circle mb-3" width="90" height="90"
														style="object-fit: cover">

													<h4 class="fw-bold mb-1">${v.nguoiDung.hoTen}</h4>
													<p class="text-muted">@${v.nguoiDung.tenDangNhap}</p>

													<hr>

													<p>
														<strong>Email:</strong> ${v.nguoiDung.email}
													</p>

													<p class="text-muted">
														<c:choose>
															<c:when test="${not empty v.nguoiDung.tieuSu}">
																${v.nguoiDung.tieuSu}
															</c:when>
															<c:otherwise>
																Chưa cập nhật tiểu sử
															</c:otherwise>
														</c:choose>
													</p>

													<button class="btn btn-secondary rounded-pill px-4 mt-2"
														data-bs-dismiss="modal">Đóng</button>
												</div>
											</div>
										</div>
									</div>

								</c:forEach>
							</c:when>

							<c:otherwise>
								<tr>
									<td colspan="6" class="text-center py-5 text-muted"><i
										class="fa-solid fa-inbox fa-2x mb-2"></i><br> Không có dữ
										liệu</td>
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
