function StudyComp(props){
  return(<>
    <li><a href='/' onClick={() => props.onEvent1()}>학습</a></li>
  </>)
}
function HobbyComp(props){ //중요!
  const childMsg = '자식의 메세지'
  return(<>
    <li><a href='/test' onClick={event => {
      event.preventDefault()
      props.onEvent2(childMsg)
    }}>취미</a></li>
  </>)
}
function App(){
  const parentMsg = '부모의 메세지'
  return (<>
    <h2>React-이벤트</h2>
    <ol>
      <StudyComp onEvent1={()=>alert(parentMsg)}/>
      <HobbyComp onEvent2={(result) => alert(result)}/>
    </ol>
  </>)
}
export default App
