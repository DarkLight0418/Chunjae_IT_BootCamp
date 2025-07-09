package day13_stream;

import soo.P;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;

public class O {
    //int is[] =  {1, 2, 3, 4, 5}; //안됨
    //Integer is[] = {1, 2, 3, 4, 5}; //가능
    Integer is[] = {};
    List<Integer> list;
    O(){
        list = Arrays.asList(is);
    }
    void out1() throws NoSuchElementException {
        double avg =  list.stream().mapToInt(iObj -> iObj.intValue()) //Integer::intValue
                .average().getAsDouble();
        P.pln("방법1) 평균: " + avg);
    }
    void out2() {
        OptionalDouble optionalD = list.stream().mapToInt(Integer::intValue).average();
        if(optionalD.isPresent()){
            P.pln("방법2) 평균: " + optionalD.getAsDouble());
        }else{
            P.pln("방법2) 평균: 0.0");
        }
    }
    void out3(){
        double avg = list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        P.pln("방법3) 평균: " + avg);
    }
    void out4(){
        list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .ifPresent(avg-> P.pln("방법4) 평균: " + avg));
    }
    public static void main(String args[]){
        O o = new O();
        try {
            o.out1();
        }catch(NoSuchElementException ne){
            P.pln("방법1) 평균: 0.0");
        }
        o.out2();
        o.out3();
        o.out4();
    }
}
