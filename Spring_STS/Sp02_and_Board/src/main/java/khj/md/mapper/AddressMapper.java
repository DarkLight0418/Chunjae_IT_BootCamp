package khj.md.mapper;

import java.util.List;
import khj.md.domain.Address;

public interface AddressMapper {
	List<Address> list(); // 
	void insert(Address adderss);
	void delete(long seq);
}

/*
 <mapper namespace="khj.md.mapper.AddressMapper">
	<select id="list" resultType="Address">  이거랑
		select * from ADDRESS order by SEQ desc
	</select>
	<insert id="insert" parameterType="Address"> 이거랑 
		 insert into ADDRESS(NAME, ADDR, RDATE) values(#{name}, #{addr}, now())  <!-- 세미콜론 하면 안됨 -->
	</insert>
	<delete id="delete" parameterType="long"> 이거 매핑됐음을 알 수 있어요
		 delete from ADDRESS where SEQ=#{seq}
	</delete>
</mapper>
 */
