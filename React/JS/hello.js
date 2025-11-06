'use strict' //엄격 모드

console.log('안녕 JS^^')

let a //선언
a = 3 //초기화
console.log('a', a)
console.log(`a ${a}`)

let div = document.getElementById('divId')
console.log(`div.nodeName ${div.nodeName}`)
div.innerHTML = "<font color='red'>테스트</font>"