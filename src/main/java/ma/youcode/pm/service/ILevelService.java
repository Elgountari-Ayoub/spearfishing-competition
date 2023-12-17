package ma.youcode.pm.service;

import ma.youcode.pm.dto.LevelDTO;
import ma.youcode.pm.model.RankingId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILevelService {
    LevelDTO findById(Long code);
    Page<LevelDTO> findAll(Pageable pageable);
    LevelDTO save(LevelDTO LevelDTO);
    LevelDTO update(Long code, LevelDTO levelDTO);
    void delete(Long code);

}
