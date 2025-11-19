import { Button, Stack, TextField, Snackbar } from "@mui/material"
import { useState } from "react"
import AddressList from "./AddressList"
import axios from 'axios';
import { REST_URL } from "../constants";

function Login(props) {
  const [user, setUser] = useState({ username: '', password: '' })
  const changeHandler = e => {
    setUser({ ...user, [e.target.name]: e.target.value })
    //console.log('user', user)
  }
  const loginHandler = () => {
    /*
    fetch(REST_URL+'login', { //방법1
      method: 'POST', 
      headers: {'Content-Type':'application/json'}, 
      body:JSON.stringify(user)
    })*/
    /*
    axios.post(REST_URL+'login', user, { //방법2-1
      headers: {'Content-Type':'application/json'} //기본값
    })
    */
    axios.post(REST_URL + 'login', user) //방법2-2
      .then(resObj => {
        //console.log('resObj', resObj)
        //const jwt = resObj.headers.get('Authorization') [//fetch방식일때 
        const jwt = resObj.headers['authorization'] //axios방식일때
        console.log(`jwt: ${jwt}`)
        if (jwt != null) {
          sessionStorage.setItem('jwt', jwt)
          props.onSetAuth(true)
        } else {
          setOpen(true)
        }
      })
      .catch(err => {
        console.error('로긴예외 err:', err)
        setOpen(true)
      })
  }

  const [open, setOpen] = useState(false)

  if (props.onAuth) {
    return <AddressList onUsername={user.username} />
  } else {
    return (<div>
      <Stack spacing={2} mt={2} alignItems="center">
        <TextField label="아이디" name="username" autoFocus onChange={changeHandler} />
        <TextField label="비밀번호" name="password" type="password" onChange={changeHandler} />
        <Button color="primary" variant="outlined" onClick={loginHandler}>로그인</Button>
      </Stack>
      <Snackbar open={open}
        autoHideDuration={3000}
        onClose={() => setOpen(false)}
        message="로그인 실패! 아이디나 비밀번호를 확인하세요" />
    </div>)
  }
}
export default Login