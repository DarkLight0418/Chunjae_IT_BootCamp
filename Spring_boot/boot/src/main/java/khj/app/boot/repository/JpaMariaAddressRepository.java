package khj.app.boot.repository;

import jakarta.persistence.EntityManager;
import khj.app.boot.domain.Address;
import java.util.List;

//@Repository
public class JpaMariaAddressRepository implements AddressRepository2 {
    private final EntityManager entityManager;
    //@Autowired
    public JpaMariaAddressRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Address> list() {
        List<Address> list = entityManager
                .createQuery("select a from Address a order by a.name desc", Address.class)
                .getResultList();

        return list;
    }
    @Override
    public boolean insert(Address address) { //insert, update, delete 기능은 jpql이 필요없음
        entityManager.persist(address);
        return true;
    }
    @Override
    public boolean delete(long seq) {
        entityManager.remove(entityManager.find(Address.class, seq));
        return true;
    }
}
