package gui.day01;

import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

class H extends JFrame implements ActionListener {
    JButton A,B,C,D,E;
    JPanel p;

    AudioInputStream[] audioInputStreams = new AudioInputStream[5];
    Clip[] clips = new Clip[5];
    void init(){
        String[] notes = {"do.wav","le.wav","mi.wav","fa.wav","sol.wav"};

        URL url = getClass().getResource("resource/do.wav");
        System.out.println("url: " + url);
        if (url != null) {
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
                System.out.println("소리 재생됨");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("파일 못찾음");
        }

        /*
        for(int i= 0; i < notes.length; i++){
            try {
                URL url = getClass().getResource("resource/"+notes[i]);
                System.out.println(url);

                if(url != null) {
                    AudioFormat format = audioInputStreams[i].getFormat();
                    DataLine.Info info = new DataLine.Info(Clip.class, format);
                    clips[i] = (Clip) AudioSystem.getLine(info);
                    clips[i].open(audioInputStreams[i]);
                }else{
                    System.out.println("파일 못찾음 "+ notes[i]);
                }


            }catch (Exception e){
                e.printStackTrace();
            }
        }
*/
        A = new JButton("도");
        B = new JButton("레");
        C = new JButton("미");
        D = new JButton("파");
        E = new JButton("솔");

        p = new JPanel();
        p.setLayout(null);
        A.setBounds(20, 20, 50, 110);
        B.setBounds(60, 20, 50, 110);
        C.setBounds(100, 20, 50, 110);
        D.setBounds(140, 20, 50, 110);
        E.setBounds(180, 20, 50, 110);

        p.add(A);
        p.add(B);
        p.add(C);
        p.add(D);
        p.add(E);

        Container cp = getContentPane();
        cp.add(p);

        A.addActionListener(this);
        B.addActionListener(this);
        C.addActionListener(this);
        D.addActionListener(this);
        E.addActionListener(this);

        setUI();
    }
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        JButton b = (JButton)obj;
        System.out.println(b.getText());

        if(b == A){
            playSound(0);
        } else if (b == B) {
            playSound(1);
        }else if (b == C) {
            playSound(2);
        }else if (b == D) {
            playSound(3);
        }else if (b == E) {
            playSound(4);
        }
    }
    void playSound(int index){
        if(clips[index] != null){
            clips[index].setFramePosition(0);
            clips[index].start();
        }
    }
    void setUI(){
        setTitle("피아노");
        setVisible(true);
        setSize(700,500);
        setLocation(200,200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        H h = new H();
        h.init();
    }
}