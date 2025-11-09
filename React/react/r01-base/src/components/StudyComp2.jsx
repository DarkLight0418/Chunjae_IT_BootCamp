function StudyComp2(props){
  return(<>
    <li><a href='/' onClick={event => {
      event.preventDefault()
      props.onSetMode('study')
    }}>학습</a></li>
  </>)
}

export default StudyComp2