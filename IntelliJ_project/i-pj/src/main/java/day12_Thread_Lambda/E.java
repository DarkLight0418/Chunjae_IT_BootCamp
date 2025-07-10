package day12_Thread_Lambda;//package day12;
//
//import soo.P;
//
//interface EI1 {
//    void m1();
//}
//
//class EI1Child implements EI1 {
//    public void m1() {
//        P.pln("m1() 구현1");
//    }
//}
//
//interface EI2 {
//    void m2(int i);
//}
//
//interface EI3 {
//    int m3(int i, int j);  // return 있음 -> 한 줄 람다식에 return 쓸 필요없음
//}
//
//class E {
//    void use1() {
//        EI1 ei1_1 = new EI1Child();  // 1
//        EI1 ei1_2 = new EI1() {  // 2
//            @Override
//            public void m1() {
//                P.pln("m1() 구현2");
//            }
//        };   // 이렇게도 생성
//
//        EI1 ei1_3 = () -> P.pln("m1() 구현3");  // 람다식
//
//        ei1_1.m1();
//        ei1_2.m1();
//        ei1_3.m1();
//    }
//
//    void use2() {
//        EI2 ei2 = (i) -> {  // i는 이 바디의 지역 변수
//            int r = i + 1;
//            P.pln("r: " + r);
//        };
//
//        ei2.m2(10);
//    }
//
//    void use3() {
////        EI3 ei3 = (i, j) -> {return i + j; };
//        EI3 ei3 = (i, j) -> i+j;
//        int sum = ei3.m3(3, 5);
//        P.pln("sum: " + sum);
//    }
//
//    void use4() {
////        EI3 ei3 = (i, j) -> {
////            return multiply(i, j);
////        }; // 중괄호 안에서는 return 써줘야 함
//        // -> multiply(i, j);
//
//        EI3 ei3 = (i, j)  -> new EOther().multiply2(i, j);
//        int mul = ei3.m3(1, 3);
//        P.pln("mul: " + mul);
//
//
//    }
//
//    int multiply(int i, int j) {
//        i = i + 1;
//        return i * j;
//    }
//
//    public static void main(String[] args) {
//        E e = new E();
////        e.use1();
////        e.use2();
////        e.use3();
//        e.use4();
//    }
//}
//
//class EOther {
//    int multiply2(int i, int j) {
//        i = i + 1;
//        return i * j;
//    }
//}

import soo.P;

interface EI1{
    void m1();
}
class EI1Child implements EI1{
    public void m1() {
        P.pln("m1() 구현1");
    }
}
interface EI2 {
    void m2(int i);
}
interface EI3{
    int m3(int i, int j);
}
class E {
    void use1() {
        EI1 ei1_1 = new EI1Child(); //1
        EI1 ei1_2 = new EI1() { //2
            public void m1() {
                P.pln("m1() 구현2");
            }
        };
        EI1 ei1_3 = () -> P.pln("m1() 구현3");//3

        ei1_1.m1();
        ei1_2.m1();
        ei1_3.m1();
    }
    void use2() {
        EI2 ei2 = i -> { //EI2 ei2 = P::pln;
            int r = i + 20;
            P.pln("m2()구현 r: " + r);
        };

        ei2.m2(10);
    }
    void use22() {
        //EI2 ei2 = i -> System.out.println(i);
        //EI2 ei2 = System.out::println; //최종확장(Oracle reference)
        //EI2 ei2 = P::pln; //최종확장(Oracle reference)

        //ei2.m2(20);
    }
    void use3() {
        //EI3 ei3 = (i, j) -> { return i+j; };
        EI3 ei3 = (i, j) -> i+j;

        int sum = ei3.m3(1,2);
        P.pln("sum: " + sum);
    }
    void use4() {
        //EI3 ei3 = (i, j) -> multiply(i, j);
        EI3 ei3 = (i, j) -> new EOther().multiply2(i, j);

        int mul = ei3.m3(1, 2);
        P.pln("mul: " + mul);
    }
    void use44() {
        //EI3 ei3 = (i, j) -> multiply(i, j);
        EI3 ei3 = this::multiply; //메쏘드참조연산자(Method Reference Operator)

        int mul = ei3.m3(1, 2);
        P.pln("mul: " + mul);
    }
    int multiply(int i, int j) {
        i = i + 1;
        return i*j;
    }
    public static void main(String args[]) {
        E e = new E();
        //e.use1();
        //e.use2();
        //e.use22();
        //e.use3();
        //e.use4();
        e.use44();
    }
}
class EOther {
    int multiply2(int i, int j) {
        i = i + 1;
        return i*j;
    }
}
