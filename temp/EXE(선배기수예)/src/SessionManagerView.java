package main.java.views;

/**
 * 사용자 세션을 관리하는 싱글톤 클래스입니다.
 * 로그인 상태 및 현재 로그인한 사용자의 정보를 관리합니다.
 */
public class SessionManagerView {
    // 싱글톤 인스턴스
    private static volatile SessionManagerView instance;

    // 현재 로그인한 사용자 ID
    private String loggedInUserId;

    // 기본 생성자를 private으로 설정
    private SessionManagerView() {
    }

    /**
     * 싱글톤 인스턴스를 반환합니다.
     * Double-checked locking을 사용하여 스레드 안전성을 보장합니다.
     */
    public static SessionManagerView getInstance() {
        if (instance == null) {
            synchronized (SessionManagerView.class) {
                if (instance == null) {
                    instance = new SessionManagerView();
                }
            }
        }
        return instance;
    }

    /**
     * 현재 사용자의 로그인 상태를 확인합니다.
     * 
     * @return 로그인 상태 여부
     */
    public boolean isLoggedIn() {
        return loggedInUserId != null && !loggedInUserId.isEmpty();
    }

    /**
     * 로그인 상태를 확인하고, 로그인되지 않은 경우 로그인 화면으로 이동합니다.
     */
    public void checkLoginAndRedirect() {
        if (!isLoggedIn()) {
            MainFrame.getInstance().showPanel("Login", new LoginView());
        }
    }

    /**
     * 사용자 로그인을 처리합니다.
     * 
     * @param userId 로그인할 사용자의 ID
     */
    public void login(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("사용자 ID는 null이거나 빈 값일 수 없습니다.");
        }
        this.loggedInUserId = userId;
    }

    /**
     * 사용자 로그아웃을 처리합니다.
     * 로그아웃 시 UserInformation의 데이터도 함께 초기화합니다.
     */
    public void logout() {
        this.loggedInUserId = null;
        // UserInformation 초기화 제거
        // UserInformation.getInstance().clearData();
    }

    /**
     * 현재 로그인한 사용자의 ID를 반환합니다.
     * 
     * @return 로그인한 사용자의 ID, 로그인되지 않은 경우 null
     */
    public String getLoggedInUserId() {
        return loggedInUserId;
    }

    @Override
    public String toString() {
        return "SessionManagerView{" +
                "loggedInUserId='" + loggedInUserId + '\'' +
                ", isLoggedIn=" + isLoggedIn() +
                '}';
    }
}
