package main.java.views;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MeetingMakeView extends JPanel implements ActionListener {
    // UI 크기 상수
    private static final int WINDOW_WIDTH = 330;
    private static final int WINDOW_HEIGHT = 800;
    private static final int FIELD_WIDTH = 200;
    private static final int FIELD_HEIGHT = 25;
    private static final int TEXT_AREA_HEIGHT = 120;
    private static final int GRID_SPACING = 10;
    private static final int INSET_SIZE = 10;
    private static final int BUTTON_SPACING = 20;

    private static final Font BUTTON_FONT = new Font("맑은 고딕", Font.BOLD, 16);
    private static final Color MAIN_COLOR = new Color(144, 203, 251);

    // 입력 제한 상수
    private static final int MAX_TITLE_LENGTH = 50;
    private static final int MAX_CONTENT_LENGTH = 500;
    private static final int MIN_PARTICIPANTS = 2;
    private static final int MAX_PARTICIPANTS = 10;
    private static final int MINUTE_INTERVAL = 10;

    // UI 컴포넌트
    private final JButton submitButton = createSubmitButton();
    private final JTextField titleField = new JTextField(20);
    private final JDateChooser dateChooser = createDateChooser();
    private final JTextArea messageField = createMessageField();
    private final JComboBox<String> hourComboBox = createHourComboBox();
    private final JComboBox<String> minuteComboBox = createMinuteComboBox();
    private final JComboBox<String> recruitComboBox = createRecruitComboBox();
    private final JPanel center = createCenterPanel();

    // 모임 정보
    private final int restaurantNumber;
    private final String restaurantName;
    private String meetingDate;

    public MeetingMakeView(int restaurantNumber, String restaurantName) {
        this.restaurantNumber = restaurantNumber;
        this.restaurantName = restaurantName;

        if (!SessionManagerView.getInstance().isLoggedIn()) {
            SessionManagerView.getInstance().checkLoginAndRedirect();
            return;
        }

        setLayout(new BorderLayout());
        initializeUI();
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        return panel;
    }

    private JDateChooser createDateChooser() {
        JDateChooser chooser = new JDateChooser();
        chooser.setDateFormatString("yyyy-MM-dd");
        chooser.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        return chooser;
    }

    private JTextArea createMessageField() {
        JTextArea field = new JTextArea(15, 20);
        field.setMinimumSize(new Dimension(FIELD_WIDTH, TEXT_AREA_HEIGHT));
        field.setLineWrap(true);
        field.setWrapStyleWord(true);
        return field;
    }

    private JComboBox<String> createHourComboBox() {
        JComboBox<String> combo = new JComboBox<>();
        for (int i = 0; i < 24; i++) {
            combo.addItem(String.format("%02d시", i));
        }
        return combo;
    }

    private JComboBox<String> createMinuteComboBox() {
        JComboBox<String> combo = new JComboBox<>();
        for (int i = 0; i < 60; i += MINUTE_INTERVAL) {
            combo.addItem(String.format("%02d분", i));
        }
        return combo;
    }

    private JComboBox<String> createRecruitComboBox() {
        JComboBox<String> combo = new JComboBox<>();
        for (int i = MIN_PARTICIPANTS; i <= MAX_PARTICIPANTS; i++) {
            combo.addItem(String.format("%2d명", i));
        }
        return combo;
    }

    private JButton createSubmitButton() {
        JButton button = new JButton("모임 만들기 완료 >");
        button.setPreferredSize(new Dimension(180,40));
        button.setBackground(MAIN_COLOR);
        button.setBorder(new LineBorder(Color.GRAY, 0, true));
        button.setFont(BUTTON_FONT);
        button.addActionListener(this);
        return button;
    }

    private void initializeUI() {
        // Header 추가
        add(new HeaderView(MainFrame.getInstance(), "모임 만들기", true), BorderLayout.NORTH);

        // 메인 컨텐츠 패널 설정
        JPanel contentPanel = createContentPanel();
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createContentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setMaximumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        GridBagConstraints gbc = createGridBagConstraints();

        // 모임 장소
        addFormField(panel, "모임 장소:", gbc, 0);
        JLabel locationLabel = new JLabel(restaurantNumber != 0 ? restaurantName : "모임 장소를 골라주세요!", JLabel.CENTER);
        addComponent(panel, locationLabel, gbc, 1, 0, 3);

        // 모임 제목
        addFormField(panel, "모임 제목:", gbc, 1);
        addComponent(panel, titleField, gbc, 1, 1, 3);

        // 모임 일시
        addFormField(panel, "모임 일시:", gbc, 2);
        addDateTimeComponents(panel, gbc);

        // 모집 인원
        addFormField(panel, "모집 인원:", gbc, 4);
        addComponent(panel, recruitComboBox, gbc, 1, 4, 3);

        // 소개 메시지
        addFormField(panel, "소개 메시지:", gbc, 5);
        addComponent(panel, messageField, gbc, 1, 5, 3);

        // 제출 버튼
        addSubmitButton(panel, gbc);

        return panel;
    }

    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private void addDateTimeComponents(JPanel panel, GridBagConstraints gbc) {
        // 날짜 선택기
        JPanel datePanel = new JPanel(new BorderLayout());
        datePanel.add(dateChooser);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(datePanel, gbc);

        // 시간 선택
        JPanel timePanel = new JPanel(new GridLayout(1, 2, GRID_SPACING, GRID_SPACING));
        timePanel.add(hourComboBox);
        timePanel.add(minuteComboBox);
        gbc.gridy = 3;
        panel.add(timePanel, gbc);
    }

    private void addSubmitButton(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(BUTTON_SPACING, 0, BUTTON_SPACING, 0);
        panel.add(submitButton, gbc);
    }

    private void addFormField(JPanel panel, String labelText, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel(labelText), gbc);
    }

    private void addComponent(JPanel panel, JComponent component, GridBagConstraints gbc,
            int gridx, int gridy, int gridwidth) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(component, gbc);
    }

    private boolean validateForm() {
        if (restaurantNumber == 0) {
            showWarning("식당을 선택해주세요");
            return false;
        }

        String title = titleField.getText().trim();
        if (title.isEmpty()) {
            showWarning("제목을 입력해주세요");
            return false;
        }
        if (title.length() > MAX_TITLE_LENGTH) {
            showWarning("제목은 " + MAX_TITLE_LENGTH + "자 이내로 입력해주세요");
            return false;
        }

        if (dateChooser.getDate() == null) {
            showWarning("모임 날짜를 선택해주세요");
            return false;
        }

        if (!validateDateTime()) {
            return false;
        }

        String message = messageField.getText().trim();
        if (message.isEmpty()) {
            showWarning("모임에 대한 소개를 입력해주세요");
            return false;
        }
        if (message.length() > MAX_CONTENT_LENGTH) {
            showWarning("내용은 " + MAX_CONTENT_LENGTH + "자 이내로 입력해주세요");
            return false;
        }

        return true;
    }

    private boolean validateDateTime() {
        Date selectedDate = dateChooser.getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        String hour = ((String) hourComboBox.getSelectedItem()).replace("시", "").trim();
        String minute = ((String) minuteComboBox.getSelectedItem()).replace("분", "").trim();

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
        calendar.set(Calendar.SECOND, 0);

        Date selectedDateTime = calendar.getTime();
        if (selectedDateTime.before(new Date())) {
            showWarning("이미 지난 시간입니다");
            return false;
        }

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        meetingDate = dateTimeFormat.format(selectedDateTime);
        return true;
    }

    private void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "안내", JOptionPane.WARNING_MESSAGE);
    }

    private void createMeeting() {
        String title = titleField.getText().trim();
        int recruitNumber = recruitComboBox.getSelectedIndex() + MIN_PARTICIPANTS;
        String message = messageField.getText().trim();
        new NewMeeting(title, meetingDate, recruitNumber, message, restaurantNumber);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton && validateForm()) {
            Object[] options = { "확인", "취소" };
            int result = JOptionPane.showOptionDialog(
                    this,
                    "이대로 모임을 생성하시겠습니까?",
                    "확인",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (result == JOptionPane.YES_OPTION) {
                createMeeting();
            }
        }
    }
}