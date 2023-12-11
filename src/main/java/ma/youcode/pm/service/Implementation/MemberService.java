package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.dto.MemberRequest;
import ma.youcode.pm.dto.MemberResponse;
import ma.youcode.pm.model.Member;
import ma.youcode.pm.repository.IMemberRepository;
import ma.youcode.pm.service.IMemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        member.setName(memberRequest.getName());
        member.setFamilyName(memberRequest.getFamilyName());
        member.setAccessionDate(memberRequest.getAccessionDate());
        member.setNationality(memberRequest.getNationality());
        member.setIdentityDocument(memberRequest.getIdentityDocument());
        return member;
    }
}
