function NavContent(props) {
  return (
    <nav>
      <a
        href='/'
        onClick={(event) => {
          event.preventDefault();
          props.onChangeMode('list');
        }}
      >
        목록
      </a>
      &nbsp;
      <a
        href='/'
        onClick={(event) => {
          event.preventDefault();
          props.onChangeMode('update');
        }}
      >
        수정
      </a>
      &nbsp;
      <a
        href='/'
        onClick={(event) => {
          event.preventDefault();
          if (window.confirm('삭제할까요?')) {
            props.onChangeMode('delete');
          }
        }}
      >
        삭제
      </a>
    </nav>
  );
}
export default NavContent;
