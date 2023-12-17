package ma.youcode.pm.service;

import ma.youcode.pm.dto.FishDTO;
import ma.youcode.pm.model.RankingId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFishService {
    FishDTO findById(long id);
    List<FishDTO> searchByName(String name);
    Page<FishDTO> findAll(Pageable pageable);
    FishDTO save(FishDTO FishDTO);
    FishDTO update(long id, FishDTO fishDTO);

    void delete(Long id);
}
