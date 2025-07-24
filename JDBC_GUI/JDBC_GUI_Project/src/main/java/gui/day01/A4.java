package gui.day01;

import javax.swing.*;
import java.awt.event.*;

public class A4 extends JFrame implements ActionListener {
    JButton b;
    void init(){
        b = new JButton("자바의 버튼");
        b.addActionListener(this);
        add(b);
        setUI();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        b.setText("이벤트 처리됨! by 자신의클래스");
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
        A4 a = new A4();
        a.init();
    }
}



