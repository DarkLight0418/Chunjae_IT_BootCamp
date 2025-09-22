package khj.app.boot.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import khj.app.boot.domain.Address;
import khj.app.boot.dto.AddressListResult;
import khj.app.boot.repository.SpringDataJpaMariaAddressRepository;

import java.util.List;

//@Transactional(readOnly=true)//DML커밋을 막는 옵션
@Transactional
@RequiredArgsConstructor
@Service
public class SpringDataJpaPageAddressService implements PageAddressService {
    @Autowired
    private final SpringDataJpaMariaAddressRepository springDataJpaMariaAddressRepository;

    @Override
    public Page<Address> findAll(Pageable pageable) {
        Page<Address> pList = springDataJpaMariaAddressRepository.findByOrderBySeqDesc(pageable);

        /*
        List<Address> list = pList.getContent();
        int totalPages = pList.getTotalPages();
        long totalElements = pList.getTotalElements();
        int num = pList.getNumber();
        int size = pList.getSize();
        boolean hasNext = pList.hasNext();
        boolean hasPrevious = pList.hasPrevious();
        boolean isFirst = pList.isFirst();
        boolean isLast = pList.isLast();
        */

        return pList;
    }

    @Override
    public AddressListResult getAddressListResult(Pageable pageable) {
        Page<Address> pList = springDataJpaMariaAddressRepository.findByOrderBySeqDesc(pageable);
        int page = pList.getNumber(); //페이지번호
        int size = pList.getSize(); //페이지크기
        long totalCount = pList.getTotalElements(); //글의 총갯수
        int totalPageCount = pList.getTotalPages(); //페이지 총갯수

        pln("#Service page: "+page+", size: "+ size
                + ", totalCount: "+totalCount +", totalPageCount :"+totalPageCount);

        return new AddressListResult(pList, page, size, totalCount, totalPageCount);
    }

    @Override
    public Address insertS(Address address) {
        address = springDataJpaMariaAddressRepository.save(address);
        return address;
    }
    @Override
    public void deleteS(long seq) {
        springDataJpaMariaAddressRepository.deleteById(seq);
    }

    void pln(String str){
        System.out.println(str);
    }
}
