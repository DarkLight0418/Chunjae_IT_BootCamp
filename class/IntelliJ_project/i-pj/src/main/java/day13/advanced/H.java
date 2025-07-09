package day13.advanced;

import soo.P;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class H { //IntStream, DoubleStream, 제너릭적용스트림
    int iss[] = {1, 2, 3};

    void out1() {  // 스트림 기본 사용 방식...
        IntStream is = Arrays.stream(iss);
        is.forEach(i -> P.pln(i));
    }
    void out2() { // 스트림 형변환
        IntStream is = Arrays.stream(iss);
        DoubleStream ds = is.asDoubleStream();
        ds.forEach(d -> P.pln(""+d));
    }
    void out3() {  // 스트림 박싱
        IntStream is = Arrays.stream(iss);
        Stream<Integer> sBox = is.boxed();
        sBox.forEach(iObj -> P.pln("value : " + iObj.intValue()));
    }

    public static void main(String[] args) {
        H h = new H();
        //h.out1();
        //h.out2();
        h.out3();
    }
}
