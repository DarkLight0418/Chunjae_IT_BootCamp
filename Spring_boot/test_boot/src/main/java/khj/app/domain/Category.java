package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.ibatis.annotations.One;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CategoryId;
    @Column(name = "parent_id")
    private Long parentId;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureCategory> lectureCategories = new ArrayList<>();
}
