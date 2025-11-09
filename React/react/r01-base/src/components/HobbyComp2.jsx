function HobbyComp2({onSetMode}){ 
  return(<>
    <li><a href='/' onClick={event => {
      event.preventDefault()
      onSetMode('hobby')
    }}>취미</a></li>
  </>)
}

export default HobbyComp2