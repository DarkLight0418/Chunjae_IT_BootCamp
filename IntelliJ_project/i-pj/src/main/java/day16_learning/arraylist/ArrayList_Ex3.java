package day16_learning.arraylist;

import java.util.ArrayList;

public class ArrayList_Ex3 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("hello");
        list.add("hi");
        list.add("Hello");

        list.add(10);
        // list.add(new Integer(10));
        list.add(0, "bye");

        for(int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            System.out.println(o.toString());
        }

        list.remove(2);
        System.out.println("=====================");

        for(Object object : list) {
            System.out.println(object.toString());
        }
    }
}
