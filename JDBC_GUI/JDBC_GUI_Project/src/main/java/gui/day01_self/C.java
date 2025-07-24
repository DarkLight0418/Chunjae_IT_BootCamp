package gui.day01_self;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class C extends JFrame {
    JButton bs[] = new JButton[6];

    void init() {
        setLayout(new GridLayout(2, 3));
        Container cp = getContentPane();
        ActionListener listener = (e -> handle(e));
        for(int i=0; i<bs.length; i++) {
            bs[i] = new JButton("버튼 " + i);
            bs[i].addActionListener(listener);
            cp.add(bs[i]);
        }
        setUI();
    }

    void pln(String str) {
        System.out.println(str);
    }

    void setUI() {  // 언제나 패널 등의 컴포넌트 다 배치하고 실행
        setTitle("GridLayout Test Ver 1.0");
        setVisible(true);
        setSize(400, 300);
        setLocation(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // EXIT_ON_CLOSE : 창 종료와 동시에 프로그램 종료
    }

    void handle(ActionEvent e) {
        Object obj = e.getSource();
        for (int i=0; i<bs.length; i++) {
            if (obj == bs[i]) {
                pln((i+1) + "번째");
            }
        }
    }

    public static void main(String[] args) {
        C c = new C();
        c.init();
    }
}
