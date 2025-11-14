import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function List() {
  // boardData: 서버에서 받아온 게시글 배열을 저장하는 state
  const [boardData, setBoardData] = useState([]);

  // Spring Boot REST API 엔드포인트
  const requestUrl = "http://localhost:8080/reboards";

  /*
    useEffect:
    - 컴포넌트가 처음 렌더링될 때 서버에 GET 요청 전송
    - dependency 배열을 []로 두면 '마운트 시 1번만 실행'
  */
  useEffect(() => {
    fetch(requestUrl)
      /*
        fetch()의 첫 번째 then:
        - 서버의 Response 객체(resObj)를 실제 JSON 데이터로 변환
      */
      .then((resObj) => resObj.json())

      /*
        두 번째 then:
        - 변환된 JS 배열(jsObjArr)을 state에 저장
        - 화면이 다시 렌더링되면서 목록이 표시됨
      */
      .then((jsObjArr) => {
        console.log("<2> jsObjArr", jsObjArr);
        setBoardData(jsObjArr); // state 업데이트
      })
      .catch((err) => console.error("목록 조회 오류:", err));
  }, []); // 의존성 빈 배열 → 최초 한 번 실행

  /*
    listTag 생성:
    - boardData 배열을 map()으로 순회하며 <tr> 리스트 생성
    - 제목이 너무 길면 substring()으로 앞 20글자만 표시
    - 날짜(regdate)가 "2025-01-01T12:22:33" 형태일 경우 앞 10자리만 사용
  */
  const listTag = boardData.map((row) => {
    // 제목 20글자 제한 (리스트 UI 안정성)
    let subject = row.subject.substring(0, 20);

    // 날짜 yyyy-MM-dd 형식만 사용
    let regdate = row.regdate.substring(0, 10);

    return (
      <tr key={row.idx}>
        <td className="cen">{row.idx}</td>
        <td className="cen">{row.name}</td>

        {/* 제목 클릭 → /reboards/글번호 상세 페이지로 이동 */}
        <td>
          <Link to={`/reboards/${row.idx}`}>{subject}</Link>
        </td>

        <td className="cen">{regdate}</td>
      </tr>
    );
  });

  return (
    <>
      <header>
        <h2>게시판-목록</h2>
      </header>

      {/* 글쓰기 페이지 이동 버튼 */}
      <nav>
        <Link to="/write">글쓰기</Link>
      </nav>

      <article>
        <form>
          <table id="boardTable">
            <thead>
              <tr>
                <th>번호</th>
                <th>이름</th>
                <th>제목</th>
                <th>날짜</th>
              </tr>
            </thead>

            {/* map()으로 생성한 <tr> 출력 */}
            <tbody>{listTag}</tbody>
          </table>
        </form>
      </article>
    </>
  );
}

export default List;
