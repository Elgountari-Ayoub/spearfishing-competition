package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.model.Member;
import ma.youcode.pm.repository.IMemberRepository;
import ma.youcode.pm.service.IMemberService;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements IMemberService {
    IMemberRepository memberRepository;
    public MemberService(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member finByNum(String num) {
        return memberRepository.findById(num).get();
    }
}
