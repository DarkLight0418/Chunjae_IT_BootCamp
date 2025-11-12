import { useState } from "react"

function ArticleUpdate(props) {
  const [name, setName] = useState(props.onSelectRow.name)
  const [subject, setSubject] = useState(props.onSelectRow.subject)
  const [content, setContent] = useState(props.onSelectRow.content)

  return (<article>
    <form onSubmit={event => {
      event.preventDefault()
      props.onUpdateAction(name, subject, content)
    }}>
      <table id="boardTable">
        <tbody>
          <tr>
            <th>이름</th>
            <td><input name="writer" value={name} onChange={event => {
              setName(event.target.value)
            }} /></td>
          </tr>
          <tr>
            <th>제목</th>
            <td><input name="title" value={subject} onChange={event => {
              setSubject(event.target.value)
            }} /></td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea name="content" col="22" rows="3" value={content}
                onChange={event => {
                  setContent(event.target.value)
                }} />
            </td>
          </tr>
        </tbody>
      </table>
      <input type="submit" value="수정" />
    </form>
  </article>)
}
export default ArticleUpdate