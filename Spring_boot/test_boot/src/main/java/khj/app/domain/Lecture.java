package khj.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.One;

//import java.math.BigDecimal;
import java.util.ArrayList;
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
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private String title;
    private String description;
    
    // Decimal 타입이 따로 있구나
    //private BigDecimal price;
    private Long price;

    // enum 타입은 필드로 선언 + @Enumerated 지정
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Column(name = "video_count")
    private Long videoCount;

    @Column(name = "total_duration")
    private int totalDuration;

    @Column(name = "total_students")
    private int totalStudents;

    @Column(name = "average_rate")
    private double averageRate;

    @Column(name = "like_count")
    private int likeCount;

    // lecture_category 매핑 (1:N)
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureCategory> lectureCategories = new ArrayList<>();

    // lecture_index
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureIndex> lectureIndices = new ArrayList<>();

    // enum 정의
    public enum Difficulty {
        BEGINNER, SEMI, MIDDLE, HIGH, PROFESSIONAL
    }
}
