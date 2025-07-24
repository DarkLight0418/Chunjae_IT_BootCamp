package gui.day01_self;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class A3Handler implements ActionListener {
    A3 a3;
    A3Handler(A3 a3) {
        this.a3 = a3;
    }

    public void actionPerformed(ActionEvent e) {
        a3.b.setText("이벤트 처리됨! by 제3클래스");
        a3.setTitle("내 앱");
    }
}

class A3_2Handler implements ActionListener {
    A3_2 a3;
    A3_2Handler(A3_2 a3) {
        this.a3 = a3;
    }
    
    public void actionPerformed(ActionEvent e) {
        a3.b.setText("이벤트 처리됨! by 제3클래스");
        a3.setTitle("내 앱");
    }
}