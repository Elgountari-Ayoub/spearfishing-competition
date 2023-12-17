package ma.youcode.pm.service;

import ma.youcode.pm.dto.HuntingDTO;
import ma.youcode.pm.model.RankingId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IHuntingService {
    HuntingDTO findById(long id);

    Page<HuntingDTO> findAll(Pageable pageable);
    HuntingDTO save(HuntingDTO HuntingDTO);

    HuntingDTO update(long id, HuntingDTO huntingDTO);

    void delete(long id);

}
