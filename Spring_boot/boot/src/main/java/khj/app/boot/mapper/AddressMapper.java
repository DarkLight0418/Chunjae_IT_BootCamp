package khj.app.boot.mapper;

import khj.app.boot.domain.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddressMapper {
    List<Address> list();
    boolean insert(Address address);
    boolean delete(long seq);
}
