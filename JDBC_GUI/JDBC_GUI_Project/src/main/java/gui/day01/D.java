package gui.day01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//CardLayout
public class D extends JFrame {
    String labels[] = {"도L", "레", "미", "파", "솔", "라", "시", "도H"};
    JButton bs[];
    Container cp;
    ActionListener listener;

    void init(){
        setLayout(new CardLayout());
        cp = getContentPane();
        bs = new JButton[labels.length];
        listener = new ActionHandler(this);
        undo();

        setUI();
    }
    void setUI(){
        setTitle("CardLayout Test Ver 1.0");
        setVisible(true);
        setSize(400, 300);
        setLocation(200, 100);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void undo(){
        for(int i=0; i<bs.length; i++){
            bs[i] = new JButton(labels[i]);
            bs[i].addActionListener(listener);
            cp.add(bs[i]);
        }
    }
    public static void main(String[] args) {
        D d = new D();
        d.init();
    }
}
class ActionHandler implements ActionListener {
    D d;
    ActionHandler(D d){
        this.d = d;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        JButton b = (JButton)obj;
        d.cp.remove(b);
        if(b == d.bs[d.bs.length-1]) d.undo();

        d.revalidate();
    }
}
