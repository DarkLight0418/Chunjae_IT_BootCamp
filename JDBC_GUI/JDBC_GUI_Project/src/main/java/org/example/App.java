package org.example;

import JAE.P;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class App extends JFrame implements ActionListener {

    //Query 연결 객체 선언
    String maria = "org.mariadb.jdbc.Driver";
    String mariaUrl = "jdbc:mariadb://127.0.0.1:3305/java_schema";
    Connection con;
    Statement stmt;

    //북쪽 컴포넌트 선언
    JComboBox<String> combo;
    JTextField search;

    //센터 컴포넌트 선언
    JTable table;
    DefaultTableModel model;

    //남쪽 컴포넌트 선언
    JTextField tfDeptno, tfDname, tfLoc;
    JButton btnAdd, btnUpdate, btnDelete;
    String sql = "select * from DEPT";

    App() {
        //Query 연결 TEST
        try {
            Class.forName(maria);
            con = DriverManager.getConnection(mariaUrl, "scott", "tiger");
            stmt = con.createStatement();
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException ce) {
            System.out.println("드라이버 로딩 실패");
        } catch (SQLException se) {
            System.out.println("DB 연결 실패");
        }
        

        //북쪽컴포넌트 생성
        combo = new JComboBox<>(new String[]{"DEPTNO", "DNAME", "LOC"}); // 북쪽 콤보박스
        search = new JTextField(); //북쪽 search 박스를 JTextField로 선언

        //북쪽 레이아웃 생성
        JPanel northPanel = new JPanel(new GridLayout(1, 2)); // JPanel의 형태를 GridLayout으로 설정
        northPanel.add(combo);
        northPanel.add(search);// northpanel에 콤보박스와 search 텍스트필드 생성


        //센터컴포넌트 생성
        String[] colNames = {"DEPTNO", "DNAME", "LOC"};
        Object[][] data = {{},{},{},{}}; // 테이블 데이터 test

        //센터 테이블 레이아웃 생성
        model = new DefaultTableModel(data, colNames);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        //남쪽 컴포넌트 생성
        tfDeptno = new JTextField();
        tfDname = new JTextField();
        tfLoc = new JTextField();
        btnAdd = new JButton("추가");
        btnUpdate = new JButton("수정");
        btnDelete = new JButton("삭제");
        //컴포넌트의 행위를 지정해주기위해 btn에 addActionListener 선언
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);

        //남쪽 레이아웃 생성
        JPanel southPanel = new JPanel(new GridLayout(2, 3)); // 남쪽 Panel에 2,3 GridLayout설정
        southPanel.add(tfDeptno);
        southPanel.add(tfDname);
        southPanel.add(tfLoc);
        southPanel.add(btnAdd);
        southPanel.add(btnUpdate);
        southPanel.add(btnDelete);

        //자동 감지기능을 위한 search 컴포넌트에 DocumentListener선언
        search.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                filterQuery();
            }
            public void removeUpdate(DocumentEvent e) {
                filterQuery();
            }
            public void changedUpdate(DocumentEvent e) {
                filterQuery();
            }
        });


        //Container 객체를 생성해서 붙힐 위치를 정함
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(northPanel, BorderLayout.NORTH);
        cp.add(scrollPane, BorderLayout.CENTER);
        cp.add(southPanel, BorderLayout.SOUTH);

        //Swing 프로그램 설정
        setTitle("JTable Test3");
        setSize(400, 300);
        setLocation(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //Main프로그램 시작점
    public void start(){
        this.initSetting();
    }

    //버튼객체로부터 행위 정의
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAdd){
            insert();
        }else if(e.getSource() == btnUpdate){
            update();
        }else if(e.getSource() == btnDelete){
            delete();
}
    }

    //초기 테이블 셋팅
    public void initSetting(){
        ResultSet rs = null;
        //Query Test
        try{
            rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cc = rsmd.getColumnCount();
            for(int i=1; i<=cc; i++) {
                String cn = rsmd.getColumnName(i);
                P.p(cn+"\t");
            }

            String selectsql = "Select * from dept";
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("DEPTNO"),
                        rs.getString("DNAME"),
                        rs.getString("LOC")
                });
            }
        }catch(SQLException se){
        }finally {
            try{
                rs.close();
            }catch(SQLException se){}
        }

    }

    public void insert() {
        try {
            //sql의 형태를 preparedStatement로 선언
            String sql = "INSERT INTO DEPT VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            //Textfield로부터 데이터를 getText로 받아와서 데이터 정제후 변수에 대입
            int deptno = Integer.parseInt(tfDeptno.getText());
            String dname = tfDname.getText();
            String loc = tfLoc.getText();

            //대입된 변수를 통해 pstmt객체에 대입
            pstmt.setInt(1, deptno);
            pstmt.setString(2, dname);
            pstmt.setString(3, loc);

            //대입한 쿼리실행
            pstmt.executeUpdate();
            //테이블 다시 Select
            initSetting();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB 오류: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "DEPTNO는 숫자로 입력하세요.");
        }
    }

    public void delete() {
        try {
            String sql = "DELETE FROM DEPT WHERE DEPTNO = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            int deptno = Integer.parseInt(tfDeptno.getText());

            pstmt.setInt(1, deptno);

            pstmt.executeUpdate();
            initSetting();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB 오류: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "DEPTNO는 숫자로 입력하세요.");
        }
    }

    public void update() {
        try {
            String sql = "UPDATE DEPT SET DNAME = ?, LOC = ? WHERE DEPTNO = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            int deptno = Integer.parseInt(tfDeptno.getText());
            String dname = tfDname.getText();
            String loc = tfLoc.getText();

            pstmt.setString(1, dname);
            pstmt.setString(2, loc);
            pstmt.setInt(3, deptno);

            pstmt.executeUpdate();
            initSetting();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "DB 오류: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "DEPTNO는 숫자로 입력하세요.");
        }
    }

    public void filterQuery() {
        String column = combo.getSelectedItem().toString(); // 선택한 컬럼 (DEPTNO, DNAME, LOC)
        String keyword = search.getText();                  // 입력한 검색어

        String filterSql = "SELECT * FROM DEPT WHERE " + column + " LIKE ?";
        // ?를 통해 preparedstatement 에서 추후에 대입할 값을 위한 빈칸

        try (PreparedStatement pstmt = con.prepareStatement(filterSql)) {
            pstmt.setString(1, "%" + keyword + "%"); // 부분 일치 검색
            ResultSet rs = pstmt.executeQuery();
            //setstring에서 빈칸을 채움

            model.setRowCount(0); // 기존 테이블 초기화

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("DEPTNO"),
                        rs.getString("DNAME"),
                        rs.getString("LOC")
                });
            }
            //add row를 통해 한줄을 채우는데 DEPTNO에서 한개,DNAME,LOC에서 한개씩 채우고 rs.next실행후
            //그다음줄을 채움
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
}
