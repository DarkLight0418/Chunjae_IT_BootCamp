package com.example.t;

import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);

    void getTime() {
        int A = sc.nextInt();
        int B = sc.nextInt();
    
        int C = sc.nextInt();

        System.out.println("A + B + C = " + (A + B + C));
    }

    public static void main(String[] args) {
        Main m = new Main();    
        m.getTime();
    }
}