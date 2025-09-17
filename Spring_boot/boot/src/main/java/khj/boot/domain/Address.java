package khj.boot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private long seq;
    private String name;
    private String addr;
    private Date rdate;
}
