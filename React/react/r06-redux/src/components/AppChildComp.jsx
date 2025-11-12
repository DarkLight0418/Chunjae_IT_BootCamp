import { useDispatch, useSelector } from 'react-redux';
import { decrease, increase, reset } from '../store/countSlice';

//(3) state사용 컴포넌트
function AppChildComp() {
  const dispatch = useDispatch();
  const value = useSelector((state) => state.count.value);

  return (
    <div>
      <p>자식컴포넌트(AppChildComp) count: {value}</p>
      <button onClick={() => dispatch(decrease())}>-</button>
      <button onClick={() => dispatch(increase())}>+</button>
      <button onClick={() => dispatch(reset())}>0</button>
    </div>
  );
}
export default AppChildComp;
