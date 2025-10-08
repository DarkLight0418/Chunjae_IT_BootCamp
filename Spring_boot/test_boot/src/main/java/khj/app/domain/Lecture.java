package khj.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long lectureId;

    @ManyToOne
    @Column(name = "teacher_id")
    private Long teacherId;

    private String title;
    private String description;
    
    // Decimal 타입이 따로 있구나
    //private BigDecimal price;
    private Long price;

    // enum 타입은 필드로 선언 + @Enumerated 지정
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private Long videoCount;
    private int totalDuration;
    private int totalStudents;
    private double averageRate;
    private int likeCount;

    // lecture_category 매핑 (1:N)
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureCategory> lectures;
    // enum 정의
    public enum Difficulty {
        BEGINNER, SEMI, MIDDLE, HIGH, PROFESSIONAL
    }
}
