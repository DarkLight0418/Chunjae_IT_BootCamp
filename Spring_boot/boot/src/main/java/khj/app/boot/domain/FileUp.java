package khj.app.boot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
//@AllArgsConstructor
@Data
@Table(name = "fileup")
public class FileUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;
    private String orgnm;
    private String savednm;
    private String savedpath;

    @Builder
    public FileUp(Long id, String orgnm, String savednm, String savedpath){
        this.id = id;
        this.orgnm = orgnm;
        this.savednm = savednm;
        this.savedpath = savedpath;
    }
}
/*

// 빌더를 이용한 옵션

class User{
    void m(){
        FileUp fileup = FileUp.builder()
                .id(10L)
                .savedpath("c")
                .savednm("b")
                .orgnm("a")
                .build();
    }
}
*/

