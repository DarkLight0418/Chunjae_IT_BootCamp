package khj.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@OneToMany()
public class lecture_category {
    private long lecture_category_id;
    private long lecture_id;
    private long category_id;
}
