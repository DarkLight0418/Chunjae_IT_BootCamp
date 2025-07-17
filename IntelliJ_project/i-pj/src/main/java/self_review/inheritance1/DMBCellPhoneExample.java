package self_review.inheritance1;

public class DMBCellPhoneExample {
    public static void main(String[] args) {
        DmbCellPhone dmbCellPhone =new DmbCellPhone("자바폰", "검정", 10);

        System.out.println("모델: " + dmbCellPhone.model);
        System.out.println("색상: " + dmbCellPhone.color);

        System.out.println("채널: " + dmbCellPhone.channel);

        dmbCellPhone.powerOn();
        dmbCellPhone.bell();
        dmbCellPhone.sendVoice("여보세요");
        dmbCellPhone.receiveVoice("안녕하세요! 저는 김누구입니다!");
        dmbCellPhone.sendVoice("아~ 예 반갑습니다!");
        dmbCellPhone.hangUp();

        dmbCellPhone.turnOnDMB();
        dmbCellPhone.changeChannelDMB(12);
        dmbCellPhone.turnOffDMB();
    }
}
