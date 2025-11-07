'use strict'

class Student {
    constructor(name, age, enrolled, score){
        this.name = name;
        this.age = age;
        this.enrolled = enrolled;
        this.score = score;
    }
}
const students= [
    new Student('홍길동',30, true, 46),
    new Student('이순신',29, false, 81),
    new Student('강감찬',31, true, 91),
    new Student('유관순',40, false, 67),
    new Student('세종대왕',19, true, 89), 
];
/*
const students= [
	{name:'홍길동', age:30, enrolled:true, score:46}, 
  {name:'이순신', age:29, enrolled:false, score:81},
  {name:'강감찬', age:31, enrolled:true, score:91},
  {name:'유관순', age:40, enrolled:false, score:67},
  {name:'세종대왕', age:19, enrolled:true, score:89},
];
*/
//[Q1] 91점을 받은 학생을 찾으시오
{
  //find<S extends T>(predicate: (value: T, index: number, obj: T[]) => value is S, thisArg?: any): S | undefined;
  const result = students.find(student => student.score===91)
  console.log(result.name)
  console.clear()
}

//[Q2] 등록된 학생들 배열을 만드시오
{
  //filter<S extends T>(predicate: (value: T, index: number, array: T[]) => value is S, thisArg?: any): S[];
  const resultArr = students.filter(student => student.enrolled)
  console.log(resultArr)
  console.table(resultArr)
  console.clear()
}

//[Q3] 점수들의 배열을 만드시오 ( 결과: [46, 81, 91, 67, 89] )
{
  //map<U>(callbackfn: (value: T, index: number, array: T[]) => U, thisArg?: any): U[];
  const resultArr = students.map(student => student.score)
  console.log(resultArr)
  console.clear()
}

//[Q4] 50점보다 낮은 학생이 있는 지 체크하시오 
{
  //some(predicate: (value: T, index: number, array: T[]) => unknown, thisArg?: any): boolean; //truthy, falsy
  const result = students.some(student => student.score<50)
  console.log(`50점 미만의 학생 존재여부: ${result}`)
  console.log(result? '존재':'비존재')
  console.clear()
}

//[Q5] 스코어의 평균을 출력하시오
{
  //forEach(callbackfn: (value: T, index: number, array: T[]) => void, thisArg?: any): void;
  //map<U>(callbackfn: (value: T, index: number, array: T[]) => U, thisArg?: any): U[];
  //(1) map과 forEach 이용
  const scores = students.map(student => student.score)
  let sum = 0
  scores.forEach(score => sum+=score)
  console.log(`(1)평균: ${sum/scores.length}`)
  console.clear()

  //reduce<U>(callbackfn: (previousValue: U, currentValue: T, currentIndex: number, array: T[]) => U, initialValue: U): U;
  //(2) reduce 이용
  const total = students.reduce((p, c) => p+c.score, 0)
  console.log(`(2)평균: ${total/scores.length}`)
  console.clear()
}

//[Q6] 50점 이상 점수들의 문자열을 만드시오 ( 결과: '81,91,67,89' )
{
  //filter<S extends T>(predicate: (value: T, index: number, array: T[]) => value is S, thisArg?: any): S[];
  
  //(1) 변수를 이용 (map 과 filter 이용)
  const scores = students.map(student => student.score)
  const scores2 = scores.filter(score => score>=50)
  console.log(scores2.join())
  console.clear()

  //(2) 변수를 이용X
  const result = students
    .map(student => student.score)
    .filter(score => score>=50)
    .join()

  console.log(result)
  console.clear()
}

//[Q7] 점수들을 오름차순으로 정렬된 '문자열'을 만드시오 ( 결과: '46,67,81,89,91' )
{
  //(1) 점수배열
  const scores = students.map(student => student.score)
  
  //(2) 정렬
  //sort(compareFn?: (a: T, b: T) => number): this;
  //const scores2 = scores.sort((a,b) => a-b) //오름차순 
  const scores2 = scores.sort((a,b) => b-a) //내림차순 
  const result1 = scores2.join() //문자열변환
  console.log('result1', result1) //배열 

  //(3) (1)과 (2)를 결합
  const result2 = students
    .map(student => student.score)
    .sort((a,b) => a-b)
    .join()
  console.log('result2', result2)
  console.clear()
}

//[Q8] 이름들을 내림차순 정렬한 '문자열'을 만드시오 ( ex: 홍길동|이순신|유관순|세종대왕|강감찬 )
{
  //(1) 이름들 배열 
  const names1 = students.map(student => student.name)
  console.log('names1', names1)

  //(2) 정렬 
  //const names2 = names1.sort() //오름차순  
  const names2 = names1.sort().reverse() //내림차순
  console.log('names2', names2)

  console.clear()

  //(3) (1)과 (2)를 결합
  const result = students
    .map(student => student.name)
    .sort()
    .reverse()
    .join('|')
  console.log('result', result)
}
