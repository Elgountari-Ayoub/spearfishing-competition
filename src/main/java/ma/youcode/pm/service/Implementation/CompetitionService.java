package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.dto.CompetitionDTO;
import ma.youcode.pm.exception.CompetitionExistInSameDayException;
import ma.youcode.pm.exception.CompetitionNotFoundException;
import ma.youcode.pm.exception.DuplicateCompetitionException;
import ma.youcode.pm.model.Competition;
import ma.youcode.pm.repository.ICompetitionRepository;
import ma.youcode.pm.service.ICompetitionService;
import ma.youcode.pm.util.CompetitionCodeGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService implements ICompetitionService {
    ICompetitionRepository competitionRepository;
    CompetitionCodeGenerator competitionCodeGenerator;
    private ModelMapper modelMapper;

    public CompetitionService(ICompetitionRepository competitionRepository, ModelMapper modelMapper, CompetitionCodeGenerator competitionCodeGenerator) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
        this.competitionCodeGenerator = competitionCodeGenerator;
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
        competitionDTO.setCode(competitionCodeGenerator.generate(competitionDTO.getLocation(), competitionDTO.getDate()));
        if (competitionRepository.existsByCode(competitionDTO.getCode())) {
            throw new DuplicateCompetitionException("Competition with code " + competitionDTO.getCode() + " already exists.");
        }else if(competitionRepository.existsByDate(competitionDTO.getDate())){
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

    //    HELPER METHODS

}
