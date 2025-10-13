package khj.app.repository;

import khj.app.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static khj.app.domain.Member.RoleType.STUDENT;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepoTest {

    @Autowired
    private MemberRepo memberRepo;

    @Test
    void create() {
        Member member = new Member();
        member.setActivated(true);
        member.setPassword("1234");
        member.setNickname("말링이");
        member.setName("김마리");
        member.setPassword("");
        member.setEmail("mari@test.com");
        member.setRoleType(STUDENT);
        memberRepo.save(member);
        System.out.println("멤버 추가 완료 : " + member);
    }
}