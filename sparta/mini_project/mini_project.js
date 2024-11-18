<script>
    const teamCards = document.querySelectorAll('.teamcard');
    const dropZone = document.getElementById('dropZone');

    // 드래그 시작할 때의 이벤트
    teamCards.forEach(card => {
        card.addEventListener('dragstart', (e) => {
            e.dataTransfer.setData('text/plain', card.dataset.url);
        });
    });

    // 드롭존에 대한 이벤트
    dropZone.addEventListener('dragover', (e) => {
        e.preventDefault(); // 드롭을 허용하기 위해 필요
    });

    dropZone.addEventListener('drop', (e) => {
        e.preventDefault();
        const url = e.dataTransfer.getData('text/plain');
        window.open(url, '_blank'); // 새 탭에서 URL 열기
    });
</script> 