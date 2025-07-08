package day13.advanced;

import soo.P;

import java.util.Arrays;
import java.util.List;

public class G {
    List<String> list;
    void in(){
        list = Arrays.asList("봄", "여름", "가을", "겨울", "가을", "가흘");
    }
    void filter1() { //중복제거
        list.stream()
                .distinct()
                .forEach(item -> P.pln(item));
    }
    void filter2(){ //"가"로 시작되는 문자열만
        list.stream()
                .filter(item -> item.startsWith("가"))
                .forEach(item-> P.pln(item));
    }
    void filter3() { //중복제거 후, "가"로 시작되는 문자열만
        list.stream()
                .distinct()
                .filter(item -> item.startsWith("가"))
                .forEach(P::pln);
    }
    public static void main(String args[]){
        G g = new G();
        g.in();
        //g.filter1();
        //g.filter2();
        g.filter3();
    }
}
