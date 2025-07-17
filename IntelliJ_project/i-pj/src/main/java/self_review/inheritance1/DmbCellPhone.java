package self_review.inheritance1;

public class DmbCellPhone extends CellPhone {
    // 필드

    int channel;

    // 생성자

    DmbCellPhone(String model, String color, int channel) {
        this.model = model;
        this.color = color;
        this.channel = channel;
    }

    void turnOnDMB() {
        System.out.println("채널 " + channel +"번 DMB 방송 수신을 시작합니다.");
    }

    void changeChannelDMB(int channel) {
        this.channel = channel;
        System.out.println("채널 " + channel + "번으로 바꿉니다.");
    }
    void turnOffDMB() {
        System.out.println("DMB 방송 수신을 멈춥니다.");
    }
}
