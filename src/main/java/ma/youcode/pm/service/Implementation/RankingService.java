package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.dto.RankingDTO;
import ma.youcode.pm.exception.*;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Member;
import ma.youcode.pm.model.Ranking;
import ma.youcode.pm.model.RankingId;
import ma.youcode.pm.repository.ICompetitionRepository;
import ma.youcode.pm.repository.IMemberRepository;
import ma.youcode.pm.repository.IRankingRepository;
import ma.youcode.pm.service.IRankingService;
import ma.youcode.pm.util.CompetitionCodeGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class RankingService implements IRankingService {
    ICompetitionRepository competitionRepository;
    IMemberRepository memberRepository;
    IRankingRepository rankingRepository;
    private final ModelMapper modelMapper;

    public RankingService(
            ICompetitionRepository competitionRepository, ModelMapper modelMapper, IMemberRepository memberRepository,
            IRankingRepository rankingRepository) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
        this.memberRepository = memberRepository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public RankingDTO findById(RankingId rankingId) {
        Ranking ranking = rankingRepository.findById(rankingId)
                .orElseThrow(() -> new CompetitionNotFoundException("Ranking not found with competition code: " + rankingId.getCompetitionCode()));
        return modelMapper.map(ranking, RankingDTO.class);
    }

    @Override
    public Page<RankingDTO> finAll(Pageable pageable) {
        Page<Competition> competitions = competitionRepository.findAll(pageable);
        return competitions.map(competition -> modelMapper.map(competition, RankingDTO.class));
    }

    @Override
    public RankingDTO save(RankingDTO rankingDTO) {
        Member member = memberRepository.findById(rankingDTO.getId().getMemberNum())
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        Competition competition = competitionRepository.findById(rankingDTO.getId().getCompetitionCode())
                .orElseThrow(() -> new CompetitionNotFoundException("Competition not found"));

        // Check if registration is allowed (24 hours before the start)
        LocalDate today = LocalDate.now();
        LocalDate competitionStartDate = competition.getDate();
        long daysDifference = ChronoUnit.DAYS.between(today, competitionStartDate);

        if (daysDifference <= 1) {
            throw new RegistrationException("Registration closed. It's less than 24 hours before the competition.");
        }

        // Create ranking entry for the member in the competition
        if (rankingRepository.existsRankingByCompetitionAndMember(competition, member)) {
            throw new RegistrationException("Member is already registered for this competition.");
        }

        Ranking ranking = modelMapper.map(rankingDTO, Ranking.class);
        ranking.setMember(member);
        ranking.setCompetition(competition);

        ranking = rankingRepository.save(ranking);
        return modelMapper.map(ranking, RankingDTO.class);

    }

    @Override
    public RankingDTO update(RankingId rankingId, RankingDTO rankingDTO) {
        Competition competition = competitionRepository.findById(rankingId.getCompetitionCode())
                .orElseThrow(() -> new CompetitionNotFoundException("Competition not found with code: " + rankingId.getCompetitionCode()));

        Member member = memberRepository.findById(rankingId.getMemberNum())
                .orElseThrow(() -> new CompetitionNotFoundException("Member not found with num: " + rankingId.getMemberNum()));

        // Create ranking entry for the member in the competition
        if (rankingRepository.existsRankingByCompetitionAndMember(competition, member)) {
            throw new RegistrationException("Member is already registered for this competition.");
        }

        Ranking ranking = modelMapper.map(rankingDTO, Ranking.class);
        ranking.setMember(member);
        ranking.setCompetition(competition);

        ranking = rankingRepository.save(ranking);
        return modelMapper.map(ranking, RankingDTO.class);

    }


    @Override
    public void delete(RankingId rankingId) {
        Competition competition = competitionRepository.findById(rankingId.getCompetitionCode())
                .orElseThrow(() -> new CompetitionNotFoundException("Competition not found with code: " + rankingId.getCompetitionCode()));
        competitionRepository.delete(competition);
    }

}
