package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lecture_category")
@Data
public class LectureCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_category_id")
    private Long lectureCategoryId;

    // 다대일: 여러 매핑이 하나의 강의에 연결
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    // 다대일: 여러 매핑이 하나의 카테고리에 연결
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
