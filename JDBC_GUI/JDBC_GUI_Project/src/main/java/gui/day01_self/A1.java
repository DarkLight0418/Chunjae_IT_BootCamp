package gui.day01_self;

import javax.swing.*;
import java.awt.event.*;

public class A1 extends JFrame {
    JButton b;

    void init() {
        b = new JButton("자바 버튼");
        ActionListener listener = new A1Handler();
        b.addActionListener(listener);
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
        A1 a = new A1();
        a.init();
    }

    class A1Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            b.setText("이벤트 처리됨, by 유명 내부 클래스");
        }
    }
}
