function NavList(props) {
  return (
    <nav>
      <a
        href='/'
        onClick={(event) => {
          event.preventDefault();
          props.onChangeMode();
        }}
      >
        글쓰기
      </a>
    </nav>
  );
}
export default NavList;
