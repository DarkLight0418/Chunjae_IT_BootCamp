import useCountStore from './store';

function NewChildComp() {
  const { count, increase, decrease, reset } = useCountStore();

  return (
    <div>
      <p>자식컴포넌트(NewChildComp) count: {count}</p>
      <button onClick={decrease}>-</button>
      <button onClick={increase}>+</button>
      <button onClick={reset}>0</button>
    </div>
  );
}
export default NewChildComp;
