import { useState } from "react"
import NavList from "./components/navigation/NavList"
import ArticleList from "./components/article/ArticleList"
import NavWrite from "./components/navigation/NavWrite"
import ArticleWrite from "./components/article/ArticleWrite"
import NavContent from "./components/navigation/NavContent"
import ArticleContent from "./components/article/ArticleContent"
import NavUpdate from "./components/navigation/NavUpdate"
import ArticleUpdate from "./components/article/ArticleUpdate"
import './App.css'

function Header(props) {
  return (<header>
    <h2>{props.onTitle}</h2>
  </header>)
}

function App() {
  //(1) 화면구성변수 
  let titleVar
  let navComp, articleComp

  //(2) 데이터들 
  const [boardData, setBoardData] = useState([
    { idx: 1, name: '고창우', subject: '제목1', content: '내용1\n 임다', regdate: '2025-11-12' },
    { idx: 2, name: '박수빈', subject: '제목2', content: '내용2', regdate: '2025-11-12' },
    { idx: 3, name: '양진석', subject: '제목3', content: '내용3', regdate: '2025-11-12' },
  ])

  //(3) state정의 
  const [mode, setMode] = useState('list')

  const [nextIdx, setNextIdx] = useState(4) //for write 
  const [idx, setIdx] = useState(null) //for content

  //(4) list -> write -> content
  //console.log('mode:', mode)
  if (mode === 'list') {
    titleVar = '게시판-목록'
    navComp = <NavList onChangeMode={() => setMode('write')} />
    articleComp = <ArticleList onBoardData={boardData} onChangeMode={(pidx) => {
      if (typeof pidx === 'string') {
        // 삭제 요청
        setMode(pidx);
      } else {
        // 상세보기 요청
        setMode('content')
        setIdx(pidx)
      }
    }}
      onSelectIdx={(pidx) => setIdx(pidx)}
    />
  } else if (mode === 'write') {
    titleVar = '게시판-작성'
    navComp = <NavWrite onChangeMode={() => setMode('list')} />
    articleComp = <ArticleWrite onWriteAction={(w, t, c) => {
      let copyBoardData = [...boardData]
      let nowDate = new Date().toISOString().slice(0, 10)
      let addBoardData = { idx: nextIdx, name: w, subject: t, content: c, regdate: nowDate }
      copyBoardData.push(addBoardData)

      setBoardData(copyBoardData)
      setNextIdx(nextIdx + 1)
      setMode('list')
    }} />
  } else if (mode === 'content') {
    titleVar = '게시판-상세'
    navComp = <NavContent onChangeMode={pmode => {
      setMode(pmode)
    }} />

    let selectRow
    for (let i = 0; i < boardData.length; i++) {
      if (idx === boardData[i].idx) {
        selectRow = boardData[i]
      }
    }
    articleComp = <ArticleContent onSelectRow={selectRow} />
  } else if (mode === 'delete') {
    let newBoardData = []
    for (let i = 0; i < boardData.length; i++) {
      if (idx !== boardData[i].idx) {
        newBoardData.push(boardData[i])
      }
    }
    setBoardData(newBoardData)
    setMode('list')
  } else if (mode === 'update') {
    titleVar = '게시판-수정'
    navComp = <NavUpdate
      onBack={() => {
        setMode('content')
      }}
      onList={() => {
        setMode('list')
      }}
    />

    let selectRow
    for (let i = 0; i < boardData.length; i++) {
      if (idx === boardData[i].idx) {
        selectRow = boardData[i]
      }
    }
    articleComp
      = <ArticleUpdate onSelectRow={selectRow} onUpdateAction={(w, t, c) => {
        //console.log('w', w, 't', t, 'c', c)
        let updateBoardObj = { idx: idx, name: w, subject: t, content: c, regdate: selectRow.regdate }
        let copyBoardData = [...boardData]
        for (let i = 0; i < copyBoardData.length; i++) {
          if (copyBoardData[i].idx === idx) {
            copyBoardData[i] = updateBoardObj
            break
          }
        }
        setBoardData(copyBoardData)
        setMode('content')
      }} />
  }

  return (<>
    <Header onTitle={titleVar} />
    {navComp}
    {articleComp}
  </>)
}
export default App