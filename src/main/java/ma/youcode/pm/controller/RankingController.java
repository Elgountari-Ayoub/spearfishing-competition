package ma.youcode.pm.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import ma.youcode.pm.dto.RankingDTO;
import ma.youcode.pm.model.RankingId;
import ma.youcode.pm.service.Implementation.CompetitionService;
import ma.youcode.pm.service.Implementation.RankingService;
import ma.youcode.pm.service.Implementation.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rankings")
@CrossOrigin(origins = "http://localhost:4200/")
public class RankingController {

    RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    //TODO:  Ranking Creation
    @PostMapping
    public ResponseEntity<RankingDTO> save(@Validated(RankingDTO.SaveValidationGroup.class) @RequestBody RankingDTO rankingDTO) {
        RankingDTO createdRanking = rankingService.save(rankingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRanking);
    }

    //TODO:  Find Ranking By RankingId
    @GetMapping("/{memberId}/{competitionCode}")
    public ResponseEntity<RankingDTO> findById(
            @PathVariable long memberId,
            @PathVariable String competitionCode) {
        RankingId rankingId = new RankingId();
        rankingId.setMemberId(memberId);
        rankingId.setCompetitionCode(competitionCode);
        RankingDTO rankingDTO = rankingService.findById(rankingId);
        return ResponseEntity.status(HttpStatus.OK).body(rankingDTO);
    }

    //TODO:  Find All Rankings
    @GetMapping
    public ResponseEntity<Page<RankingDTO>> findAll(Pageable pageable) {

        Page<RankingDTO> rankings = rankingService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(rankings);
    }

    //TODO:  Update Ranking
    @PutMapping(value = "/{memberId}/{competitionCode}")
    public ResponseEntity<RankingDTO> update(@PathVariable long memberId, @PathVariable String competitionCode, @Valid @RequestBody RankingDTO rankingDTO) {
        RankingId rankingId = new RankingId();
        rankingId.setMemberId(memberId);
        rankingId.setCompetitionCode(competitionCode);
        RankingDTO updatedRanking = rankingService.update(rankingId, rankingDTO);
        return ResponseEntity.ok(updatedRanking);

    }

    //TODO:  Delete Ranking
    @DeleteMapping(value = "/{memberId}/{competitionCode}")
    public ResponseEntity<RankingDTO> delete(@PathVariable long memberId, @PathVariable String competitionCode) {
        RankingId rankingId = new RankingId();
        rankingId.setMemberId(memberId);
        rankingId.setCompetitionCode(competitionCode);
        rankingService.delete(rankingId);
        return ResponseEntity.noContent().build();
    }

}
