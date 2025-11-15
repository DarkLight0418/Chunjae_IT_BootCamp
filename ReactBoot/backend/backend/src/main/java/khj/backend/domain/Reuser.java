package khj.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "reuser")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Json Ignore 사용하려면 쓰는 거
public class Reuser {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false, updatable=false)
    private Long id;

    @Column(nullable=false, unique=true)
    private String username;

    @JsonIgnore //나중에추가 ( 비밀번호 감추기 )
    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String role;

    private String name;

    @JsonIgnore //나중에추가
    @OneToMany(cascade=CascadeType.ALL, mappedBy="reuser")
    private List<Readdress> readdresses;
}
