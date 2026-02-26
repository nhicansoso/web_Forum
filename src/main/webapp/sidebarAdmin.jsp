<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="assets/css/admin-style.css" rel="stylesheet">

<div class="sidebar">
    <a href="#" class="brand mb-4 d-flex align-items-center gap-2 text-white text-decoration-none">
        <span class="fw-bold fs-5">Trang Admin</span>
    </a>

    <nav class="flex-grow-1 d-flex flex-column">
        <a href="tcAdminController"
           class="nav-link ${activeMenu == 'dashboard' ? 'active' : ''}">
            <i class="fa-solid fa-house"></i> Trang chủ	
        </a>
        <a href="nguoidungAdminController"
           class="nav-link ${activeMenu == 'users' ? 'active' : ''}">
            <i class="fa-solid fa-users"></i> Quản lý người dùng
        </a>
        <a href="baivietAdminController"
           class="nav-link ${activeMenu == 'posts' ? 'active' : ''}">
            <i class="fa-solid fa-newspaper"></i> Quản lý bài viết
        </a>
        <a href="binhluanAdminController"
           class="nav-link ${activeMenu == 'comments' ? 'active' : ''}">
            <i class="fa-solid fa-comment"></i> Quản lý bình luận
        </a>
    </nav>

    <div class="mt-auto">
        <a href="dangxuatController" class="nav-link logout-link text-danger">
            <i class="fa-solid fa-right-from-bracket"></i> Đăng xuất
        </a>
    </div>
</div>
