package khj.app.repository;

import khj.app.domain.Lecture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LectureRepoTest {
    @Autowired
    private LectureRepo lectureRepo;

    @Test
    void setLecture() {
        Lecture lecture = new Lecture();
    }
}