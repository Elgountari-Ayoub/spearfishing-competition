package ma.youcode.pm.service;

import ma.youcode.pm.dto.CompetitionDTO;
import ma.youcode.pm.dto.RankingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICompetitionService {
    CompetitionDTO findByCode(String code);
    Page<CompetitionDTO> finAll(Pageable pageable);

    List<CompetitionDTO> findPassedCompetitions() ;

    CompetitionDTO findTodayCompetition();

    List<CompetitionDTO> findUpcomingCompetitions();
    CompetitionDTO save(CompetitionDTO CompetitionDTO);
    CompetitionDTO update(String code, CompetitionDTO CompetitionDTO);
    void delete(String code);
    CompetitionDTO join(RankingDTO rankingDTO);
}
