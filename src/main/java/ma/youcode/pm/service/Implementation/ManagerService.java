package ma.youcode.pm.service.Implementation;

import ma.youcode.pm.dto.ManagerDTO;
import ma.youcode.pm.dto.MemberRankingsResponse;
import ma.youcode.pm.exception.DuplicateMemberException;
import ma.youcode.pm.exception.MemberNotFoundException;
import ma.youcode.pm.model.Manager;
import ma.youcode.pm.model.Ranking;
import ma.youcode.pm.repository.IManagerRepository;
import ma.youcode.pm.repository.IManagerRepository;
import ma.youcode.pm.repository.IRankingRepository;
import ma.youcode.pm.repository.UserRepository;
import ma.youcode.pm.service.IManagerService;
import ma.youcode.pm.service.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManagerService implements IManagerService {
    @Autowired
    IManagerRepository managerRepository;
    @Autowired
    IRankingRepository rankingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ManagerDTO findById(long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Manager not found with id: " + id));

        return modelMapper.map(manager, ManagerDTO.class);
    }

    @Override
    public boolean validate(long id) {
        var user = userRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        user.setIsAccepted(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean refuse(long id) {
        var user = userRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        user.setIsAccepted(false);
        userRepository.save(user);
        return true;
    }

}