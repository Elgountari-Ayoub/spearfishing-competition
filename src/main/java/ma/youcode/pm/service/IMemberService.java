package ma.youcode.pm.service;

import ma.youcode.pm.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IMemberService {
    MemberDTO finByNum(String num);
    Page<MemberDTO> finAll(Pageable pageable);
    MemberDTO save(MemberDTO memberDTO);
    MemberDTO update(String num, MemberDTO memberDTO);
    void delete(String num);

}
