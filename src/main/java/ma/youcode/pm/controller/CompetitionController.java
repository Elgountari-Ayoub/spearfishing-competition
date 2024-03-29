package ma.youcode.pm.controller;

import jakarta.validation.Valid;
import ma.youcode.pm.dto.CompetitionDTO;
import ma.youcode.pm.dto.CompetitionRankingsResponse;
import ma.youcode.pm.dto.RankingDTO;
import ma.youcode.pm.service.Implementation.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
//@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/v1/competitions")
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
        CompetitionDTO competitionDTO = competitionService.findByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(competitionDTO);
    }

    //TODO:  Find All Competitions
    @GetMapping
    public ResponseEntity<Page<CompetitionDTO>> findAllCompetitions(Pageable pageable) {
        Page<CompetitionDTO> competitions = competitionService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(competitions);
    }

    //TODO:  Find All Competition Members
    @GetMapping("/{code}/members")
    public ResponseEntity<CompetitionRankingsResponse> findCompetitionRankings(@PathVariable String code, Pageable pageable) {
        CompetitionRankingsResponse competitionMembersDTO = competitionService.findRankings(code, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(competitionMembersDTO);
    }

    //TODO:  Find Passed Competitions
    @GetMapping("/passed")
    public ResponseEntity<Page<CompetitionDTO>>  findPassedCompetitions(Pageable pageable) {
        Page<CompetitionDTO> competitionsDTO = competitionService.findPassedCompetitions(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(competitionsDTO);
    }

    //TODO:  Find Today Competition
    @GetMapping("/today")
    public ResponseEntity<CompetitionDTO> findTodayCompetition() {
        CompetitionDTO competitionDTO = competitionService.findTodayCompetition();
        return ResponseEntity.status(HttpStatus.OK).body(competitionDTO);
    }

    //TODO:  Find Upcoming Competitions
    @GetMapping("/upcoming")
    public ResponseEntity<Page<CompetitionDTO>>  getUpcomingCompetitions(Pageable pageable) {
        Page<CompetitionDTO> competitionsDTO = competitionService.findUpcomingCompetitions(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(competitionsDTO);
    }
    //TODO:  Update Competition
    @PutMapping(value = "/{code}")
    public ResponseEntity<CompetitionDTO> update(
            @PathVariable String code,
            @Validated(CompetitionDTO.UpdateValidationGroup.class) @RequestBody CompetitionDTO competitionDTO) {
        CompetitionDTO updatedCompetition = competitionService.update(code, competitionDTO);
        return ResponseEntity.ok(updatedCompetition);

    }

    //TODO:  Delete Competition
    @DeleteMapping(value = "/{code}")
    public ResponseEntity<CompetitionDTO> delete(@PathVariable String code) {
        competitionService.delete(code);
        return ResponseEntity.noContent().build();


    }

    //TODO:  JOIN MEMBER A COMPETITION
    @PostMapping("/join")
    public ResponseEntity<RankingDTO> join(@Validated(RankingDTO.SaveValidationGroup.class) @RequestBody RankingDTO rankingDTO) {
        CompetitionDTO competitionDTO = competitionService.join(rankingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
