package ma.youcode.pm.service;

import ma.youcode.pm.dto.ManagerDTO;
import ma.youcode.pm.dto.MemberRankingsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IManagerService {
    ManagerDTO findById(long id);
    boolean validate(long id);
    boolean refuse(long id);
//    Page<ManagerDTO> findAll(Pageable pageable);
//    ManagerDTO save(ManagerDTO managerDTO);
//    ManagerDTO update(long id, ManagerDTO managerDTO);
//    void delete(long id);

}
