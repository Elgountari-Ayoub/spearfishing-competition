package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.dto.HuntingDTO;
import ma.youcode.pm.dto.MemberDTO;
import ma.youcode.pm.dto.RankingDTO;
import ma.youcode.pm.exception.*;
import ma.youcode.pm.model.*;
import ma.youcode.pm.repository.ICompetitionRepository;
import ma.youcode.pm.repository.IHuntingRepository;
import ma.youcode.pm.repository.IMemberRepository;
import ma.youcode.pm.repository.IRankingRepository;
import ma.youcode.pm.service.IHuntingService;
import ma.youcode.pm.service.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class HuntingService implements IHuntingService {


    IHuntingRepository huntingRepository;
    MemberService memberService;
    CompetitionService competitionService;
    RankingService rankingService;
    FishService fishService;


    private final ModelMapper modelMapper;

    public HuntingService(
            ModelMapper modelMapper, IHuntingRepository huntingRepository,
            MemberService memberService, CompetitionService competitionService, RankingService rankingService, FishService fishService
    ) {
        this.modelMapper = modelMapper;
        this.competitionService = competitionService;
        this.memberService = memberService;
        this.huntingRepository = huntingRepository;
        this.rankingService = rankingService;
        this.fishService = fishService;
    }

    @Override
    public HuntingDTO findById(long id) {
        Hunting hunting = huntingRepository.findById(id)
                .orElseThrow(() -> new RankingNotFoundException("Hunting not found with id: " + id));
        return modelMapper.map(hunting, HuntingDTO.class);
    }

    @Override
    public Page<HuntingDTO> finAll(Pageable pageable) {
        Page<Hunting> huntings = huntingRepository.findAll(pageable);
        return huntings.map(hunting -> modelMapper.map(hunting, HuntingDTO.class));
    }

    @Override
    public HuntingDTO save(HuntingDTO huntingDTO) {
        RankingId rankingId = new RankingId();
        rankingId.setCompetitionCode(huntingDTO.getCompetition().getCode());
        rankingId.setMemberNum(huntingDTO.getMember().getNum());

        RankingDTO rankingDTO = rankingService.findById(rankingId);

        LocalDate today = LocalDate.now();
        LocalDate competitionDate = rankingDTO.getCompetition().getDate();
        if (!today.isEqual(competitionDate)) {
            throw new RegistrationException("Cannot insert hunting. Competition is not playing today.");
        }

        fishService.findById(huntingDTO.getFish().getId());

        Hunting hunting = huntingRepository.findByCompetitionAndMemberAndFish(huntingDTO.getCompetition(), huntingDTO.getMember(), huntingDTO.getFish());
        if (hunting != null) {
            huntingDTO.setNumberOfFish(hunting.getNumberOfFish() + huntingDTO.getNumberOfFish());
        }
        hunting = modelMapper.map(huntingDTO, Hunting.class);
        huntingRepository.save(hunting);

        return modelMapper.map(hunting, HuntingDTO.class);
    }

    @Override
    public HuntingDTO update(long id, HuntingDTO huntingDTO) {
        RankingId rankingId = new RankingId();
        rankingId.setCompetitionCode(huntingDTO.getCompetition().getCode());
        rankingId.setMemberNum(huntingDTO.getMember().getNum());

        RankingDTO rankingDTO = rankingService.findById(rankingId);

        LocalDate today = LocalDate.now();
        LocalDate competitionDate = rankingDTO.getCompetition().getDate();
        if (!today.isEqual(competitionDate)) {
            throw new RegistrationException("Cannot insert hunting. Competition is not playing today.");
        }

        fishService.findById(huntingDTO.getFish().getId());

        Hunting hunting = huntingRepository.findByCompetitionAndMemberAndFish(huntingDTO.getCompetition(), huntingDTO.getMember(), huntingDTO.getFish());
        if (hunting != null) {
            huntingDTO.setNumberOfFish(hunting.getNumberOfFish() + huntingDTO.getNumberOfFish());
            hunting = modelMapper.map(huntingDTO, Hunting.class);
            huntingRepository.save(hunting);
        } else {
            throw new HuntingNotFoundException("Hunting not found!");
        }
        return modelMapper.map(hunting, HuntingDTO.class);
    }


    @Override
    public void delete(long id) {
        Hunting hunting = huntingRepository.findById(id)
                .orElseThrow(()->  new HuntingNotFoundException("Hunting not found!"));
        huntingRepository.delete(hunting);
    }

}
