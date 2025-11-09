function HobbyComp(props){ //중요!
  const childMsg = '자식의 메세지'
  return(<>
    <li><a href='/test' onClick={event => {
      event.preventDefault()
      props.onEvent2(childMsg)
    }}>취미</a></li>
  </>)
}

export default HobbyComp