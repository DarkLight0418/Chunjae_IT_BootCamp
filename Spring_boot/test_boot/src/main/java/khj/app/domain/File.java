package khj.app.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    @Column(name = "original_name", length = 255)
    private String originalName;

    @Column(name = "store_name")
    private String storeName;

    @Column(length = 10)
    private String ext;

    @Column(columnDefinition = "BIGINT")
    private Long size;

    @Column(name = "target_type", length = 50)
    private String targetType;

    @Column(name = "target_id", columnDefinition = "BIGINT UNSIGNED")
    private Long targetId;
}
