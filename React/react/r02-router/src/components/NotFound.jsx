import { Link } from 'react-router-dom';

const NotFound = () => {
  return (
    <>
      <h2>Not Found (404)</h2>
      <p>
        없는 페이지 경로 입니다
        <br />
        <br />
        <Link to='/'>Home</Link>
      </p>
    </>
  );
};
export default NotFound;
