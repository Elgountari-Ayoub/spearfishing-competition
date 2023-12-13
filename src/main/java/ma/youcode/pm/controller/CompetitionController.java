package ma.youcode.pm.controller;

import jakarta.validation.Valid;
import ma.youcode.pm.dto.CompetitionDTO;
import ma.youcode.pm.service.Implementation.CompetitionService;
import ma.youcode.pm.service.Implementation.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/competition")
public class CompetitionController {

    CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;

    }

    //TODO:  Competition /Creation
    @PostMapping
    public ResponseEntity<CompetitionDTO> save(@Valid @RequestBody CompetitionDTO competitionDTO) {
        CompetitionDTO createdCompetition = competitionService.save(competitionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompetition);
    }

    //TODO:  Find Competition By Code
    @GetMapping("/{code}")
    public ResponseEntity<CompetitionDTO> findByCode(@PathVariable String code) {
            CompetitionDTO competitionDTO = competitionService.finByCode(code);
            return ResponseEntity.status(HttpStatus.FOUND).body(competitionDTO);
    }

    //TODO:  Find All Competitions
    @GetMapping
    public ResponseEntity<Page<CompetitionDTO>> findAllCompetitions(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CompetitionDTO> competitions = competitionService.finAll(pageable);
        return ResponseEntity.status(HttpStatus.FOUND).body(competitions);
    }

    //TODO:  Update Competition
    @PutMapping(value = "/{code}")
    public ResponseEntity<CompetitionDTO> update(@PathVariable String code, @Valid @RequestBody CompetitionDTO competitionDTO) {
        CompetitionDTO updatedCompetition = competitionService.update(code, competitionDTO);
        return ResponseEntity.ok(updatedCompetition);

    }

    //TODO:  Delete Competition
    @DeleteMapping(value = "/{code}")
    public ResponseEntity<CompetitionDTO> delete(@PathVariable String code) {
        competitionService.delete(code);
        return ResponseEntity.noContent().build();


    }

}
