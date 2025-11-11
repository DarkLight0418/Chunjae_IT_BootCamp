import { useState } from 'react';

function ChildComp({ onClick }) {
  console.log('자식(ChildComp) 렌더링됨');
  return <button onClick={onClick}>자식버튼</button>;
}

/*
  +버튼Click -> state(count)변경 -> 부모(UseCallbackComp1) 랜더링 
  -> handleClick 새로생성 -> 자식(ChildComp)의 props변경인식 -> 자식(ChildComp) 랜더링
*/
const UseCallbackComp1 = () => {
  const [count, setCount] = useState(0);

  const handleClick = () => {
    console.log('자식 버튼 클릭됨');
  };

  console.log('부모(UseCallbackComp1) 렌더링됨');

  return (
    <>
      <h2>useCallback을 사용하지 않은 예</h2>
      <p>
        count: {count}
        <button
          onClick={() => {
            setCount(count + 1);
          }}
        >
          +
        </button>
        <br />
        <ChildComp onClick={handleClick} />
      </p>
    </>
  );
};
export default UseCallbackComp1;
