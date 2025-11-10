import { useEffect } from 'react';
import { useState } from 'react';

function BoxComp(props) {
  console.log('(1) step1');
  const [position, setPosition] = useState(props.initPosition);
  const boxStyle = {
    backgroundColor: 'red',
    position: 'relative',
    textAlign: 'center',
    color: 'white',
    width: '100px',
    height: '100px',
    margin: '10px',
    lineHeight: '100px',
    left: `${position}px`,
  };
  const [leftCount, setLeftCount] = useState(1);

  const moveLeft = () => {
    setPosition(position - 10);
    setLeftCount(leftCount + 1);
  };
  const moveRight = () => setPosition(position + 10);

  //첫 실행시에 (1)(2)(3) 수행되고, state변경시마다 (1)(2) 수행
  useEffect(
    function () {
      console.log('(3) step3');
      return () => {
        console.log('(4) step4');
      };
      //}) //1. 의존성배열생략( state변경시마다 (4)(3) 추가수행 )
      //}, []) //2. 의존성배열에 빈 배열지정( state변경되도 (4)(3) 수행안됨 )
    },
    [leftCount]
  ); //3. 의존성배열에 state변수 지정( leftCount 변경시마다 (4)(3) 추가수행 )

  console.log('(2) step2');
  return (
    <>
      <h4>컴포넌트 LifeCycle</h4>
      <div style={boxStyle}>{leftCount}</div>
      <input type='button' value='좌측이동' onClick={moveLeft} />
      <input type='button' value='우측이동' onClick={moveRight} />
    </>
  );
}

function LifeCycle() {
  return (
    <>
      <h2>React훅-useEffect</h2>
      <BoxComp initPosition={50} />
    </>
  );
}
export default LifeCycle;
