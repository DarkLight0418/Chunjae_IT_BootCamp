import { useState } from 'react';
import OldChildComp from './OldChildComp';

function OldComp() {
  const [count, setCount] = useState(0);

  return (
    <>
      <h2>부모컴포넌트(OldComp) count: {count}</h2>
      <br />
      <OldChildComp onCount={count} onSetCount={setCount} />
    </>
  );
}
export default OldComp;
