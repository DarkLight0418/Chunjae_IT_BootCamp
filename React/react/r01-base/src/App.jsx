function App(){
  return (<>
    <h2>React-기본</h2>
    <ol>
      <li>학습</li>
      <ul>
        <li>Java</li>
        <li>Maria</li>
        <li>Spring</li>
        <li>React</li>
      </ul>
      <li>취미</li>
      <ul>
        <li>수영</li>
        <li>헬쓰</li>
        <li>여행</li>
        <li>게임</li>
      </ul>
    </ol>
    <form>
      <select name="gubun">
        <option value="study">학습</option>
        <option value="hobby">취미</option>
      </select>
      <input type="text" name="title"/>
      <input type="submit" value="추가"/>
    </form>
  </>)
}
export default App
