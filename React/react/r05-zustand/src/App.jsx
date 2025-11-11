import TopNavi from './components/TopNavi';
import Home from './components/Home';
import useCountStore from './components/UseCountStore';

import { BrowserRouter, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <>
      <TopNavi />
      <Routes>
        <Route path='/' element={useCountStore}></Route>
      </Routes>
    </>
  );
}

export default App;
