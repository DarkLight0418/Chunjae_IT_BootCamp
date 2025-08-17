package addr.mvc.model;

import java.util.ArrayList;
import addr.mvc.domain.Address;

public class AddrService {
	private AddrDAO dao;
	
	// 싱글톤 객체 모델
	private static final AddrService instance = new AddrService();
	private AddrService() {
		dao = new AddrDAO();
	}
	public static AddrService getInstance() {
		return instance;
	}
	public ArrayList<Address> listS() {
		return dao.list();
	}
	public boolean insertS(Address dto) {
		return dao.insert(dto);
	}
	public boolean deleteS(long seq) {
		return dao.delete(seq);
	}
	public boolean updateS(Address dto, long seq) {
		return dao.update(dto, seq);
	}
	public boolean contentS(Address dto) {
		return dao.content(dto);
	}
}
