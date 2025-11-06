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
	...
];*/

//[Q1] 91점을 받은 학생을 찾으시오 - filter
{
    let result1 = students.filter(student => student.score >= 90);
    console.log(`Q1 결과:`, result1);
}
//[Q2] 등록된 학생들 배열을 만드시오 - filter
{
    const result2 = students.filter(student => student.enrolled === true);
    console.log(`Q2 ${JSON.stringify(result2)}`)
}
//[Q3] 점수들의 배열을 만드시오 ( 결과: [46, 81, 91, 67, 89] ) - map
{
    const result3 = students.map(student => student.score);
    console.log(`Q3`, result3)
}
//[Q4] 50점보다 낮은 학생이 있는 지 체크하시오 
{
    const result4 = students.some(student => student.score < 50);
    console.log(`Q4`, result4)
}
//[Q5] 스코어의 평균을 출력하시오 
{
    const result5 = students.reduce(function(sum, student) {
        console.log("현재 누적값:", sum, "/현재 학생 점수:", student.score);
        return sum + student.score;
    }, 0);

    const average = result5 / students.length;
    console.log("평균 점수:", average);

    /*
    const result5 = students.reduce((sum, student) => sum + student.score, 0);
    const average = result5 / students.length;

    console.log("Q5 결과:", average);

    const avgScore = students.reduce((acc, s) => acc + s.score, 0) / students.length;
    console.log(`Q5 평균 점수: ${avgScore}`);

    */
}
//[Q6] 50점 이상 점수들의 문자열을 만드시오 ( 결과: '81,91,67,89' )
{
    
}
//[Q7] 점수들을 오름차순으로 정렬된 '문자열'을 만드시오 ( 결과: '46,67,81,89,91' )
{
    
}
//[Q8] 이름들을 내림차순 정렬한 '문자열'을 만드시오
{
    
}