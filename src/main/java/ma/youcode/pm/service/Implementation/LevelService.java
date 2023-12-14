package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.dto.LevelDTO;
import ma.youcode.pm.exception.LevelNotFoundException;
import ma.youcode.pm.model.*;
import ma.youcode.pm.repository.ILevelRepository;
import ma.youcode.pm.service.ILevelService;
import ma.youcode.pm.util.CompetitionCodeGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class LevelService implements ILevelService {
    ILevelRepository levelRepository;
    private final ModelMapper modelMapper;

    public LevelService(ILevelRepository levelRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.levelRepository = levelRepository;
    }

    @Override
    public LevelDTO findById(Long code) {
        Level level = levelRepository.findById(code)
                .orElseThrow(() -> new LevelNotFoundException("Level not found with code: " + code));

        return modelMapper.map(level, LevelDTO.class);
    }

    @Override
    public Page<LevelDTO> finAll(Pageable pageable) {
        Page<Level> levels = levelRepository.findAll(pageable);
        return levels.map(level -> modelMapper.map(level, LevelDTO.class));
    }

    @Override
    public LevelDTO save(LevelDTO levelDTO) {
        Level level = modelMapper.map(levelDTO, Level.class);
        levelRepository.save(level);
        return levelDTO;
    }

    @Override
    public LevelDTO update(Long code, LevelDTO levelDTO) {
        return null;
    }

    @Override

    public void delete(Long code) {
        Level level = levelRepository.findById(code)
                .orElseThrow(() -> new LevelNotFoundException("Level not found with code: " + code));
        levelRepository.delete(level);
    }

}
