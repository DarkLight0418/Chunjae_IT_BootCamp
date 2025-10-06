package khj.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lecture")
public class lecture {
    @Id
    @Column(name = "lecture_id")
    private long lecture_id;
    private long teacher_id;

    private String title;
    private String description;
    private long price;

    private enum difficulty {
        BEGINNER, SEMI, MIDDLE, HIGH, PROFESSIONAL
    }
    private long video_count;
    private int total_duration;
    private int total_students;
    private double average_rate;
    private int like_count;
}
