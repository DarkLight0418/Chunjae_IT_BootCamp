<%@ page contentType="text/html;charset=utf-8"%>

<script>
    const kind = "${kind}";
    const flag = ${flag};

    let message = "";

    if(kind === "insert") {
        message = flag ? "입력성공(Model2)" : "입력실패(Model2)";
    } else {
        message = flag ? "삭제성공(Model2)" : "삭제실패(Model2)";
    }

    alert(message);
    location.href = "addr.do";
</script>