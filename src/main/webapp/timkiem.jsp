<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Kết quả tìm kiếm</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
</head>

<body class="bg-light">

<%@ include file="navbar.jsp"%>

<div class="container my-4" style="max-width: 600px;">

    <div class="mb-3">
        <h5 class="fw-bold">
            Kết quả tìm kiếm cho:
            <span class="text-primary">"${keyword}"</span>
        </h5>
    </div>

    <c:if test="${empty dsKetQua}">
        <div class="alert alert-secondary text-center mb-0">
            Không tìm thấy người dùng
        </div>
    </c:if>

    <c:if test="${not empty dsKetQua}">
        <div class="card shadow-sm">

            <c:forEach var="nd" items="${dsKetQua}" varStatus="st">
                <div
                    class="d-flex align-items-center justify-content-between p-3
                    ${!st.last ? 'border-bottom' : ''}">

                    <a href="tcnController?username=${nd.tenDangNhap}"
                       class="d-flex align-items-center text-decoration-none text-dark">

                        <img
                            src="${nd.anhDaiDien}"
                            width="45" height="45"
                            class="rounded-circle border me-3">

                        <div>
                            <div class="fw-bold">${nd.tenDangNhap}</div>
                            <div class="text-muted small">${nd.hoTen}</div>
                        </div>
                    </a>

                    <a href="tcnController?username=${nd.tenDangNhap}"
                       class="btn btn-outline-primary btn-sm fw-bold">Xem
                    </a>

                </div>
            </c:forEach>

        </div>
    </c:if>

</div>

</body>
</html>
