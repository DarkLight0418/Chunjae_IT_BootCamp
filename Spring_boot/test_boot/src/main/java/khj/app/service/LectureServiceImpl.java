package khj.app.service;

import jakarta.transaction.Transactional;
import khj.app.domain.Category;
import khj.app.domain.Lecture;
import khj.app.domain.LectureCategory;
import khj.app.repository.LectureRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepo lectureRepo;

    @Override
    public Lecture createLecture(Lecture lecture) {
//        lecture.setTitle("이차방정식의 정의");
//        lecture.setPrice(0L);
//        Category category = new Category();
//        category.setName("수학");
//        category.setLecture(lecture);
//
//        lecture.getLectureCategories().add(category);
        return lectureRepo.save(lecture);
    }

    @Override
    public Optional<Lecture> findLectureById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Lecture> findAllLectures() {
        return lectureRepo.findAll();
    }

    @Override
    public Lecture updateLecture(Long id, Lecture lecture) {
        return null;
    }

    @Override
    public void deleteLecture(Long id) {
        if (!lectureRepo.existsById(id)) {
            throw new IllegalArgumentException("삭제할 강의가 존재하지 않습니다. id=" + id);
        }
        lectureRepo.deleteById(id);
    }

}
