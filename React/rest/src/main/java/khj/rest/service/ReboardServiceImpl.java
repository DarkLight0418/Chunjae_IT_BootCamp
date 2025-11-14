package khj.rest.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import khj.rest.domain.Reboard;
import khj.rest.repository.ReboardRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReboardServiceImpl implements ReboardService {
    private final ReboardRepository reboardRepository;

    @Override
    public Reboard insertS(Reboard reboard) {
        return reboardRepository.save(reboard);
    }
    @Override
    public Reboard updateS(Reboard reboard) {
        return reboardRepository.save(reboard);
    }
    @Override
    public void deleteS(long idx) {
        if(!reboardRepository.existsById(idx)){
            throw new EntityNotFoundException("삭제하려는 글(idx:"+idx+")가 존재X");
        }
        reboardRepository.deleteById(idx);
    }

    @Override
    public Reboard getByIdxS(long idx) {
        //return reboardRepository.findById(idx).get();
        return reboardRepository.findById(idx)
                .orElseThrow(() -> new EntityNotFoundException("찾으려는 글(idx:"+idx+")가 존재X"));
    }
    @Override
    public List<Reboard> listS() {
        return reboardRepository.findAll(Sort.by(Sort.Direction.DESC, "idx"));
    }
    @Override
    public List<Reboard> getListByNameS(String name) {
        return reboardRepository.findByName(name);
    }
    @Override
    public List<Reboard> getListBySubjectS(String subject) {
        return reboardRepository.findBySubjectContaining(subject);
    }
    @Override
    public List<Reboard> getListByContentS(String content) {
        return reboardRepository.findByContentContaining(content);
    }
}