import { Route, Routes } from 'react-router-dom';
import TopNavi from './components/TopNavi';
import Home from './components/Home';
import CommonLayout from './components/CommonLayout';
import RouterIndex from './components/RouterIndex';
import RouterHook from './components/RouterHook';
import NotFound from './components/NotFound';

function App() {
  return (
    <>
      <TopNavi />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/intro' element={<CommonLayout />}>
          <Route index element={<RouterIndex />} />
          <Route path='router' element={<RouterHook />} />
        </Route>
        <Route path='*' element={<NotFound />} />
      </Routes>
    </>
  );
}
export default App;
