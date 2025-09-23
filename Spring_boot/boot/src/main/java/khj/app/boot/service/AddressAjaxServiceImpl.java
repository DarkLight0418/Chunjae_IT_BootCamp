package khj.app.boot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import khj.app.boot.domain.Address;
import khj.app.boot.repository.SpringDataJpaMariaAddressRepository;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressAjaxServiceImpl implements AddressAjaxService {
    private final SpringDataJpaMariaAddressRepository repository;

    @Override
    public List<Address> listS() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "seq"));
    }
    @Override
    public Address insertS(Address address) {
        address = repository.save(address);
        return address;
    }
    @Override
    public void deleteS(long seq) {
        repository.deleteById(seq);
    }

    @Override
    public Address getBySeqS(long seq) {
        Address address = repository.findById(seq).get();
        return address;
    }
    @Override
    public List<Address> getListByNameS(String name) {
        return repository.findByNameContaining(name);
    }
}
