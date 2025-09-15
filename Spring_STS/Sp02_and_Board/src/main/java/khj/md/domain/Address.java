package khj.md.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor  //모든 파라미터 넣는 생성자 만들어주는 어노테이션
@NoArgsConstructor  // Args == 파라미터 (즉 디폴트 생성자)
@Data
public class Address {
	private long seq;
	private String name;
	private String addr;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	private java.sql.Date rdate;
}
