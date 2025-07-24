package gui.day01;

import javax.swing.*;
import java.awt.event.*;

public class A3 extends JFrame {
    JButton b;
    void init(){
        b = new JButton("자바의 버튼");
        ActionListener handler = new A3Handler(this);
        b.addActionListener(handler);
        add(b);
        setUI();
    }
    void setUI(){
        setTitle("GUI Test Ver 1.0");
        setVisible(true);
        setSize(300, 200);
        setLocation(200, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        A3 a = new A3();
        a.init();
    }
}


