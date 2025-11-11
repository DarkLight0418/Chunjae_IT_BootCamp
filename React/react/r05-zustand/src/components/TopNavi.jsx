import { NavLink } from 'react-router-dom';

function TopNavi() {
  return (
    <nav>
      <NavLink to='/'>홈</NavLink>&nbsp;&nbsp;
      <NavLink to='/old'>기존</NavLink>&nbsp;&nbsp;
      <NavLink to='/new'>추슈탄트</NavLink>
    </nav>
  );
}
export default TopNavi;
