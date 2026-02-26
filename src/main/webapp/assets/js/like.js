document.querySelectorAll('.like-btn').forEach(btn => {
    btn.onclick = async () => { 
        const maBaiDang = btn.dataset.id;

        try {
            const res = await fetch('likeController', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: new URLSearchParams({ 'maBaiDang': maBaiDang }) 
            });

            const data = await res.json();

            const icon = btn.querySelector('i');
            const count = btn.querySelector('.like-count');

            if (data.liked) {
                btn.classList.add('text-danger');
                btn.classList.remove('text-dark');
                icon.className = 'bi bi-heart-fill me-1';
            } else {
                btn.classList.remove('text-danger');
                btn.classList.add('text-dark');
                icon.className = 'bi bi-heart me-1';
            }

            count.innerText = data.soLike;

        } catch (error) {
            console.error('Lỗi thả tim:', error);
        }
    };
});