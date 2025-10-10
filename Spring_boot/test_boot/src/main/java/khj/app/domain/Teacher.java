package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;

//import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "teacher")
public class Teacher {
    @Id @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @OneToOne(mappedBy = "teacher")
    private Member member;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lecture> lectures = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 100)
    private String subject;

    @Column(name = "total_students", columnDefinition = "INT DEFAULT 0")
    private int totalStudents;

    @Column(name = "total_review", columnDefinition = "INT DEFAULT 0")
    private int totalReview;

    @Column(name = "average_rating", precision = 3, scale = 2)
    private Long averageRating;
}
