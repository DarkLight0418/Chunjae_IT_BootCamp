import { configureStore, createSlice } from '@reduxjs/toolkit';
import { Provider, useDispatch, useSelector } from 'react-redux';

function App() {
  //const [count, setCount] = useState(0)

  //(1) slice 정의
  const countsSlice = createSlice({
    name: 'count',
    initialState: { value: 0 },
    reducers: {
      increase: (state) => {
        state.value += 1;
      },
      decrease: (state) => {
        state.value -= 1;
      },
      reset: (state) => {
        state.value = 0;
      },
    },
  });

  //(2) store 생성
  const { actions, reducer } = countsSlice;
  const { increase, decrease, reset } = actions;
  const store = configureStore({ reducer: { count: reducer } });

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

  //(4) Provider로 감싸기
  return (
    <>
      <h2>리덕스-부모컴포넌트(App)</h2>
      <Provider store={store}>
        <AppChildComp />
      </Provider>
    </>
  );
}

export default App;
