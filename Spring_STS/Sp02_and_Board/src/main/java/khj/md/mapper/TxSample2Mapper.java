package khj.md.mapper;

import org.apache.ibatis.annotations.Insert;

public interface TxSample2Mapper {
	// mapper.xml 없이도 가능은 하네
	@Insert("insert into tbl_sample2 values(#{data})")
	int insertCol2(String data);
	
}
