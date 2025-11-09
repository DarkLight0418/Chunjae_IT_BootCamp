import StudyComp from "./components/StudyComp2"
import HobbyComp from "./components/HobbyComp2"
import { useState } from "react"

function App(){
  const [mode, setMode] = useState('both') //단계1
  //console.log(`mode: ${mode}`)

  const handleMode = (m)=> {setMode(m)} //단계3

  let contents = '' //단계2
  if(mode === 'study'){
    contents = <StudyComp onSetMode={(m)=> {setMode(m)}}/>
  }else if(mode === 'hobby'){
    contents = <HobbyComp onSetMode={handleMode}/>
  }else {
    contents = <>
      <StudyComp onSetMode={(m)=> {setMode(m)}}/>
      <HobbyComp onSetMode={handleMode}/>
    </>
  }

  return (<>
    <h2><a href='/' onClick={event =>{
      event.preventDefault()
      setMode('both')
    }}>React-state변경</a></h2>
    <ol>
      {contents}
    </ol>
  </>)
}
export default App
