package gui.day01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//GridLayout
public class C extends JFrame {
    JButton bs[] = new JButton[6];

    void init(){
        setLayout(new GridLayout(2, 3));
        Container cp = getContentPane();
        ActionListener listener = (e-> handle(e));
        for(int i=0; i<bs.length; i++) {
            bs[i] = new JButton("버튼 "+i);
            bs[i].addActionListener(listener);
            cp.add(bs[i]);
        }

        setUI();
    }
    void pln(String str){
        System.out.println(str);
    }
    void handle(ActionEvent e){
        Object obj = e.getSource();
        if(obj == bs[0]){
            pln("첫번째");
        }else if(obj == bs[1]){
            pln("두번째");
        }else if(obj == bs[2]){
            pln("세번째");
        }else if(obj == bs[3]){
            pln("네번째");
        }else if(obj == bs[4]){
            pln("다섯번째");
        }else{
            pln("여섯번째");
        }
    }
    void setUI(){
        setTitle("GridLayout Test Ver 1.0");
        setVisible(true);
        setSize(400, 300);
        setLocation(200, 100);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        C c = new C();
        c.init();
    }
}
