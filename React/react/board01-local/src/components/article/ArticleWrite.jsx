function ArticleWrite(props) {
  return (
    <article>
      <form
        onSubmit={(event) => {
          event.preventDefault();
          let writer = event.target.writer.value;
          let title = event.target.title.value;
          let content = event.target.content.value;
          props.onWriteAction(writer, title, content);
        }}
      >
        <table id='boardTable'>
          <tbody>
            <tr>
              <th>이름</th>
              <td>
                <input type='text' name='writer' />
              </td>
            </tr>
            <tr>
              <th>제목</th>
              <td>
                <input type='text' name='title' />
              </td>
            </tr>
            <tr>
              <th>내용</th>
              <td>
                <textarea name='content' col='22' rows='3' />
              </td>
            </tr>
          </tbody>
        </table>
        <input type='submit' value='전송' />
      </form>
    </article>
  );
}
export default ArticleWrite;
