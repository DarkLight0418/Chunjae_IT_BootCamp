package khj.app.domain;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "lecture_answer")
public class LectureAnswer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_answer_id")
    private Long lectureAnswerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_qna_id")
    private LectureQuestion lectureQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
