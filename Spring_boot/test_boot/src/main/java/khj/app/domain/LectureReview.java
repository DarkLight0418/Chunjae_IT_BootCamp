package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "lecture_review")
@Data
@Entity
@NoArgsConstructor
public class LectureReview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_review_id")
    private Long lectureReviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "content")
    private String content;

    @Column(name = "rating", columnDefinition = "TINYINT(5)")
    private int rating;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
