<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="d-flex justify-content-center align-items-center vh-100">
    <div class="card shadow-sm" style="width: 100%; max-width: 350px;">
        <div class="card-body p-4">
            <h3 class="card-title text-center mb-4">Admin Login</h3>

            <form action="dangnhapAdminController" method="post">
                <div class="mb-3">
                    <label for="taiKhoan" class="form-label">Tài khoản</label>
                    <input type="text" name="taiKhoan" id="taiKhoan" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label for="matKhau" class="form-label">Mật khẩu</label>
                    <input type="password" name="matKhau" id="matKhau" class="form-control" required>
                </div>

                <button type="submit" class="btn btn-primary w-100">
					Đăng nhập
                </button>
            </form>

            <c:if test="${not empty error}">
                <div class="alert alert-danger mt-3 text-center" role="alert">
                    ${error}
                </div>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
