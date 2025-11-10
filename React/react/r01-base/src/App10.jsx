import { useState } from "react"

const ContentComp = ({data}) => {
  return (<>
    <ol>
      <li>학습</li>
      <ul>
        {data.study.map((item, i) => <li key={i}>{item}</li>)}
      </ul>
      <li>취미</li>
      <ul>
        {data.hobby.map((item, i) => <li key={i}>{item}</li>)}
      </ul>
    </ol>
  </>)
}

function App(){
  const [items, setItems] = useState({
    study: ['Java', 'Maria', 'Spring', 'React'], 
    hobby: ['수영', '헬쓰', '여행', '게임']
  })
  const addStudy = () => { //이렇게 하면 안됨!
    items.study.push('AWS')
    setItems(items)
  }
  const addHobby = () => { //이렇게 해야 함!
    const newHobby = [...items.hobby, '여행']
    const newItems = {...items,  hobby:newHobby}
    setItems(newItems)
  }

  return(<>
    <h2>React-아이템추가</h2>
    <ContentComp data={items}/>
    <button type='button' onClick={addStudy}>학습아이템 추가</button>
    <button type='button' onClick={addHobby}>취미아이템 추가</button>
  </>) 
}
export default App