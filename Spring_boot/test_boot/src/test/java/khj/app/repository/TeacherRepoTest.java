package khj.app.repository;

import khj.app.domain.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepoTest {
    @Autowired
    private TeacherRepo teacherRepo;

    @Test
    void insertSsam() {
        Teacher teacher = new Teacher();
        // teacher.setTeacherId(1L);
        teacher.setSubject("수학");
        teacher.setDescription("안녕하세요 김누구입니다.");
    }
}