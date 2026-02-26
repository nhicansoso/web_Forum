<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<nav
	class="navbar navbar-light bg-white border-bottom shadow-sm sticky-top py-2">
	<div class="container d-flex justify-content-between align-items-center">
	
		<a class="navbar-brand fw-bold fs-3 text-dark" href="tcController">Instaclone </a>
		
		<form action="timkiemController" method="post" class="d-flex" style="width: 260px;">
			<div class="input-group input-group-sm">
				<input type="text" name="search" class="form-control"
					placeholder="Tìm kiếm người dùng...">
				<button class="btn btn-primary" type="submit">Tìm</button>
			</div>
		</form>

		<div class="d-flex align-items-center gap-3 fs-4">
			<a href="tcController" class="text-dark text-decoration-none"> 
				<i class="bi bi-house-door"></i>
			</a> 
			
			<button class="btn btn-primary btn-sm fw-bold" data-bs-toggle="modal"
				data-bs-target="#dangBaiModal">
				<i class="bi bi-plus-circle me-1"></i> Đăng bài
			</button>
			
			<div class="dropdown">
			    <a href="#" class="text-dark text-decoration-none position-relative"
			       id="dropdownThongBao" data-bs-toggle="dropdown"
			       aria-expanded="false"> 
			        <i class="bi bi-bell"></i>
			        <c:if test="${soThongBaoChuaDoc > 0}">
			            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
			                  style="font-size: 10px;"> ${soThongBaoChuaDoc}
			            </span>
			        </c:if>
			    </a>
			    
			    <ul class="dropdown-menu dropdown-menu-end shadow"
			        style="width: 320px; max-height: 400px; overflow-y: auto;"
			        aria-labelledby="dropdownThongBao">
			        <li class="dropdown-header fw-bold">Thông báo</li>
			
			        <c:forEach var="tb" items="${dsThongBao}">
			            <li class="dropdown-item d-flex align-items-center justify-content-between gap-2">
			                <a class="small text-decoration-none text-dark flex-grow-1 text-truncate"
			                   href="capnhattbController?action=read&maTB=${tb.maTB}">
			                    <b>${tb.tenNguoiGui}</b> đã theo dõi bạn
			                </a>

			                <a href="capnhattbController?action=delete&maTB=${tb.maTB}"
			                   class="text-danger flex-shrink-0"> 
			                   <i class="bi bi-x-lg"></i>
			                </a>
			            </li>
			        </c:forEach>
			
			        <c:if test="${empty dsThongBao}">
			            <li>
			                <span class="dropdown-item text-muted small"> Không có thông báo </span>
			            </li>
			        </c:if>
			    </ul>
			</div>


			<div class="dropdown">
				<a href="#"
					class="d-flex align-items-center text-dark text-decoration-none"
					id="dropdownUser" data-bs-toggle="dropdown" aria-expanded="false">
					<c:choose>
						<c:when test="${not empty ndSession.anhDaiDien}">
							<img src="${ndSession.anhDaiDien}"
								 width="30" height="30" class="rounded-circle border"
								 style="object-fit: cover">
						</c:when>
						<c:otherwise>
							<i class="bi bi-person-circle"></i>
						</c:otherwise>
					</c:choose>
				</a>
				<ul class="dropdown-menu dropdown-menu-end shadow"
					aria-labelledby="dropdownUser">
					<li>
						<a class="dropdown-item" href="tcnController?user=${nd.tenDangNhap}"> 
							<i class="bi bi-person me-2"></i>Trang cá nhân
						</a>
					</li>
					<li><hr class="dropdown-divider"></li>
					<li>
						<a class="dropdown-item text-danger" href="dangxuatController"> 
							<i class="bi bi-box-arrow-right me-2"></i>Đăng xuất
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</nav>

		<div class="modal fade" id="dangBaiModal" tabindex="-1"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title fw-bold">Tạo bài viết</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<form method="post" action="dangbaiController"
						enctype="multipart/form-data">
						<div class="modal-body">
							<div class="mb-3">
								<textarea class="form-control" name="noiDung" rows="4"
									placeholder="Bạn đang nghĩ gì?"></textarea>
							</div>
							<div class="mb-3">
								<input type="file" class="form-control" name="hinhAnh"
									accept="image/*">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Hủy</button>
							<button type="submit" class="btn btn-primary fw-bold">Đăng</button>
						</div>
					</form>
				</div>
			</div>
		</div>
