package ma.youcode.pm.service;

import ma.youcode.pm.dto.CompetitionRankingsResponse;
import ma.youcode.pm.dto.MemberDTO;
import ma.youcode.pm.dto.MemberRankingsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IMemberService {
    MemberDTO findById(long id);
    Page<MemberDTO> findAll(Pageable pageable);
    MemberRankingsResponse findRankings(long id, Pageable pageable);
    MemberDTO save(MemberDTO memberDTO);
    MemberDTO update(long id, MemberDTO memberDTO);
    void delete(long id);

}
