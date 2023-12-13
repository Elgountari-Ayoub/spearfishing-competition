package ma.youcode.pm.service;

import ma.youcode.pm.dto.CompetitionDTO;
import ma.youcode.pm.dto.RankingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICompetitionService {
    CompetitionDTO findByCode(String code);
    Page<CompetitionDTO> finAll(Pageable pageable);
    CompetitionDTO save(CompetitionDTO CompetitionDTO);
    CompetitionDTO update(String code, CompetitionDTO CompetitionDTO);
    void delete(String code);
    CompetitionDTO join(RankingDTO rankingDTO);
}
