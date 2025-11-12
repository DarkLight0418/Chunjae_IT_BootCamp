function ArticleContent(props) {
  return (<article>
    <table id="boardTable">
      <colgroup>
        <col width="20%" />
        <col width="*" />
      </colgroup>
      <tbody>
        <tr>
          <th>번호</th>
          <td>{props.onSelectRow.idx}</td>
        </tr>
        <tr>
          <th>이름</th>
          <td>{props.onSelectRow.name}</td>
        </tr>
        <tr>
          <th>제목</th>
          <td>{props.onSelectRow.subject}</td>
        </tr>
        <tr>
          <th>내용</th>
          <td>{props.onSelectRow.content}</td>
        </tr>
        <tr>
          <th>내용(원본)</th>
          <td style={{ whiteSpace: 'pre-wrap' }}>
            {props.onSelectRow.content}
          </td>
        </tr>
        <tr>
          <th>날짜</th>
          <td>{props.onSelectRow.regdate}</td>
        </tr>
      </tbody>
    </table>
  </article>)
}
export default ArticleContent