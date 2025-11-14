package khj.rest.service;

import khj.rest.domain.Reboard;
import java.util.List;

public interface ReboardService {
    Reboard insertS(Reboard reboard);
    Reboard updateS(Reboard reboard);
    void deleteS(long idx);

    Reboard getByIdxS(long idx);
    List<Reboard> listS();
    List<Reboard> getListByNameS(String name);
    List<Reboard> getListBySubjectS(String subject);
    List<Reboard> getListByContentS(String content);
}