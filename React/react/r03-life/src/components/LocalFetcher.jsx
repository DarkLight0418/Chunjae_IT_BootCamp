import { useEffect } from 'react';
import { useState } from 'react';

function LocalList(props) {
  //자식1
  const [datas, setDatas] = useState([]);
  useEffect(() => {
    fetch('./json/datas.json')
      .then((resObj) => {
        console.log('(1) resObj', resObj);
        const promise = resObj.json();
        console.log('(2) promise', promise);
        return promise;
      })
      .then((jsObjArr) => {
        console.log('(3)jsObjArr', jsObjArr);
        setDatas(jsObjArr);
      });
  }, []);

  const listTag = datas.map((jsObj) => {
    return (
      <li key={jsObj.id}>
        <a
          href={jsObj.id}
          data-id={jsObj.num}
          onClick={(event) => {
            event.preventDefault();
            //console.log(`event.target.dataset.id: ${event.target.dataset.id}`) //1 or 2 or 3
            props.onLinkClick(event.target.dataset.id);
          }}
        >
          {jsObj.id}
        </a>
      </li>
    );
  });

  return (
    <nav>
      <ul>{listTag}</ul>
    </nav>
  );
}

function LocalContent(props) {
  //자식2
  return (
    <>
      <h2>{props.data.name}</h2>
      <ul>
        <li>num: {props.data.num}</li>
        <li>id: {props.data.id}</li>
        <li>phone: {props.data.phone}</li>
        <li>desc: {props.data.desc}</li>
      </ul>
    </>
  );
}

function LocalFetcher() {
  //부모
  const [data, setData] = useState({});

  return (
    <>
      <h2>로컬 데이터 가져오기</h2>
      <LocalList
        onLinkClick={(num) => {
          fetch(`./json/data${num}.json`)
            .then((resObj) => {
              console.log('(4) resObj', resObj);
              return resObj.json();
            })
            .then((jsObj) => {
              console.log('(5) jsObj', jsObj);
              setData(jsObj);
            });
        }}
      />
      <br />

      <LocalContent data={data} />
    </>
  );
}
export default LocalFetcher;
