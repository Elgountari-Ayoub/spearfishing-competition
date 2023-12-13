package ma.youcode.pm.service;

import ma.youcode.pm.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ICompetitionService {
    MemberDTO finByCode(String num);
    Page<MemberDTO> finAll(Pageable pageable);
    ResponseEntity save(MemberDTO memberDTO);
//    ResponseEntity update(MemberDTO memberDTO);
//    ResponseEntity delete(String num);

}
