package ma.youcode.pm.controller;

import jakarta.validation.Valid;
import ma.youcode.pm.dto.HuntingDTO;
import ma.youcode.pm.service.Implementation.HuntingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/huntings")
public class HuntingController {

    HuntingService huntingService;

    @Autowired
    public HuntingController(HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    //TODO:  Hunting Creation
    @PostMapping
    public ResponseEntity<HuntingDTO> save(@Valid @RequestBody HuntingDTO huntingDTO) {
        HuntingDTO createdHunting = huntingService.save(huntingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHunting);
    }

    //TODO:  Find Hunting By id
    @GetMapping("/{id}")
    public ResponseEntity<HuntingDTO> findByNum(
            @PathVariable long id) {
        HuntingDTO huntingDTO = huntingService.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(huntingDTO);
    }

    //TODO:  Find All Huntings
    @GetMapping
    public ResponseEntity<Page<HuntingDTO>> findAll(Pageable pageable) {
        
        Page<HuntingDTO> huntings = huntingService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.FOUND).body(huntings);
    }

    //TODO:  Update Hunting
    @PutMapping(value = "/{id}")
    public ResponseEntity<HuntingDTO> update(@PathVariable long id, @Valid @RequestBody HuntingDTO huntingDTO) {
        HuntingDTO updatedHunting = huntingService.update(id, huntingDTO);
        return ResponseEntity.ok(updatedHunting);
    }

    //TODO:  Delete Hunting
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HuntingDTO> delete(@PathVariable long id) {
        huntingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
