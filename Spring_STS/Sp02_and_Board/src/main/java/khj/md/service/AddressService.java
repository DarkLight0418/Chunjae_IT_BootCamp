package khj.md.service;

import java.util.List;

import khj.md.domain.Address;

public interface AddressService {  // public 있어야 함 - 다른 패키지에서 쓰려면!
	List<Address> listS(); // public abstract
	void insertS(Address adderss);
	void deleteS(long seq);
}


/*

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="khj.board.mapper.BoardMapper">
	<select id="list" resultType="Address">
		select * from BOARD order by SEQ desc
	</select>
	<insert id="insert" parameterType="Address">
		 insert into Board(WRITER, EMAIL, SUBJECT, CONTENT, RDATE) values(#{WRITER}, #{EMAIL}, #{SUBJECT}, #{CONTENT}, now())  <!-- 세미콜론 하면 안됨 -->
	</insert>
	<delete id="delete" parameterType="long">
		 delete from BOARD where SEQ=#{seq}
	</delete>
</mapper>

*/