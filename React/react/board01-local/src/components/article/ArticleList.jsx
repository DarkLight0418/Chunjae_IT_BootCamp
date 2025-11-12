function ArticleList(props) {
  const listTag = [];
  for (let i = 0; i < props.onBoardData.length; i++) {
    let row = props.onBoardData[i];
    listTag.push(
      <tr key={row.idx}>
        <td className='cen'>{row.idx}</td>
        <td className='cen'>{row.name}</td>
        <td>{row.subject}</td>
        <td className='cen'>{row.regdate}</td>
        <td className='cen'>
          <button
            onClick={() => {
              props.onChangeMode('delete');
              props.onSelectIdx(row.idx);
            }}
          >
            삭제
          </button>
        </td>
      </tr>
    );
  }
  return (
    <article>
      <table id='boardTable'>
        <thead>
          <tr>
            <th>번호</th>
            <th>이름</th>
            <th>제목</th>
            <th>날짜</th>
          </tr>
        </thead>
        <tbody>{listTag}</tbody>
      </table>
    </article>
  );
}
export default ArticleList;
