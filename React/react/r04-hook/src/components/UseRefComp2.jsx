import { useEffect, useRef } from 'react';

const UseRefComp2 = () => {
  const pwdRef1 = useRef();
  const pwdRef2 = useRef();

  useEffect(() => {
    console.log('pwdRef1.current', pwdRef1.current); //<input name='pwd1'/> 태그
    console.log('pwdRef2.current', pwdRef2.current); //<input name='pwd2'/> 태그
    pwdRef1.current.focus();
  }, []);

  const checkPwd = () => {
    console.log(`pwdRef1.current.value: ${pwdRef1.current.value}`); //input태그 값
    console.log(`pwdRef2.current.value: ${pwdRef2.current.value}`); //input태그 값

    if (!pwdRef1.current.value || pwdRef1.current.value == '') {
      alert('비밀번호를 입력해주세요');
      pwdRef1.current.focus();
      return;
    }

    if (pwdRef1.current.value === pwdRef2.current.value) {
      alert('비밀번호 일치(O)');
    } else {
      alert('비밀번호 불일치(X)');
      pwdRef1.current.value = '';
      pwdRef2.current.value = '';
      pwdRef1.current.focus();
    }
  };

  return (
    <>
      <h2>useRef 사용</h2>
      <form>
        비밀번호1: <input type='text' ref={pwdRef1} name='pwd1' />
        <br />
        비밀번호2: <input type='text' ref={pwdRef2} name='pwd2' />
        <br />
        <button type='button' onClick={checkPwd}>
          비밀번호 일치확인
        </button>
      </form>
    </>
  );
};
export default UseRefComp2;
