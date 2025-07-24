package gui.day01;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class G extends JFrame{
    JTable t;
    /*Object[] columnNames = {"번호", "이름", "날짜"};
    Object[][] rowData = {
            {10, "가길동", "2025-07-24"},
            {20, "나길동", "2025-07-24"},
            {30, "다길동", "2025-07-24"},
            {40, "라길동", "2025-07-24"},
            {50, "마길동", "2025-07-24"},
            {60, "바길동", "2025-07-24"},
            {70, "사길동", "2025-07-24"},
            {80, "아길동", "2025-07-24"},
            {90, "자길동", "2025-07-24"}
    };*/
    Vector<String> columnNames;
    Vector<Vector<Object>> rowData;
    G(){
        columnNames = new Vector<>();
        columnNames.add("번호");
        columnNames.add("이름");
        columnNames.add("날짜");

        rowData = new Vector<>();
        Vector<Object> v1 = new Vector<>();
        v1.add(10); v1.add("가길동"); v1.add("2025-07-24");
        Vector<Object> v2 = new Vector<>();
        v2.add(20); v2.add("나길동"); v2.add("2025-07-24");
        Vector<Object> v3 = new Vector<>();
        v3.add(30); v3.add("다길동"); v3.add("2025-07-24");
        Vector<Object> v4 = new Vector<>();
        v4.add(40); v4.add("라길동"); v4.add("2025-07-24");
        Vector<Object> v5 = new Vector<>();
        v5.add(50); v5.add("마길동"); v5.add("2025-07-24");
        Vector<Object> v6 = new Vector<>();
        v6.add(60); v6.add("바길동"); v6.add("2025-07-24");
        Vector<Object> v7 = new Vector<>();
        v7.add(70); v7.add("마길동"); v7.add("2025-07-24");

        rowData.add(v1);rowData.add(v2);rowData.add(v3);rowData.add(v4);
        rowData.add(v5);rowData.add(v6);rowData.add(v7);
    }
    void init() {
        Container cp = getContentPane();

        t = new JTable(rowData, columnNames);
        JScrollPane sp = new JScrollPane(t);
        cp.add(sp);

        setUI();
        test();
    }
    void setUI(){
        setTitle("JTable Ver 2.0");
        setVisible(true);
        setSize(500, 150);
        //setLocation(200, 100);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void test(){
        //(1) 데이터(get/set)
        t.setValueAt("서준서",0, 1);
        Object data = t.getValueAt(2, 1);//(value, row, col)
        pln("data: " + data);

        //(2) 갯수
        int rc = t.getRowCount();
        int cc = t.getColumnCount();
        pln("rc: " + rc + ", cc: " + cc);

        //(3) 컬럼이름
        String cn = t.getColumnName(1);
        pln("cn: " + cn);
    }
    void pln(String str){
        System.out.println(str);
    }
    public static void main(String[] args) {
        G g = new G();
        g.init();
    }
}

