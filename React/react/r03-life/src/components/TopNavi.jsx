import { NavLink } from 'react-router-dom';

const TopNavi = () => {
  return (
    <nav>
      <NavLink to='/'>생명주기</NavLink>&nbsp;
      <NavLink to='/local'>내부호출</NavLink>&nbsp;
      <NavLink to='/remote'>원격호출</NavLink>
    </nav>
  );
};
export default TopNavi;
