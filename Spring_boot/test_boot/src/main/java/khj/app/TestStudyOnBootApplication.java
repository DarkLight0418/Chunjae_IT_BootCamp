package khj.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "khj.app") // 전체 패키지 스캔 // JPA 레포지토리 경로
@EntityScan(basePackages = "khj.app.domain") // 엔티티 경로
public class TestStudyOnBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestStudyOnBootApplication.class, args);
	}

}
