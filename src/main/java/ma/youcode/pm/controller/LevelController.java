package ma.youcode.pm.controller;

import jakarta.validation.Valid;
import ma.youcode.pm.dto.LevelDTO;
import ma.youcode.pm.service.Implementation.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/levels")
public class LevelController {

    LevelService levelService;

    @Autowired
    public LevelController(LevelService levelService) {
        this.levelService = levelService;

    }

    //TODO:  Level Creation
    @PostMapping
    public ResponseEntity<LevelDTO> save(@Valid @RequestBody LevelDTO levelDTO) {
        LevelDTO createdLevel = levelService.save(levelDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLevel);
    }

    //TODO:  Find Level By Code
    @GetMapping("/{code}")
    public ResponseEntity<LevelDTO> findByCode(@PathVariable long code) {
            LevelDTO levelDTO = levelService.findById(code);
            return ResponseEntity.status(HttpStatus.FOUND).body(levelDTO);
    }

    //TODO:  Find All Levels
    @GetMapping
    public ResponseEntity<Page<LevelDTO>> findAll(Pageable pageable) {
        
        Page<LevelDTO> levels = levelService.finAll(pageable);
        return ResponseEntity.status(HttpStatus.FOUND).body(levels);
    }

    //TODO:  Update Level
    @PutMapping(value = "/{code}")
    public ResponseEntity<LevelDTO> update(@PathVariable long code, @Valid @RequestBody LevelDTO levelDTO) {
        LevelDTO updatedLevel = levelService.update(code, levelDTO);
        return ResponseEntity.ok(updatedLevel);

    }

    //TODO:  Delete Level
    @DeleteMapping(value = "/{code}")
    public ResponseEntity<LevelDTO> delete(@PathVariable long code) {
        levelService.delete(code);
        return ResponseEntity.noContent().build();
    }

}
