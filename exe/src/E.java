package gui.day01;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class E extends JFrame {
    JLabel laImg;

    void init(){
        URL url = getClass().getResource("imgs/move.gif");
        Icon icon = new ImageIcon(url);
        laImg = new JLabel(icon);
        Container cp = getContentPane();
        cp.add(laImg);

        setUI();
    }
    void setUI(){
        setTitle("ImageTest Ver 1.0");
        setVisible(true);
        pack();
        //setLocation(200, 100);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        E e = new E();
        e.init();
    }
}
