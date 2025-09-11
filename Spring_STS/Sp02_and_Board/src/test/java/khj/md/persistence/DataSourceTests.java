package khj.md.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTests {
	@Autowired
	private DataSource dataSource; //Object Injection 
	
	@Test
	public void testConnection() {
		pln("#dataSource: " + dataSource);
		try {
			Connection con = dataSource.getConnection();
			pln("#con: " + con);
		}catch(SQLException se) {
			pln("#testConnection() se: " + se);
		}
	}
	void pln(String str) {
		System.out.println(str);
	}
}
