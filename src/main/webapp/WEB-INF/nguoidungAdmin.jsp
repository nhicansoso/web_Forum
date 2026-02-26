<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Modal.BaiDang" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Bài viết - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; font-family: 'Segoe UI', sans-serif; }
        
        /* Sidebar */
        .sidebar { width: 250px; height: 100vh; background: #212529; color: white; position: fixed; top: 0; left: 0; padding: 20px; }
        .nav-link { color: #adb5bd; padding: 10px; margin-bottom: 5px; display: flex; align-items: center; text-decoration: none; border-radius: 5px; transition: 0.3s; }
        .nav-link:hover, .nav-link.active { background: #0d6efd; color: white; }
        .nav-link i { margin-right: 10px; width: 20px; }
        
        /* Main Content */
        .main-content { margin-left: 250px; padding: 30px; }
        
        /* Post Styling */
        .post-img { width: 80px; height: 80px; object-fit: cover; border-radius: 8px; border: 1px solid #ddd; }
        .author-avatar { width: 30px; height: 30px; border-radius: 50%; object-fit: cover; margin-right: 8px; }
        
        /* Giới hạn nội dung hiển thị 2 dòng */
        .post-content { 
            max-width: 350px; 
            overflow: hidden; 
            text-overflow: ellipsis; 
            display: -webkit-box; 
            -webkit-line-clamp: 2; 
            -webkit-box-orient: vertical; 
            font-size: 0.9rem;
            color: #555;
        }
        
        /* Search Box */
        .search-box { position: relative; max-width: 300px; }
        .search-box i { position: absolute; left: 15px; top: 50%; transform: translateY(-50%); color: #6c757d; }
        .search-box input { padding-left: 35px; border-radius: 20px; }
    </style>
</head>
<body>

    <div class="sidebar d-flex flex-column">
        <h4 class="mb-4 text-center fw-bold text-white">ADMIN PANEL</h4>
        <nav class="flex-grow-1">
            <a href="tcAdminController" class="nav-link"><i class="fa-solid fa-chart-line"></i> Dashboard</a>
            <a href="nguoidungAdminController" class="nav-link"><i class="fa-solid fa-users"></i> Người dùng</a>
            
            <a href="baivietAdminController" class="nav-link active"><i class="fa-solid fa-newspaper"></i> Bài viết</a>
        </nav>
        <div class="mt-auto">
             <a href="LogoutServlet" class="nav-link text-danger"><i class="fa-solid fa-right-from-bracket"></i> Đăng xuất</a>
        </div>
    </div>

    <div class="main-content">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h3 class="fw-bold m-0">Danh sách Bài viết</h3>
            
            <form action="baivietAdminController" method="GET" class="search-box">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" name="key" class="form-control" placeholder="Tìm nội dung, user..." value="${key != null ? key : ''}">
            </form>
        </div>

        <div class="card p-3 border-0 shadow-sm">
            <table class="table table-hover align-middle">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Người đăng</th>
                        <th>Nội dung</th>
                        <th>Hình ảnh</th>
                        <th>Ngày đăng</th>
                        <th class="text-end">Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        ArrayList<BaiDang> list = (ArrayList<BaiDang>) request.getAttribute("dsBaiDang");
                        if (list != null && !list.isEmpty()) {
                            for (BaiDang p : list) {
                    %>
                    <tr>
                        <td class="text-muted small">#<%= p.getMaBaiDang() %></td>
                        
                        <td>
                            <div class="d-flex align-items-center">
                                <img src="<%= (p.getAnhDaiDien() != null && !p.getAnhDaiDien().isEmpty()) ? p.getAnhDaiDien() : "https://ui-avatars.com/api/?name=" + p.getTenDangNhap() %>" 
                                     class="author-avatar" alt="avt">
                                <span class="fw-bold text-dark text-nowrap"><%= p.getTenDangNhap() %></span>
                            </div>
                        </td>
                        
                        <td>
                            <div class="post-content" title="<%= p.getNoiDung() %>">
                                <%= p.getNoiDung() %>
                            </div>
                        </td>
                        
                        <td>
                            <% if (p.getHinhAnh() != null && !p.getHinhAnh().isEmpty()) { %>
                                <img src="<%= p.getHinhAnh() %>" class="post-img" alt="Post Img">
                            <% } else { %>
                                <span class="text-muted small fst-italic">Không có ảnh</span>
                            <% } %>
                        </td>
                        
                        <td class="text-secondary small text-nowrap"><%= p.getNgayDang() %></td>
                        
                        <td class="text-end">
                            <a href="#" onclick="confirmDelete(<%= p.getMaBaiDang() %>)" 
                               class="btn btn-sm btn-outline-danger" title="Xóa bài viết">
                                <i class="fa-solid fa-trash-can"></i>
                            </a>
                        </td>
                    </tr>
                    <%      }
                        } else {
                    %>
                        <tr><td colspan="6" class="text-center py-5 text-muted">Không tìm thấy bài viết nào.</td></tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <script>
        function confirmDelete(id) {
            if (confirm("CẢNH BÁO: Bạn có chắc muốn xóa bài viết #" + id + "?\n\nBình luận và Like của bài này cũng sẽ bị xóa vĩnh viễn.")) {
                window.location.href = "baivietAdminController?action=delete&id=" + id;
            }
        }
    </script>
</body>
</html>