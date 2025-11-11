import NewChildComp from './NewChildComp';
import useCountStore from './store';

function NewComp() {
  //const {count, increase, decrease, reset} = useCountStore()
  const { count } = useCountStore();

  return (
    <>
      <h2>부모컴포넌트(NewComp) count: {count}</h2>
      <br />
      <NewChildComp />
    </>
  );
}
export default NewComp;
