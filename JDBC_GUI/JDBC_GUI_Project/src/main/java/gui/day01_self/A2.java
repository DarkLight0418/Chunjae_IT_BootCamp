package gui.day01_self;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

class A2_2 extends JFrame {
    JButton b;
    void init() {
        b = new JButton("자바 버튼");
        /*ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.setText("이벤트 처리됨! by 무명 내부 클래스");
            }
        };
        b.addActionListener(listener);*/

        /*
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.setText("이벤트 처리됨! by 무명내부클래스");
            }
        }); */

        b.addActionListener(e -> b.setText("이벤트 처리됨! by 무명내부클래스"));
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
        A2_2 a2 = new A2_2();
        a2.init();
    }
}
