package main.java.views;

import java.time.LocalDate;
import java.util.Objects;

public class UserInformation {
    // 싱글톤 인스턴스
    private static UserInformation instance;

    // 사용자 기본 정보
    private int userNumber;
    private String id;
    private String nickname;
    private String email;

    // 사용자 추가 정보
    private String gender;
    private String location;
    private String profileImg;
    private LocalDate birthDate;  // 생년월일 정보만 저장

    // 기본 생성자를 private으로 설정
    private UserInformation() {
    }

    /**
     * 싱글톤 인스턴스를 반환합니다.
     */
    public static UserInformation getInstance() {
        if (instance == null) {
            instance = new UserInformation();
        }
        return instance;
    }

    /**
     * 모든 사용자 정보를 초기화합니다.
     */
    public void clearData() {
        userNumber = 0;
        id = null;
        nickname = null;
        email = null;
        gender = null;
        location = null;
        profileImg = null;
        birthDate = null;
    }

    // Getter 메서드
    public int getUserNumber() {
        return userNumber;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Setter 메서드
    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserInformation that = (UserInformation) o;
        return userNumber == that.userNumber &&
                Objects.equals(id, that.id) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNumber, id, email);
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "userNumber=" + userNumber +
                ", id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}