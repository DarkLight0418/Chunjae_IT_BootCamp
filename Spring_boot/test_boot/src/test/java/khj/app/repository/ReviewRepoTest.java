package khj.app.repository;

import jakarta.transaction.Transactional;
import khj.app.domain.LectureReview;
import khj.app.TestStudyOnBootApplication;
import khj.app.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Optional;


@SpringBootTest(classes = TestStudyOnBootApplication.class)
public class ReviewRepoTest {
    @Autowired
    private ReviewRepo reviewRepo;

    @Test
    void reviewAddTest() {
        LectureReview review = new LectureReview();
        review.setRating(4);
        review.setContent("나쁘지 않았음요 굿");

        reviewRepo.save(review);
    }

    @Test
    void reviewPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        Page<LectureReview> reviews = reviewRepo.findAll(pageable);
        reviews.forEach(System.out::println);

        // 아래 이하 주석들 reviews.forEach(System.out::println); 동문

        //reviews.forEach(review -> System.out.println(review));

        /*
        reviews.forEach(new Consumer<LectureReview>() {
            @Override
            public void accept(LectureReview lectureReview) {

            }
        });

        for (LectureReview review : reviews) {
            System.out.println(review);
        }

         */
    }

    @Test
    void deleteTest() {
    //    reviewRepo.deleteById(1L);
    }

    @Test
    @Transactional
    void updateReview() {
        Optional<LectureReview> lectureReviewOpt = reviewRepo.findById(1L);
        if (lectureReviewOpt.isPresent()) {
            // 실제 엔티티 꺼냄
            LectureReview review = lectureReviewOpt.get();

            Member member = new Member();
            member.setMemberId(1L);

            review.setMember(member);

            // 원하는 필드 수정
            review.setRating(5);
            review.setContent("너무 훌륭한 강의였습니다");
            review.setUpdatedAt(LocalDateTime.now());

            reviewRepo.save(review);
            System.out.println("리뷰 수정 완료 : " + review);
        } else {
            System.out.println("해당 리뷰가 존재하지 않아요...");
        }
    }
}