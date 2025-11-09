function StudyComp(){
  return(<>
    <li>학습</li>
      <ul>
        <li>Java</li>
        <li>Maria</li>
        <li>Spring</li>
        <li>React</li>
      </ul>
  </>)
}
function HobbyComp(){
  return(<>
    <li>취미</li>
      <ul>
        <li>수영</li>
        <li>헬쓰</li>
        <li>여행</li>
        <li>게임</li>
      </ul>
  </>)
}
//let FormComp = function(){ //방법1
let FormComp = () => { //방법2
  return(<>
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
function App(){
  return (<>
    <h2>React-컴포넌트</h2>
    <ol>
      <StudyComp/>
      <HobbyComp/>
    </ol>
    <FormComp/>
  </>)
}
export default App
