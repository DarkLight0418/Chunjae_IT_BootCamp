package gui.day01;

import javax.swing.*;
import java.awt.event.*;

public class A1 extends JFrame {
    JButton b;
    void init(){
        b = new JButton("자바의 버튼");
        ActionListener listener = new A1Handler();
        b.addActionListener(listener);
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
        my_A1 a = new my_A1();
        a.init();
    }
    class A1Handler implements ActionListener {
        public void actionPerformed(ActionEvent e){
            //System.out.println("이벤트 발생 감지");
            b.setText("이벤트 처리됨! by 유명내부클래스");
        }
    }
}
