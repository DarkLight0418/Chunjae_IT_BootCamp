package main.java.views;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class CustomerServiceView extends JPanel {

    private JTextArea chatArea;
    private JTextField inputField;

    public CustomerServiceView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        HeaderView header = new HeaderView(MainFrame.getInstance(), "1:1 문의", true);
        add(header, BorderLayout.NORTH);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton sendButton = new JButton("전송");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMessage());

        // 초기 상담원 메시지 출력
        simulateCounselorResponse("안녕하세요! 1:1 문의 서비스입니다.", 0);
        simulateCounselorResponse("문의하실 내용유무에 따라 대화창에 '문의'를 적어주세요.", 2000);
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            chatArea.append("나: " + message + "\n");
            inputField.setText("");
            processUserMessage(message);
        }
    }

    private void simulateCounselorResponse(String response, int delay) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> chatArea.append("상담원: " + response + "\n"));
            }
        }, delay);
    }

    private void processUserMessage(String message) {
        if (message.equalsIgnoreCase("문의")) {
            int delay = 0;
            simulateCounselorResponse("1. 채팅창에 질문을 입력하고 '전송'버튼을 누르세요.", delay += 1000);
            simulateCounselorResponse("2. 상담원이 최대한 빠르게 답변드리겠습니다.", delay += 1000);
        } else {
            simulateCounselorResponse("빠른 시간 내에 답변드리겠습니다. 이용해주셔서 감사합니다.", 1000);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("1:1 문의");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new CustomerServiceView());
        frame.setSize(360, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
