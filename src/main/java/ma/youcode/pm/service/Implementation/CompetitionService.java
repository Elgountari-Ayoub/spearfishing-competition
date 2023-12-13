package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.dto.CompetitionDTO;
import ma.youcode.pm.exception.CompetitionNotFoundException;
import ma.youcode.pm.exception.DuplicateCompetitionException;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.repository.ICompetitionRepository;
import ma.youcode.pm.service.ICompetitionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService implements ICompetitionService {
    ICompetitionRepository competitionRepository;

    private ModelMapper modelMapper;

    public CompetitionService(ICompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CompetitionDTO finByCode(String code) {
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
    public CompetitionDTO save(CompetitionDTO competitionDTO) {
        if (competitionRepository.existsByCode(competitionDTO.getCode())) {
            throw new DuplicateCompetitionException("Competition with code " + competitionDTO.getCode() + " already exists.");
        }
        Competition competition = modelMapper.map(competitionDTO, Competition.class);
        competition = competitionRepository.save(competition);
        return modelMapper.map(competition, CompetitionDTO.class);
    }

    @Override
    public CompetitionDTO update(String code, CompetitionDTO competitionDTO) {
        Competition existingCompetition = competitionRepository.findById(code)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition not found with code: " + code));

        existingCompetition.setCode(competitionDTO.getCode());
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

    //    HELPER METHODS

}
