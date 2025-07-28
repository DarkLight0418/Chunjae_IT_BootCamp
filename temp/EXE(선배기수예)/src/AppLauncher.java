package main;

import main.java.views.MainFrame;
import main.java.views.LoginView;
import main.java.views.MainPageView;
import main.java.views.SessionManagerView;

public class AppLauncher {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.getInstance();

        // 로그인 상태 확인
        if(SessionManagerView.getInstance().isLoggedIn()){
            // 로그인된 상태면 메인 페이지로
            MainPageView mainPageView = new MainPageView();
            mainFrame.showPanel("MainPage", mainPageView);
        }else{
            // 로그인 되지 않은 상태면 로그인 페이지로
            LoginView loginView = new LoginView();
            mainFrame.showPanel("Login", loginView);
        }
    }
}
