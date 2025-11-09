import StudyComp from "./components/StudyComp"
import HobbyComp from "./components/HobbyComp"

function App(){
  const parentMsg = '부모의 메세지'
  return (<>
    <h2>React-이벤트(컴포넌트 파일로 분리)</h2>
    <ol>
      <StudyComp onEvent1={()=>alert(parentMsg)}/>
      <HobbyComp onEvent2={(result) => alert(result)}/>
    </ol>
  </>)
}
export default App
