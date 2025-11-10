import { useLocation, useSearchParams } from 'react-router-dom';

const RouterHook = () => {
  const location = useLocation();
  const [searchParams, setSearchParams] = useSearchParams();
  const mode = searchParams.get('mode');

  const changeMode = () => {
    const nextMode = mode === 'list' ? 'view' : 'list';
    setSearchParams({ mode: nextMode });
  };
  return (
    <>
      <h2>라우터 훅</h2>
      <div>
        <ul>
          <li>URL: {location.pathname}</li>
          <li>쿼리스트링: {location.search}</li>
        </ul>
      </div>
      <button onClick={changeMode}>mode변경</button>
    </>
  );
};
export default RouterHook;
