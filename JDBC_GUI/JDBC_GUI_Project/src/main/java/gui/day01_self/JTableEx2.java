package gui.day01_self;

import JAE.P;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class JTableEx2 extends JFrame implements ActionListener {

    private ArrayList<PersonDTO> list;
    private Vector<String> vector;
    private DefaultTableModel model;
    private JTable table;
    private JButton addBtn, delBtn;


    public JTableEx2() {
        list = new ArrayList<PersonDTO>();
        list.add(new PersonDTO("hong", "홍길동", "111", "010-1234-4568"));
        list.add(new PersonDTO("conan", "코난", "333", "010-2222-2222"));

        vector = new Vector<>();
        vector.addElement("아이디");
        vector.addElement("이름");
        vector.add("비밀번호");
        vector.add("핸드폰");

        model = new DefaultTableModel(vector, 0) {
            public boolean isCellEditable(int r, int c) {
                return (c != 0) ? true : false;
            }
        };
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        addBtn = new JButton("추가");
        delBtn = new JButton("삭제");

        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p.add(addBtn);
        p.add(delBtn);

        for(PersonDTO data : list) {
            Vector<String> v = new Vector<String>();
            v.add(data.getId());
            v.add(data.getName());
            v.add(data.getPwd());
            v.add(data.getTel());
            model.addRow(v);
        }

        Container c = getContentPane();
        c.add("Center", scroll);
        c.add("South", p);

        setBounds(700, 100, 500, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addBtn.addActionListener(this);
        delBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addBtn) {
            insert();
        } else if(e.getSource()==delBtn) {
            delete();
        }
    }

    public void insert() {
        boolean value = false;
        String id = JOptionPane.showInputDialog(this, "아이디를 입력하세요.");

        if(!id.equals("")) {
            for(int i=0; i<model.getRowCount();i++) {
                if(id.equals(model.getValueAt(i,0))) {
                    JOptionPane.showMessageDialog(this, "사용중인 아이디입니다.");
                    value=true;
                    break;
                }
            }
        }
        if(value=false) {
            String name = JOptionPane.showInputDialog(this, "이름을 입력하세요");
            String pwd = JOptionPane.showInputDialog(this, "비밀번호를 입력하세요");
            String tel = JOptionPane.showInputDialog(this, "전화번호를 입력하세요");

            Vector<String> v = new Vector<String>();
            v.add(id);
            v.add(name);
            v.add(pwd);
            v.add(tel);
            model.addRow(v);
        }
    }

    public void delete() {
        String name = JOptionPane.showInputDialog(this, "삭제할 사용자의 이름을 입력해주세요");
        for(int i=0; i<model.getRowCount(); i++) {
            if(name.equals(model.getValueAt(i,1))) {
                model.removeRow(i);
                i=-1;
            }
        }
    }


    public static void main(String[] args) {
        new JTableEx2();
    }
}

class PersonDTO {
    private String id;
    private String name;
    private String pwd;
    private String tel;

    public PersonDTO(String id, String name, String pwd, String tel) {
        this.name = name;
        this.id = id;
        this.pwd = pwd;
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }
    public String getTel() {
        return tel;
    }
}