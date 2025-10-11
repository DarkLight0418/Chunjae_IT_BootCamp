package khj.app.repository;

import khj.app.domain.LectureReview;
import khj.test_boot.TestBootApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = TestBootApplication.class)
public class ReviewStudyOnRepoTest {
    @Autowired
    private ReviewStudyOnRepo reviewRepo;

    @Test
    void reviewAddTest() {
        LectureReview review = new LectureReview();
        review.setRating(5);
        review.setContent("정말 훌륭한 강의였어요!");

        reviewRepo.save(review);
    }
}