package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.dto.FishDTO;
import ma.youcode.pm.exception.DuplicateFishException;
import ma.youcode.pm.exception.FishNotFoundException;
import ma.youcode.pm.model.*;
import ma.youcode.pm.repository.IFishRepository;
import ma.youcode.pm.service.IFishService;
import ma.youcode.pm.service.ILevelService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FishService implements IFishService {
    ILevelService levelService;
    IFishRepository fishRepository;
    private final ModelMapper modelMapper;

    public FishService(ILevelService levelService,
                       IFishRepository fishRepository,
                       ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.fishRepository = fishRepository;
        this.levelService = levelService;
    }

    @Override
    public FishDTO findById(long id) {
        Fish fish = fishRepository.findById(id)
                .orElseThrow(() -> new FishNotFoundException("Fish not found with id: " + id));
        return modelMapper.map(fish, FishDTO.class);
    }

    @Override
    public List<FishDTO> searchByName(String name) {
        List<Fish> matchingFishes = fishRepository.findByNameContainingIgnoreCase(name);
        return matchingFishes.stream()
                .map(fish -> modelMapper.map(fish, FishDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<FishDTO> findAll(Pageable pageable) {
        Page<Fish> fishs = fishRepository.findAll(pageable);
        return fishs.map(fish -> modelMapper.map(fish, FishDTO.class));
    }

    @Override
    public FishDTO save(FishDTO fishDTO) {
        if (fishRepository.existsByName(fishDTO.getName())) {
            throw new DuplicateFishException("Fish with name " + fishDTO.getName() + " already exists.");
        }

        Fish fish = modelMapper.map(fishDTO, Fish.class);
        fish = fishRepository.save(fish);
        return modelMapper.map(fish, FishDTO.class);

    }

    @Override
    public FishDTO update(long id, FishDTO fishDTO) {
        Fish fish = fishRepository.findById(id)
                .orElseThrow(() -> new FishNotFoundException("Fish not found with code: " + id));
        fish = fishRepository.save(fish);
        return modelMapper.map(fish, FishDTO.class);
    }

    @Override
    public void delete(Long id) {
        Fish fish = fishRepository.findById(id)
                .orElseThrow(() -> new FishNotFoundException("Fish not found with code: " + id));
        fishRepository.delete(fish);
    }

}
