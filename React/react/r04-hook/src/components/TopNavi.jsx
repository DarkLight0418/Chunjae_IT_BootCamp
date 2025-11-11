// function TopNavi() {}

import { NavLink } from 'react-router-dom';

const TopNavi = () => {
  return (
    <nav>
      <NavLink to='/use-ref1'>useRef1</NavLink>&nbsp;&nbsp;&nbsp;
      <NavLink to='/use-ref2'>useRef2</NavLink>&nbsp;&nbsp;&nbsp;
      <NavLink to='/use-callback1'>UseCallbackComp1</NavLink>&nbsp;&nbsp;&nbsp;
      <NavLink to='/use-callback2'>UseCallbackComp2</NavLink>
    </nav>
  );
};

export default TopNavi;
