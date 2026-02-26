<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng bài | MyGram</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="col-md-6 mx-auto">
        <div class="card shadow">
            <div class="card-header text-center">
                <h4>Đăng bài mới</h4>
            </div>

            <div class="card-body">
                <form action="dangbaiController" method="post" enctype="multipart/form-data">

                    <div class="mb-3">
                        <label class="form-label">Nội dung</label>
                        <textarea name="noidung" class="form-control" rows="4"></textarea>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Ảnh</label>
                        <input type="file" name="anh" class="form-control">
                    </div>

                    <button class="btn btn-primary w-100" type="submit">Đăng bài</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
