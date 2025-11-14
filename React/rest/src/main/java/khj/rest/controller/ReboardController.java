package khj.rest.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import khj.rest.domain.Reboard;
import khj.rest.service.ReboardService;
import java.util.List;

@RestController
@RequestMapping("reboards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600) //#매우 중요!
public class ReboardController {
    private final ReboardService reboardService;

    @PostMapping
    public ResponseEntity<Reboard> create(@RequestBody Reboard reboard) {
        Reboard saved = reboardService.insertS(reboard);
        return ResponseEntity.ok(saved);
    }
    @PutMapping("{id}")
    public ResponseEntity<Reboard> update(@PathVariable("id") long id, @RequestBody Reboard reboard) {
        Reboard existing = reboardService.getByIdxS(id);
        reboard.setIdx(existing.getIdx());
        return ResponseEntity.ok(reboardService.updateS(reboard));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        try {
            reboardService.deleteS(id);
            return ResponseEntity.ok("{\"result\":true}");
        }catch(EntityNotFoundException ee) {
            return ResponseEntity.ok("{\"result\":false}");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Reboard> getById(@PathVariable("id") long id) {
        Reboard reboard = reboardService.getByIdxS(id);
        return ResponseEntity.ok(reboard);
    }
    @GetMapping
    public ResponseEntity<List<Reboard>> getAll() {
        return ResponseEntity.ok(reboardService.listS());
    }
    @GetMapping("search")
    public ResponseEntity<List<Reboard>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String content) {

        if (name != null) {
            return ResponseEntity.ok(reboardService.getListByNameS(name));
        } else if (subject != null) {
            return ResponseEntity.ok(reboardService.getListBySubjectS(subject));
        } else if (content != null) {
            return ResponseEntity.ok(reboardService.getListByContentS(content));
        } else {
            throw new EntityNotFoundException("검색 조건이 없습니다.");
        }
    }
}