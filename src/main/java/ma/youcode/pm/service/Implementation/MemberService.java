package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.dto.MemberRequest;
import ma.youcode.pm.dto.MemberResponse;
import ma.youcode.pm.model.Member;
import ma.youcode.pm.repository.IMemberRepository;
import ma.youcode.pm.service.IMemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class MemberService implements IMemberService {
    IMemberRepository memberRepository;

    public MemberService(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<MemberResponse> finByNum(String num) {
        return memberRepository.findById(num).map(this::mapToResponse);
    }

    @Override
    public Page<MemberResponse> finAll(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);
        return members.map(this::mapToResponse);
    }

    @Override
    public ResponseEntity<?> save(MemberRequest memberRequest) {
        Member member = mapToEntity(memberRequest);
        memberRepository.save(member);
        return new ResponseEntity(mapToResponse(member), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(MemberRequest memberRequest) {
        Member member = mapToEntity(memberRequest);
        memberRepository.save(member);
        return new ResponseEntity(mapToResponse(member), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> delete(String num) {
        memberRepository.deleteById(num);
        return new ResponseEntity("Member deleted successfully", HttpStatus.OK);
    }

    //    HELPER METHODS
    public MemberResponse mapToResponse(Member member) {
        MemberResponse response = new MemberResponse();
        response.setNum(member.getNum());
        response.setName(member.getName());
        response.setFamilyName(member.getFamilyName());
        response.setAccessionDate(member.getAccessionDate());
        response.setNationality(member.getNationality());
        response.setIdentityDocument(member.getIdentityDocument());
        return response;
    }

    public static Member mapToEntity(MemberRequest memberRequest) {
        Member member = new Member();
        member.setNum(memberRequest.getNum());
        member.setName(memberRequest.getName());
        member.setFamilyName(memberRequest.getFamilyName());
        member.setAccessionDate(memberRequest.getAccessionDate());
        member.setNationality(memberRequest.getNationality());
        member.setIdentityDocument(memberRequest.getIdentityDocument());
        return member;
    }
}
