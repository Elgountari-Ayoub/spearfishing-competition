package ma.youcode.pm.service;

import ma.youcode.pm.dto.CompetitionDTO;
import ma.youcode.pm.dto.RankingDTO;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Member;
import ma.youcode.pm.model.RankingId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRankingService {
    RankingDTO findById(RankingId rankingId);
    Page<RankingDTO> finAll(Pageable pageable);
    RankingDTO save(RankingDTO RankingDTO);

    RankingDTO update(RankingId rankingId, RankingDTO rankingDTO);

    //    RankingDTO update(RankingId rankingId);
    void delete(RankingId rankingId);

}
