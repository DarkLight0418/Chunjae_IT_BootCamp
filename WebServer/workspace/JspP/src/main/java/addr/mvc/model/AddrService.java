package addr.mvc.model;

import java.util.ArrayList;
import addr.mv.model.AddrDTO;
import addr.mvc.domain.Address;

public class AddrService {
	private AddrDAO dao;
	
	//SingleTon Object Model
	private static final AddrService instance = new AddrService();
	private AddrService() {
		dao = new AddrDAO();
	}
	public static AddrService getInstance() {
		return instance;
	}
	
	public ArrayList<Address> listS(){
		return dao.list();
	}
	public boolean insertS(Address dto) {
		return dao.insert(dto);
	}
	public boolean deleteS(long seq) {
		return dao.delete(seq);
	}
}
