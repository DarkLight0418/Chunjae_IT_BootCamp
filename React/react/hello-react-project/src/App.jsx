// React의 useState 훅을 불러옴 (상태 관리용)
import { useState } from 'react'

// React 로고 이미지 (로컬 자산)
import reactLogo from './assets/react.svg'

// Vite 로고 이미지 (루트 디렉토리에서 불러옴)
import viteLogo from '/vite.svg'

// 외부 CSS 파일 불러오기 (컴포넌트 전체 스타일 적용)
import './App.css'

// React 컴포넌트 정의
function App() {

  // [state 선언]
  // count: 현재 상태 값
  // setCount: 상태를 변경하는 함수
  // 초기값은 0
  const [count, setCount] = useState(0)

  // [컴포넌트 반환]
  // JSX 구조로 화면에 렌더링됨
  return (
    <>
      {/* 상단 로고 영역 */}
      <div>
        {/* Vite 로고 (링크 포함) */}
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>

        {/* React 로고 (링크 포함) */}
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>

      {/* 메인 제목 */}
      <h1>Vite + React</h1>

      {/* 중앙 버튼 영역 */}
      <div className="card">
        {/* 클릭 시 count 값을 1 증가시킴 */}
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>

        {/* HMR(Hot Module Replacement) 테스트용 안내 문구 */}
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>

      {/* 추가 안내문 (문서 링크) */}
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  )
}

// App 컴포넌트를 외부로 내보냄 (index.jsx에서 import하여 렌더링됨)
export default App
