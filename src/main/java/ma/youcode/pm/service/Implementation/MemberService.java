package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.dto.CompetitionDTO;
import ma.youcode.pm.dto.CompetitionRankingsResponse;
import ma.youcode.pm.dto.MemberDTO;
import ma.youcode.pm.dto.MemberRankingsResponse;
import ma.youcode.pm.exception.DuplicateMemberException;
import ma.youcode.pm.exception.MemberNotFoundException;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Member;
import ma.youcode.pm.model.Ranking;
import ma.youcode.pm.repository.IMemberRepository;
import ma.youcode.pm.repository.IRankingRepository;
import ma.youcode.pm.service.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class MemberService implements IMemberService {
    IMemberRepository memberRepository;
    IRankingRepository rankingRepository;

    private ModelMapper modelMapper;

    public MemberService(IMemberRepository memberRepository, IRankingRepository rankingRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.rankingRepository = rankingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MemberDTO finByNum(long num) {
        Member member = memberRepository.findById(num)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with num: " + num));

        return modelMapper.map(member, MemberDTO.class);
    }

    @Override
    public Page<MemberDTO> findAll(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);
        return members.map(member -> modelMapper.map(member, MemberDTO.class));
    }

    @Override
    public MemberRankingsResponse findRankings(long num, Pageable pageable) {
        MemberDTO memberDTO = finByNum(num);
        Member member = modelMapper.map(memberDTO, Member.class);

        Page<Ranking> rankings = rankingRepository.findByMember(member, pageable);

        MemberRankingsResponse memberRankingsResponse = new MemberRankingsResponse();
        memberRankingsResponse.setMember(member);
        memberRankingsResponse.setRankings(rankings);

        return memberRankingsResponse;
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        if (memberRepository.existsByIdentityNumber(memberDTO.getIdentityNumber())) {
            throw new DuplicateMemberException("Member with Identity Number " + memberDTO.getIdentityNumber() + " already exists.");
        }
        Member member = modelMapper.map(memberDTO, Member.class);
        member = memberRepository.save(member);
        return modelMapper.map(member, MemberDTO.class);
    }

    @Override
    public MemberDTO update(long num, MemberDTO memberDTO) {
        Member existingMember = memberRepository.findById(num)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with num: " + num));

        existingMember.setName(memberDTO.getName());
        existingMember.setFamilyName(memberDTO.getFamilyName());
        existingMember.setNationality(memberDTO.getNationality());
        existingMember.setIdentityDocument(memberDTO.getIdentityDocument());
        existingMember.setIdentityNumber(memberDTO.getIdentityNumber());

        existingMember = memberRepository.save(existingMember);

        return modelMapper.map(existingMember, MemberDTO.class);
    }


    @Override
    public void delete(long num) {
        Member member = memberRepository.findById(num)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with num: " + num));
        memberRepository.delete(member);
    }

    //    HELPER METHODS

}
