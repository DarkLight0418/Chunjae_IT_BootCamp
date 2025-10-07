package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.ibatis.annotations.One;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "teacher")
public class Teacher {
    @Id @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @OneToMany(mappedBy = "member")
    private Long memberId;

    @OneToMany(mappedBy = "lecture")
    private List<Lecture> lectures = new ArrayList<>();

    private Long lectureId;

    private String description;
    private String subject;
    private int totalStudents;
    private int totalReview;
    private BigDecimal averageRating;
}
