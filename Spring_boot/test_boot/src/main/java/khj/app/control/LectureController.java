package khj.app.control;

import khj.app.domain.Lecture;
import khj.app.service.LectureService;
import khj.app.service.LectureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("lecture")
public class LectureController {

    private final LectureService service;

    @Autowired
    private LectureController(LectureService service) {
        this.service = service;
    }

    @GetMapping("list.do")
    public String getLecture(Model model) {
        List<Lecture> lecture = service.findAllLectures();
        model.addAttribute("lecture", lecture);
        return "lecture/detail";
    }
}
