import { useEffect, useState } from "react"
import { Link } from "react-router-dom"

function List() {
  const [boardData, setBoardData] = useState([])

  const requestUrl = 'http://localhost:8080/reboards'
  useEffect(() => {
    fetch(requestUrl) /*
    .then(resObj => {
      console.log('<1> resObj', resObj)
      return resObj.json()
    })*/
      .then(resObj => resObj.json()) //상동
      .then(jsObjArr => {
        console.log('<2> jsObjArr', jsObjArr)
        setBoardData(jsObjArr)
      })
  }, [])

  const listTag = boardData.map(row => {
    let subject = row.subject.substring(0, 20)
    let regdate = row.regdate.substring(0, 10)

    return (
      <tr key={row.idx}>
        <td className="cen">{row.idx}</td>
        <td className="cen">{row.name}</td>
        <td><Link to={`/content/${row.idx}`}>{subject}</Link></td>
        <td className="cen">{regdate}</td>
      </tr>
    )
  })

  return (<>
    <header>
      <h2>게시판-목록</h2>
    </header>
    <nav>
      <Link to='/write'>글쓰기</Link>
    </nav>
    <article>
      <form>
        <select>
          <option value="subject">제목</option>
          <option value="name">이름</option>
          <option value="content">내용</option>
        </select>
        <input
          style={{ width: '380px' }}
          placeholder="검색어 입력" />
        <button type="submit">검색</button>
      </form>

      <table id="boardTable">
        <thead>
          <tr>
            <th>번호</th>
            <th>이름</th>
            <th>제목</th>
            <th>날짜</th>
          </tr>
        </thead>
        <tbody>
          {listTag}
        </tbody>
      </table>
    </article>
  </>)
}
export default List