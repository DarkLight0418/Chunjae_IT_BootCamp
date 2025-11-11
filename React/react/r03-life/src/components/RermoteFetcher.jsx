import { useState } from 'react';
import { useEffect } from 'react';

function RemoteList(props) {
  const [results, setResults] = useState([]);

  useEffect(() => {
    fetch('https://api.randomuser.me?results=5')
      .then((resObj) => {
        console.log('<1> resObj', resObj);
        return resObj.json();
      })
      .then((jsObj) => {
        console.log('<2> jsObj', jsObj);
        console.log('<2> jsObj.results', jsObj.results);
        setResults(jsObj.results);
      });
  }, []);

  const trTag = results.map((item) => {
    //내일 업그레이드!!
    return (
      <tr key={item.login.md5}>
        <td>
          <img src={item.picture.thumbnail} alt={item.login.username} />
        </td>
        <td>
          <a
            href='/'
            onClick={(event) => {
              event.preventDefault();
              props.onItem(item);
            }}
          >
            {item.login.username}
          </a>
        </td>
        <td>
          {item.name.title} {item.name.first} {item.name.last}
        </td>
        <td>{item.nat}</td>
        <td>{item.email}</td>
      </tr>
    );
  });

  return (
    <table border='1'>
      <thead>
        <tr>
          <th>증명사진</th>
          <th>아이디</th>
          <th>이름</th>
          <th>국가</th>
          <th>이메일</th>
        </tr>
      </thead>
      <tbody>{trTag}</tbody>
    </table>
  );
}
function RemoteFetcher() {
  return (
    <>
      <h2>원격 데이터 가져오기</h2>
      <RemoteList
        onItem={(item) => {
          console.log('<3> item', item);
          const info = `로그인ID: ${item.login.username} 비번:${item.login.password} 폰:${item.cell}`;
          alert(info);
        }}
      />
    </>
  );
}
export default RemoteFetcher;
