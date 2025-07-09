package day08.m;

import java.util.*;

import soo.P;

public class T2 {
	Random r = new Random();
	int total = 13;
	
	void showRan() {
		int i = r.nextInt(total); //i: 0 ~ (total-1)
		P.pln("i: " + i);
	}
	public static void main(String args[]) {
		new T2().showRan();
	}
}
