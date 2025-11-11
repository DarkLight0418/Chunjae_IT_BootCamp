import TopNavi from './components/TopNavi';
import { Routes, Route } from 'react-router-dom';
import UseRefComp1 from './components/UseRefComp1';
import UseRefComp2 from './components/UseRefComp2';
import UseCallbackComp1 from './components/UseCallbackComp1';
import UseCallbackComp2 from './components/UseCallbackComp2';

function App() {
  return (
    <>
      <TopNavi />
      <Routes>
        <Route path='/' element={<UseRefComp1 />} />
        <Route path='/use-ref1' element={<UseRefComp1 />} />
        <Route path='/use-ref2' element={<UseRefComp2 />} />
        <Route path='/use-callback1' element={<UseCallbackComp1 />} />
        <Route path='/use-callback2' element={<UseCallbackComp2 />} />
      </Routes>
    </>
  );
}
export default App;
