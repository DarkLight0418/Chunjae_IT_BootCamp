function NavUpdate(props) {
  return (<nav>
    <a href="/" onClick={event => {
      event.preventDefault()
      props.onBack()
    }}>뒤로</a>
    <a href="/" onClick={event => {
      event.preventDefault()
      props.onList()
    }}>목록</a>
  </nav>)
}
export default NavUpdate