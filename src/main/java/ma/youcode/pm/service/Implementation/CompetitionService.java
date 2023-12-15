package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.dto.CompetitionDTO;
import ma.youcode.pm.dto.RankingDTO;
import ma.youcode.pm.exception.*;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.model.Member;
import ma.youcode.pm.model.Ranking;
import ma.youcode.pm.model.RankingId;
import ma.youcode.pm.repository.ICompetitionRepository;
import ma.youcode.pm.repository.IMemberRepository;
import ma.youcode.pm.repository.IRankingRepository;
import ma.youcode.pm.service.ICompetitionService;
import ma.youcode.pm.util.CompetitionCodeGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionService implements ICompetitionService {
    ICompetitionRepository competitionRepository;
    IMemberRepository memberRepository;
    IRankingRepository rankingRepository;
    CompetitionCodeGenerator competitionCodeGenerator;
    private final ModelMapper modelMapper;

    public CompetitionService(
            ICompetitionRepository competitionRepository, ModelMapper modelMapper,
            CompetitionCodeGenerator competitionCodeGenerator, IMemberRepository memberRepository,
            IRankingRepository rankingRepository) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
        this.competitionCodeGenerator = competitionCodeGenerator;
        this.memberRepository = memberRepository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public CompetitionDTO findByCode(String code) {
        Competition competition = competitionRepository.findById(code)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition not found with code: " + code));
        return modelMapper.map(competition, CompetitionDTO.class);
    }

    @Override
    public Page<CompetitionDTO> finAll(Pageable pageable) {
        Page<Competition> competitions = competitionRepository.findAll(pageable);
        return competitions.map(competition -> modelMapper.map(competition, CompetitionDTO.class));
    }

    @Override
    public List<CompetitionDTO> findPassedCompetitions() {
        LocalDate currentDate = LocalDate.now();
        List<Competition> competitions = competitionRepository.findByDateLessThan(currentDate);
        return competitions.stream().map(competition -> modelMapper.map(competition, CompetitionDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CompetitionDTO findTodayCompetition() {
        LocalDate currentDate = LocalDate.now();
        Competition competition = competitionRepository.findByDateEquals(currentDate);
        if (competition == null){
            return new CompetitionDTO();
        }
        return modelMapper.map(competition, CompetitionDTO.class);
    }

    @Override
    public List<CompetitionDTO> findUpcomingCompetitions() {
        LocalDate currentDate = LocalDate.now();
        List<Competition> competitions = competitionRepository.findByDateGreaterThan(currentDate);
        return competitions.stream().map(competition -> modelMapper.map(competition, CompetitionDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CompetitionDTO save(CompetitionDTO competitionDTO) {
        competitionDTO.setCode(competitionCodeGenerator.generate(competitionDTO.getLocation(), competitionDTO.getDate()));
        if (competitionRepository.existsByCode(competitionDTO.getCode())) {
            throw new DuplicateCompetitionException("Competition with code " + competitionDTO.getCode() + " already exists.");
        } else if (competitionRepository.existsByDate(competitionDTO.getDate())) {
            throw new CompetitionExistInSameDayException("A competition already exists on the same day");
        }
        Competition competition = modelMapper.map(competitionDTO, Competition.class);
        competition = competitionRepository.save(competition);
        return modelMapper.map(competition, CompetitionDTO.class);
    }

    @Override
    public CompetitionDTO update(String code, CompetitionDTO competitionDTO) {
        Competition existingCompetition = competitionRepository.findById(code)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition not found with code: " + code));

        existingCompetition.setCode(code);
        existingCompetition.setDate(competitionDTO.getDate());
        existingCompetition.setStartTime(competitionDTO.getStartTime());
        existingCompetition.setEndTime(competitionDTO.getEndTime());
        existingCompetition.setNumberOfParticipants(competitionDTO.getNumberOfParticipants());
        existingCompetition.setLocation(competitionDTO.getLocation());
        existingCompetition.setAmount(competitionDTO.getAmount());

        existingCompetition = competitionRepository.save(existingCompetition);

        return modelMapper.map(existingCompetition, CompetitionDTO.class);
    }


    @Override
    public void delete(String code) {
        Competition competition = competitionRepository.findById(code)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition not found with code: " + code));
        competitionRepository.delete(competition);
    }

    @Override
    public CompetitionDTO join(RankingDTO rankingDTO) {
        Member member = memberRepository.findById(rankingDTO.getId().getMemberNum())
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));

        Competition competition = competitionRepository.findById(rankingDTO.getId().getCompetitionCode())
                .orElseThrow(() -> new CompetitionNotFoundException("Competition not found"));

        LocalDate today = LocalDate.now();
        LocalDate competitionStartDate = competition.getDate();
        long daysDifference = ChronoUnit.DAYS.between(today, competitionStartDate);

        if (daysDifference <= 1) {
            throw new RegistrationException("Registration closed. It's less than 24 hours before the competition.");
        }

        if (rankingRepository.existsRankingByCompetitionAndMember(competition, member)) {
            throw new RegistrationException("Member is already registered for this competition.");
        }

        Ranking ranking = modelMapper.map(rankingDTO, Ranking.class);
        ranking.setMember(member);
        ranking.setCompetition(competition);

        rankingRepository.save(ranking);
        return modelMapper.map(competition, CompetitionDTO.class);

    }

    //    HELPER METHODS

}
