import { Route, Routes } from "react-router-dom"
import Content from "./components/board/Content"
import List from "./components/board/list"
import Write from "./components/board/write"

function App() {
  return (<>
    <Routes>
      <Route path='/' element={<List />} />
      <Route path='/list' element={<List />} />
      <Route path='/write' element={<Write />} />
      {/* <Route path='/reboards'>
        <Route path=':idx' element={<Content />}/>
      </Route> */}
      <Route path='/reboards/:idx' element={<Content />} />
      {/* <Route path='/reboards/:idx' element={<Update />}/> */}
    </Routes>
  </>)
}
export default App