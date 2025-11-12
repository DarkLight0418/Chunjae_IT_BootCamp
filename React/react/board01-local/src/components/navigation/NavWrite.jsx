function NavWrite(props) {
  return (
    <nav>
      <a
        href='/'
        onClick={(event) => {
          event.preventDefault();
          props.onChangeMode();
        }}
      >
        목록
      </a>
    </nav>
  );
}
export default NavWrite;
