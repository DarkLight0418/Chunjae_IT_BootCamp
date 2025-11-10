import { useState } from 'react';
import { useEffect } from 'react';

function RemoteList() {
  const [results, setResults] = useState([]);

  useEffect(() => {
    fetch('https://api.randomuser.me?results=10')
      .then((resObj) => {
        console.log('(1) resObj', resObj);
        return resObj.json();
      })
      .then((jsObj) => {
        console.log('(2) jsObj', jsObj);
        console.log('(2) jsObj.results', jsObj.results);
        setResults(jsObj.results);
      });
  }, []);

  const trTag = results.map((item) => {
    //내일 업그레이드!!
    return (
      <tr>
        <td>
          <img src={item.picture.thumbnail} alt={item.login.username} />
        </td>
        <td>{item.login.username}</td>
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
      <RemoteList />
    </>
  );
}
export default RemoteFetcher;
