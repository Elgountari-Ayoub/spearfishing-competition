package ma.youcode.pm.service;

import ma.youcode.pm.dto.CompetitionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ICompetitionService {
    CompetitionDTO finByCode(String code);
    Page<CompetitionDTO> finAll(Pageable pageable);
    CompetitionDTO save(CompetitionDTO CompetitionDTO);
    CompetitionDTO update(String code, CompetitionDTO CompetitionDTO);
    void delete(String code);
}
