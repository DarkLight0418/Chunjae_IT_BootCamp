import { Outlet } from 'react-router-dom';

const CommonLayout = () => {
  return (
    <>
      <header style={{ background: 'lightgray', padding: '10px' }}>
        CommonLayout header 내용
      </header>
      <article>
        <Outlet />
      </article>
      <footer style={{ background: 'lightgray', padding: '10px' }}>
        CommonLayout footer 내용
      </footer>
    </>
  );
};
export default CommonLayout;
