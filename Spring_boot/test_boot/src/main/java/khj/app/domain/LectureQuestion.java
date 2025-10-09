package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "lecture_question")
public class LectureQuestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_qna_id")
    private Long lectureQnaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "member_id")
    private Member member;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "answer_count", columnDefinition = "INT DEFAULT 0")
    private int answerCount;

    @Column(name = "view_count", columnDefinition = "INT DEFAULT 0")
    private int viewCount;

    @Column(name = "is_solved", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isSolved;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
