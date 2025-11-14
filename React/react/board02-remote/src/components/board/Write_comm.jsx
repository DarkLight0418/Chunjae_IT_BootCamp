import { Link, useNavigate } from "react-router-dom";

function Write() {
  // useNavigate: 특정 URL로 프로그래밍 방식 이동을 지원하는 React Router Hook
  const navigate = useNavigate();

  // 게시글 등록 요청을 보낼 서버 API 엔드포인트
  const requestUrl = "http://localhost:8080/reboards";

  return (
    <>
      <header>
        <h2>게시판-작성</h2>
      </header>

      {/* 목록 페이지로 이동하는 링크 */}
      <nav>
        <Link to="/list">목록</Link>
      </nav>

      <article>
        {/* 
          onSubmit 이벤트:
          - form의 submit 동작을 가로채고(fetch) 직접 처리
          - e.preventDefault()를 통해 페이지 리로드를 막고 SPA 방식으로 동작
        */}
        <form
          onSubmit={(e) => {
            e.preventDefault(); // 기본 form submit(새로고침) 방지

            // 서버에 POST 요청 (JSON 형태로 데이터 전달)
            fetch(requestUrl, {
              method: "POST", // 등록 요청
              headers: {
                "Content-Type": "application/json", // JSON 바디 전송 명시
              },
              body: JSON.stringify({
                // form 요소의 name 값과 매칭
                name: e.target.writer.value,
                subject: e.target.title.value,
                content: e.target.content.value,
              }),
            })
              .then((resObj) => resObj.json()) // 응답을 JSON으로 파싱
              .then((jsObj) => {
                console.log("Write jsObj:", jsObj); // 서버가 반환한 생성된 데이터 확인

                // 등록 완료 후 목록 페이지로 이동
                navigate("/list");
              })
              .catch((err) => console.error("등록 중 오류 발생:", err));
          }}
        >
          <table id="boardTable">
            <tbody>
              <tr>
                <th>이름</th>
                {/* writer라는 name 값 → fetch body.writer로 전달됨 */}
                <td>
                  <input type="text" name="writer" />
                </td>
              </tr>

              <tr>
                <th>제목</th>
                {/* title → fetch body.subject로 전달됨 */}
                <td>
                  <input type="text" name="title" />
                </td>
              </tr>

              <tr>
                <th>내용</th>
                {/* content → fetch body.content로 전달됨 */}
                <td>
                  {/* textarea는 self-closing 하면 안 되는 게 일반적이나 React에서는 허용됨 */}
                  <textarea name="content" cols="22" rows="3" />
                </td>
              </tr>
            </tbody>
          </table>

          {/* 실제 제출 버튼 */}
          <input type="submit" value="전송" />
        </form>
      </article>
    </>
  );
}

export default Write;
