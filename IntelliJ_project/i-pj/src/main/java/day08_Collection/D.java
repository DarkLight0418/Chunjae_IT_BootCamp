package day08_Collection;

import soo.P;

public class D { //배열에 Enhanced Loop 적용
    String[] items = {"봄", "여름", "가을", "겨울"}; //선언+생성+초기화

    void out() {
//		for(int i=0; i<items.length; i++) {
//			P.pln("items["+i+"]: " + items[i]);
//		}

        for(String item: items) {
            P.pln("item: " + item);
        }
    }
    public static void main(String[] args) {
        new D().out();
    }
}

