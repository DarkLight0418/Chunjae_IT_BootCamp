package gui.day01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//BorderLayout
public class B extends JFrame implements ActionListener {
    JButton bN, bS, bW, bE, bC;
    JPanel p;
    JButton bPC, bPE, bPW;

    void init(){
        bN = new JButton("북");
        bS = new JButton("남");
        bW = new JButton("서");
        bE = new JButton("동");
        bC = new JButton("중앙");

        p = new JPanel();
        p.setLayout(new BorderLayout());
        bPC = new JButton("패-센터");
        bPE = new JButton("패-동");
        bPW = new JButton("패-서");
        p.add(bPC);
        p.add(bPE, BorderLayout.EAST);
        p.add(bPW, BorderLayout.WEST);

        Container cp = getContentPane();
        cp.add(bN, BorderLayout.NORTH);
        cp.add(bS, BorderLayout.SOUTH);
        cp.add(bW, BorderLayout.WEST);
        cp.add(bE, BorderLayout.EAST);
        //cp.add(bC);
        cp.add(p);

        bN.addActionListener(this);
        bS.addActionListener(this);
        bW.addActionListener(this);
        bE.addActionListener(this);
        bPC.addActionListener(this);
        bPE.addActionListener(this);
        bPW.addActionListener(this);

        setUI();
    }
    void pln(String str){
        System.out.println(str);
    }
    public void actionPerformed(ActionEvent e){
        /*Object obj = e.getSource();
        if(obj == bN){
            pln("북");
        }else if(obj == bS){
            pln("남");
        }else if(obj == bW){
            pln("서");
        }else if(obj == bE){
            pln("동");
        }else if(obj == bPC){
            pln("패-센터");
        }else if(obj == bPE){
            pln("패-동");
        }else{ //obj == bPW
            pln("패-서");
        }*/
        Object obj = e.getSource();
        JButton b = (JButton)obj;
        pln(b.getText());
    }
    void setUI(){
        setTitle("BorderLayout Test Ver 1.0");
        setVisible(true);
        setSize(400, 400);
        setLocation(200, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        B b = new B();
        b.init();
    }
}
