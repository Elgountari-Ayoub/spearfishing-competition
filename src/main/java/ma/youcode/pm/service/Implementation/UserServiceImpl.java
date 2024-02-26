package ma.youcode.pm.service.Implementation;

import lombok.RequiredArgsConstructor;
import ma.youcode.pm.dto.MemberDTO;
import ma.youcode.pm.dto.UserDTO;
import ma.youcode.pm.model.Member;
import ma.youcode.pm.repository.UserRepository;
import ma.youcode.pm.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public Page<UserDTO> findUsers(Pageable pageable) {
        var users = userRepository.findAll(pageable);
        return users.map(user -> modelMapper.map(user, UserDTO.class));

    }

    @Override
    public UserDTO findById(int id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        return modelMapper.map(user, UserDTO.class);
    }
}
