package gui.day01_self;

import gui.day01_self.A3Handler;

import javax.swing.*;
import java.awt.event.ActionListener;

public class A3 extends JFrame {
    JButton b;
    void init() {
        b = new JButton("자바 버튼");
        ActionListener handler = new A3Handler(this);
        b.addActionListener(handler);
        add(b);
        setUI();
    }

    void setUI() {
        setTitle("GUI Test Ver 1.0");
        setVisible(true);
        setSize(300, 200);
        setLocation(200, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        A3 a3 = new A3();
        a3.init();
    }
}

class A3_2 extends JFrame {
    JButton b;
    void init() {
        b = new JButton("자바의 버튼");
        ActionListener handler = new A3_2Handler(this);
        b.addActionListener(handler);
        add(b);
        setUI();
    }

    void setUI() {
        setTitle("GUI Test Ver 1.0");
        setVisible(true);
        setSize(300, 200);
        setLocation(200, 100);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        A3_2 a3 = new A3_2();
        a3.init();
    }
}
