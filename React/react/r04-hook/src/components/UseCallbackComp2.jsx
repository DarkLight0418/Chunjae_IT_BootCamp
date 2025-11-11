import React, { useCallback, useState } from 'react';

//함수의 Memoization: 같은 입력값으로 다시 함수를 호출하면,이미 계산된 결과를 꺼내 사용해서 시간 절약
const ChildComp = React.memo(({ onClick }) => {
  console.log('자식(ChildComp) 렌더링됨');
  return <button onClick={onClick}>자식버튼</button>;
});

/*
  +버튼Click -> state(count)변경 -> '부모(UseCallbackComp1) 랜더링' 
  ->그러나, handleClick 최초1회만 생성 -> 자식(ChildComp)의 props변경인식X -> '자식(ChildComp) 랜더링'되지 않음
*/
const UseCallbackComp2 = () => {
  const [count, setCount] = useState(0);

  const handleClick = useCallback(() => {
    console.log('자식 버튼 클릭됨');
  }, []); //의존성배열이 비어있으면, 최초1회만 수행

  console.log('부모(UseCallbackComp1) 렌더링됨');

  return (
    <>
      <h2>useCallback을 사용한 예</h2>
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
export default UseCallbackComp2;
