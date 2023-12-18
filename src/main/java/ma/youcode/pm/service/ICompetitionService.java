package ma.youcode.pm.service;

import ma.youcode.pm.dto.CompetitionDTO;
import ma.youcode.pm.dto.CompetitionRankingsResponse;
import ma.youcode.pm.dto.MemberDTO;
import ma.youcode.pm.dto.RankingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICompetitionService {
    CompetitionDTO findByCode(String code);
    Page<CompetitionDTO> findAll(Pageable pageable);
    CompetitionRankingsResponse findRankings(String code, Pageable pageable);
    Page<CompetitionDTO> findPassedCompetitions(Pageable pageable);
    CompetitionDTO findTodayCompetition();
    Page<CompetitionDTO> findUpcomingCompetitions(Pageable pageable);
    CompetitionDTO save(CompetitionDTO CompetitionDTO);
    CompetitionDTO update(String code, CompetitionDTO CompetitionDTO);
    void delete(String code);
    CompetitionDTO join(RankingDTO rankingDTO);
}
