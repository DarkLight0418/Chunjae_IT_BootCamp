package gui.day01;

import java.awt.event.*;
import javax.swing.*;

public class A3Handler implements ActionListener {
    A3 a3;
    public A3Handler(A3 a3){
        this.a3 = a3;
    }
    public void actionPerformed(ActionEvent e){
        //Object obj = e.getSource();
        //JButton b = (JButton)obj;
        a3.b.setText("이벤트 처리됨! by 제3클래스");
        a3.setTitle("효상이의 앱");
    }
}
