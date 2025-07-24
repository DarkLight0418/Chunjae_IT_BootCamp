package gui.day01;

import javax.swing.*;
import java.awt.event.*;

public class A2 extends JFrame {
    JButton b;
    void init(){
        b = new JButton("자바의 버튼");
        /*ActionListener listener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                b.setText("이벤트 처리됨! by 무명내부클래스");
            }
        };
        b.addActionListener(listener);*/

        /*b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                b.setText("이벤트 처리됨! by 무명내부클래스");
            }
        });*/

        b.addActionListener(e -> b.setText("이벤트 처리됨! by 무명내부클래스"));
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
        A2 a = new A2();
        a.init();
    }
}

