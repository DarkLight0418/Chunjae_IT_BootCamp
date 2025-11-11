import { useRef, useState } from 'react';

const UseRefComp1 = () => {
  //(1) useState
  const [stateNum, setStateNum] = useState(0);
  const plusState = () => {
    setStateNum(stateNum + 1);
    console.log(`stateNum: ${stateNum}`);
  };

  //(2) useRef
  const refNum = useRef(0);
  const plusRef = () => {
    refNum.current = refNum.current + 1;
    console.log(`refNum ${refNum.current}`);
  };

  //(3) variable
  let varNum = 0;
  const plusVar = () => {
    console.log(`varNum: ${++varNum}`);
  };

  return (
    <>
      <h2>useRef 사용1</h2>
      <div>
        <p>stateNum: {stateNum}</p>{' '}
        {/* stateNum+ 버튼이 클릭시마다, 즉시 UI에 반영됨 */}
        <p>refNum: {refNum.current}</p>
        {/* stateNum+ 버튼이 클릭될 때, 비로서 변경값이 UI에 반영됨 */}
        <p>varNum: {varNum}</p> {/* UI에 반영되지않음 */}
        <button onClick={plusState}>stateNum+</button>
        <button onClick={plusRef}>refNum+</button>
        <button onClick={plusVar}>varNum+</button>
      </div>
    </>
  );
};

export default UseRefComp1;
