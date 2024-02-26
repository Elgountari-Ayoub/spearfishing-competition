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
import ma.youcode.pm.model.User;
import ma.youcode.pm.repository.IMemberRepository;
import ma.youcode.pm.repository.IRankingRepository;
import ma.youcode.pm.service.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class MemberService implements IMemberService {
    IMemberRepository memberRepository;
    IRankingRepository rankingRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper;

    public MemberService(IMemberRepository memberRepository, IRankingRepository rankingRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.rankingRepository = rankingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MemberDTO findById(long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + id));

        return modelMapper.map(member, MemberDTO.class);
    }

    @Override
    public Page<MemberDTO> findAll(Pageable pageable) {
        Page<Member> members = memberRepository.findAll(pageable);
        return members.map(member -> modelMapper.map(member, MemberDTO.class));
    }

    @Override
    public MemberRankingsResponse findRankings(long id, Pageable pageable) {
        MemberDTO memberDTO = findById(id);
        Member member = modelMapper.map(memberDTO, Member.class);

        Page<Ranking> rankings = rankingRepository.findByMember(member, pageable);

        MemberRankingsResponse memberRankingsResponse = new MemberRankingsResponse();
        memberRankingsResponse.setMember(member);
        memberRankingsResponse.setRankings(rankings);

        return memberRankingsResponse;
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
            if (memberRepository.existsByIdentityNumber(memberDTO.getIdentityNumber().trim())) {
            throw new DuplicateMemberException("Member with Identity Number " + memberDTO.getIdentityNumber() + " already exists.");
        }

        var user = User.builder().name(memberDTO.getName())
                .email(memberDTO.getEmail()).password(passwordEncoder.encode(memberDTO.getPassword()))
                .role(memberDTO.getRole()).build();

        Member member = modelMapper.map(user, Member.class);
        member.setFamilyName(memberDTO.getFamilyName());
        member.setNationality(memberDTO.getNationality());
        member.setIdentityNumber(memberDTO.getIdentityNumber());
        member.setIdentityDocument(memberDTO.getIdentityDocument());
        member.setIsAccepted(memberDTO.getIsAccepted());

        member = memberRepository.save(member);

        return modelMapper.map(member, MemberDTO.class);
    }

    @Override
    public MemberDTO update(long id, MemberDTO memberDTO) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + id));

        existingMember.setName(memberDTO.getName());
        existingMember.setFamilyName(memberDTO.getFamilyName());
        existingMember.setNationality(memberDTO.getNationality());
        existingMember.setIdentityDocument(memberDTO.getIdentityDocument());
        existingMember.setIdentityNumber(memberDTO.getIdentityNumber());

        existingMember = memberRepository.save(existingMember);

        return modelMapper.map(existingMember, MemberDTO.class);
    }


    @Override
    public void delete(long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + id));
        memberRepository.delete(member);
    }


    //    HELPER METHODS

}
