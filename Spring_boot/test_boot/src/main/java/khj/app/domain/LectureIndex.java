package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "lecture_index")
@NoArgsConstructor
public class LectureIndex {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_index_id")
    private Long lectureIndexId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Column(name = "index_title")
    private String indexTitle;

    @Column(name = "index_number")
    private Long indexNumber;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
