package khj.app.service;

import khj.app.domain.Lecture;

import java.util.List;
import java.util.Optional;

public interface LectureService {
    Lecture createLecture(Lecture lecture);
    Optional<Lecture> findLectureById(Long id);
    List<Lecture> findAllLectures();
    Lecture updateLecture(Long id, Lecture lecture);
    void deleteLecture(Long id);
}
