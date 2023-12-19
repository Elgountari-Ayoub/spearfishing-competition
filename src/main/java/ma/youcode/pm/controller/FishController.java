package ma.youcode.pm.controller;

import jakarta.validation.Valid;
import ma.youcode.pm.dto.FishDTO;
import ma.youcode.pm.service.Implementation.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fishes")
@CrossOrigin(origins = "http://localhost:4200/")
public class FishController {

    FishService fishService;

    @Autowired
    public FishController(FishService fishService) {
        this.fishService = fishService;

    }

    //TODO:  Fish Creation
    @PostMapping
    public ResponseEntity<FishDTO> save(@Valid @RequestBody FishDTO fishDTO) {
        FishDTO createdFish = fishService.save(fishDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFish);
    }

    //TODO:  Find Fish By Id
    @GetMapping("/{id}")
    public ResponseEntity<FishDTO> findByCode(@PathVariable long id) {
        FishDTO fishDTO = fishService.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(fishDTO);
    }

    //TODO: Search Fish by Name
    @GetMapping("/search")
    public ResponseEntity<List<FishDTO>> searchByName(@RequestParam(name = "name") String name) {
        List<FishDTO> matchingFishes = fishService.searchByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(matchingFishes);
    }

    //TODO:  Find All Fishes
    @GetMapping
    public ResponseEntity<Page<FishDTO>> findAll(Pageable pageable) {
        Page<FishDTO> levels = fishService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.FOUND).body(levels);
    }

    //TODO:  Update Fish
    @PutMapping(value = "/{id}")
    public ResponseEntity<FishDTO> update(@PathVariable long id, @Valid @RequestBody FishDTO fishDTO) {
        FishDTO updatedLevel = fishService.update(id, fishDTO);
        return ResponseEntity.ok(updatedLevel);

    }

    //TODO:  Delete Fish
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FishDTO> delete(@PathVariable long id) {
        fishService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
