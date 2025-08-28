<%@ page contentType="text/html;charset=utf-8"%>

<script>
    const kind = "${kind}";
    const flag = ${flag};

    let message = "";

    if(kind === "insert") {
        message = flag ? "입력성공" : "입력실패";
    } else if(kind === "delete") {
        message = flag ? "삭제성공" : "삭제실패";
    } else if(kind === "update") {
    	message = flag ? "업데이트 성공" : "업데이트 실패"
    }

    alert(message);
    location.href = "board.do";
</script>