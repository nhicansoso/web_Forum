<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quản lý Bình luận</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	rel="stylesheet">
</head>
<body>

	<c:set var="activeMenu" value="comments" />
	<%@ include file="sidebarAdmin.jsp"%>

	<div class="main-content">

		<div class="d-flex justify-content-between align-items-center mb-4">
			<h3 class="fw-bold m-0 text-dark">Quản lý Bình luận</h3>

			<form action="binhluanAdminController" method="get"
				class="search-box d-flex">
				<input type="text" name="key" class="form-control"
					placeholder="  Tìm người bình luận..."
					value="${key != null ? key : ''}" style="width: 250px">
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
							<th style="width: 140px">Người bình luận</th>
							<th class="text-center" style="width: 140px">Mã bài viết</th>
							<th style="width: 140px">Ngày bình luận</th>
							<th class="text-center" style="width: 160px">Xem nội dung</th>
							<th class="text-center pe-4" style="width: 120px">Hành động</th>
						</tr>
					</thead>

					<tbody>
						<c:choose>
							<c:when test="${not empty dsBinhLuan}">
								<c:forEach var="bl" items="${dsBinhLuan}">
									<tr>
										<td class="ps-4 fw-bold text-muted">#${bl.maBL}</td>

										<td>
											<div class="fw-bold">@${bl.tenNguoiDung}</div>
										</td>

										<td class="text-center"><span
											class="badge bg-secondary bg-opacity-10 text-secondary px-3 py-2 rounded-pill">
												#${bl.maBaiDang} </span></td>

										<td class="text-muted small">${bl.ngayBL}</td>

										<td class="text-center">
											<button
												class="btn btn-sm btn-outline-primary rounded-pill px-3"
												data-bs-toggle="modal" data-bs-target="#noiDung${bl.maBL}">
												<i class="fa-solid fa-eye"></i>
											</button>
										</td>

										<td class="text-center pe-4">
											<form action="binhluanAdminController" method="post"
												style="display: inline;">
												<input type="hidden" name="action" value="delete"> <input
													type="hidden" name="id" value="${bl.maBL}">

												<button type="submit"
													class="btn btn-sm btn-outline-danger rounded-pill px-3">
													<i class="fa-solid fa-trash"></i>
												</button>
											</form>
										</td>
									</tr>

									<div class="modal fade" id="noiDung${bl.maBL}" tabindex="-1">
										<div class="modal-dialog modal-dialog-centered modal-lg">
											<div class="modal-content rounded-4 shadow border-0">
												<div class="modal-body p-4">
													<h5 class="fw-bold mb-3">Nội dung bình luận</h5>

													<div class="bg-light p-3 rounded">${bl.noiDung}</div>

													<div class="text-center mt-4">
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
									<td colspan="6" class="text-center py-5 text-muted"><i
										class="fa-solid fa-inbox fa-2x mb-2"></i><br> Không có
										bình luận</td>
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
