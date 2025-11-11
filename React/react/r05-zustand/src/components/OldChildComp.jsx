function OldChildComp({ onCount, onSetCount }) {
  return (
    <div>
      <p>자식컴포넌트(OldChildComp) count: {onCount}</p>
      <button onClick={() => onSetCount(onCount - 1)}>-</button>
      <button onClick={() => onSetCount(onCount + 1)}>+</button>
      <button onClick={() => onSetCount(0)}>0</button>
    </div>
  );
}
export default OldChildComp;
