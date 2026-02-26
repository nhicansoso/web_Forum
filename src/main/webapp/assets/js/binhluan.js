
	document.querySelectorAll('.comment-form').forEach(form => {
        form.addEventListener('submit', async function (e) {
            e.preventDefault();

            const maBaiDang = this.dataset.id;
            const input = this.querySelector('input[name="noiDung"]');
            const noiDung = input.value.trim();
            if (!noiDung) return;

            try {
                const res = await fetch('binhluanController', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                    body: new URLSearchParams({maBaiDang, noiDung})
                });

                const data = await res.json();
                if (!data.success) return alert(data.msg || 'Thêm bình luận thất bại!');

                input.value = '';

                const div = document.createElement('div');
                div.className = 'mb-2';
                div.innerHTML = `<a href="tcnController?username=${data.tenNguoiDung}" class="fw-bold text-dark text-decoration-none">
                                    ${data.tenNguoiDung}
                                 </a>: ${data.noiDung}`;

                document.getElementById(`comment-list-${maBaiDang}`).appendChild(div);

            } catch (err) {
                console.error('Lỗi bình luận:', err);
                alert('Không thể gửi bình luận, vui lòng thử lại!');
            }
        });
    });

