/*
function ChildComp(props){
  return(<>
    <p>
      {props.p1}, {props.p2}, {props.p3}, {props.p4}
    </p>
  </>)
}
*/
/*
function ChildComp({p1,p2,p3,p4}){ //구조분해할당
  return(<>
    <p>
      {p1}, {p2}, {p3}, {p4}
    </p>
  </>)
}
*/
function ChildComp({p1,p3}){ //(선택적)구조분해할당
  return(<>
    <p>
      {p1}, {p3}
    </p>
  </>)
}

function App(){
  const ver = 3
  return (<>
    <h2>React-프롭스2 (props사용)</h2>
    <ChildComp p1={'Java'} p2={'Maria'+(10+1)} p3={ver} p4={`CSS${ver}`}/>
  </>)
}
export default App
