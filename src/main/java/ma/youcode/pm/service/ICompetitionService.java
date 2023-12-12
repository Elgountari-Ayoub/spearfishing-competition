package ma.youcode.pm.service;

import ma.youcode.pm.dto.member.MemberRequest;
import ma.youcode.pm.dto.member.MemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ICompetitionService {
    Optional<MemberResponse> finByCode(String num);
    Page<MemberResponse> finAll(Pageable pageable);
    ResponseEntity save(MemberRequest memberRequest);
    ResponseEntity update(MemberRequest memberRequest);
    ResponseEntity delete(String num);

}
