package khj.app.boot.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import khj.app.boot.domain.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Repository
public class JdbcTemplateMariaAddressRepository implements AddressRepository2 {
    private final JdbcTemplate jdbcTemplate;
    //@Autowired
    public JdbcTemplateMariaAddressRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Address> list() {
        String sql = "select * from ADDRESS order by SEQ desc";
        List<Address> list = jdbcTemplate.query(sql, addressRowMapper());
        return list;
    }
    private RowMapper<Address> addressRowMapper(){
        return new RowMapper<Address>(){
            @Override
            public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
                Address address = new Address();
                address.setSeq(rs.getLong("seq"));
                address.setName(rs.getString("name"));
                address.setAddr(rs.getString("addr"));
                address.setRdate(rs.getTimestamp("rdate")); //hh:mm:ss 정보도 담음
                return address;
            }
        };
    }
    @Override
    public boolean insert(Address address) {
        String sql = "insert into ADDRESS(name, addr, rdate) values(?,?, now())";
        int count = jdbcTemplate.update(sql, address.getName(), address.getAddr());
        if(count > 0) return true;
        else return false;
    }
    @Override
    public boolean delete(long seq) {
        String sql  = "delete from ADDRESS where SEQ=?";
        int count = jdbcTemplate.update(sql, seq);
        if(count > 0) return true;
        else return false;
    }
}
