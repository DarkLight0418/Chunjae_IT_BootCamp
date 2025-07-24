package gui.day01_self;

import javax.swing.*;

public class A2 extends JFrame {
    JButton b;
    void init() {
        b = new JButton("자바 버튼");
        b.addActionListener(e -> b.setText("이벤트 처리됨, by. 무명 내부 클래스"));
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
        A2 a2 = new A2();
        a2.init();
    }
}
