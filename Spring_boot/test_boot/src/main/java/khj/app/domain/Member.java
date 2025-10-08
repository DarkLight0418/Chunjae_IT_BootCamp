package khj.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 255, unique = true, nullable = false)
    private String email;

    @Column(length = 25, nullable = false)
    private String password;

    @Column(length = 50, unique = true, nullable = false)
    private String nickname;

    //@Temporal(TemporalType.DATE)
    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Column(name = "withdraw_at")
    private LocalDateTime withdrawAt;

    @Column(name = "is_activated", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean isActivated;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    private RoleType roleType;

    @Column(name = "provider_id", length = 100)
    private String providerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_provider")
    private SocialProvider socialProvider;

    // enum 타입 정의
    private enum RoleType {
        STUDENT, TEACHER, ADMIN
    }
    private enum SocialProvider {
        GOOGLE, NAVER, KAKAO
    }
}
