<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>MyGram - Trang chủ</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/css/tc.css" rel="stylesheet">
</head>

<body class="bg-light">

	<%@ include file="navbar.jsp"%>

	<div class="container my-4">
		<div class="row">

			<div class="col-md-3">
				<div class="card p-3 mb-3">
					<div class="mb-3">
						<h6 class="fw-bold mb-0">
							Đang theo dõi <span class="text-muted fw-normal">${dsDangTheoDoi.size()}</span>
						</h6>
					</div>


					<c:forEach var="nd" items="${dsDangTheoDoi}">
						<div class="d-flex align-items-center mb-2">
							<a href="tcnController?username=${nd.tenDangNhap}"> <img
								src="${nd.anhDaiDien}" width="32" height="32"
								class="rounded-circle me-2 border"></a> <a
								href="tcnController?username=${nd.tenDangNhap}"
								class="text-dark text-decoration-none small fw-semibold">
								${nd.tenDangNhap} </a>
						</div>
					</c:forEach>

					<c:if test="${empty dsDangTheoDoi}">
						<div class="text-muted small">Bạn chưa theo dõi ai</div>
					</c:if>
				</div>
			</div>

			<div class="col-md-6">

				<div class="feed-tabs sticky-top" style="top: 70px; z-index: 10;">
					<div class="row g-0">
						<div class="col-6">
							<a href="?tab=foryou"
								class="feed-tab-item ${param.tab != 'following' ? 'active' : ''}">
								Dành cho bạn </a>
						</div>
						<div class="col-6">
							<a href="?tab=following"
								class="feed-tab-item ${param.tab == 'following' ? 'active' : ''}">
								Đang theo dõi </a>
						</div>
					</div>
				</div>

				<c:forEach var="p" items="${ds}">
					<div class="card post-card">

						<div class="post-header">
							<div class="d-flex align-items-center">
								<a href="tcnController?username=${p.tenDangNhap}"> <img
									src="${p.anhDaiDien}" width="40" height="40"
									class="rounded-circle me-3 border">
								</a> <a href="tcnController?username=${p.tenDangNhap}"
									class="fw-bold text-dark text-decoration-none">
									${p.tenDangNhap} </a>
							</div>

							<div>
								<c:if test="${p.maND != ndSession.maND}">
									<form action="theodoiController" method="post" class="d-inline">
										<input type="hidden" name="nguoiBiTheoDoi" value="${p.maND}">

										<button type="submit"
											class="btn btn-sm
								            ${p.trangThaiTheoDoi eq '1' ? 'btn-success' : 'btn-outline-primary'}">
											${p.trangThaiTheoDoi eq '1' ? 'Đang theo dõi' : 'Theo dõi'}</button>
									</form>
								</c:if>


								<c:if test="${p.maND == ndSession.maND}">
									<form method="post" action="xoabaivietController"
										class="d-inline">
										<input type="hidden" name="maBaiDang" value="${p.maBaiDang}">
										<input type="hidden" name="from" value="tc">
										<button class="btn btn-sm btn-outline-danger ms-2">Xóa</button>
									</form>
								</c:if>
							</div>
						</div>

						<div class="card-body pt-0 pb-0 px-3">
							<p class="mb-2">${p.noiDung}</p>
						</div>

						<c:if test="${not empty p.hinhAnh}">
							<img src="${p.hinhAnh}" class="post-image-minimal"
								onerror="this.style.display='none'">
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
													<input type="hidden" name="maBaiDang"
														value="${p.maBaiDang}">
													<div class="input-group">
														<input type="text" name="noiDung"
															class="form-control rounded-pill"
															placeholder="Viết bình luận..." required
															autocomplete="off">
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

				<c:if test="${empty ds}">
					<div class="text-center text-muted p-5">
						<i class="bi bi-inbox fs-1"></i>
						<p>Không có bài viết</p>
					</div>
				</c:if>

			</div>

			<div class="col-md-3">
				<div class="card p-3 mb-3">
					<a href="tcnController?username=${ndSession.tenDangNhap}"
						class="d-flex align-items-center text-decoration-none text-dark">
						<img src="${ndSession.anhDaiDien}" width="50" height="50"
						class="rounded-circle me-3 border">
						<div>
							<div class="fw-bold">${ndSession.tenDangNhap}</div>
							<div class="text-muted small">${ndSession.hoTen}</div>
						</div>
					</a>
				</div>

				<div class="card p-3">
					<div class="fw-bold mb-2">Gợi ý cho bạn</div>

					<c:forEach var="nd" items="${dsGoiY}">
						<div class="d-flex align-items-center mb-2">
							<a href="tcnController?username=${nd.tenDangNhap}"> <img
								src="${nd.anhDaiDien}" width="35" height="35"
								class="rounded-circle me-2 border">
							</a> <a href="tcnController?username=${nd.tenDangNhap}"
								class="text-dark text-decoration-none small fw-bold">
								${nd.tenDangNhap} </a>
						</div>
					</c:forEach>
				</div>
			</div>

		</div>
	</div>

	<script src="assets/js/like.js"></script>
	<script src="assets/js/binhluan.js"></script>

</body>
</html>