package ma.youcode.pm.service.Implementation;


import lombok.RequiredArgsConstructor;
import ma.youcode.pm.dao.request.SignUpRequest;
import ma.youcode.pm.dao.request.SigninRequest;
import ma.youcode.pm.dao.response.JwtAuthenticationResponse;
import ma.youcode.pm.model.User;
import ma.youcode.pm.repository.UserRepository;
import ma.youcode.pm.service.AuthenticationService;
import ma.youcode.pm.service.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
//    private final IJobSeekerRepository jobSeekerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().name(request.getName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole()).build();
//        JobSeeker jobSeeker = modelMapper.map(user, JobSeeker.class);
//        jobSeekerRepository.save(jobSeeker);

        var jwt = jwtService.generateToken(user);
        System.out.println(jwt);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        System.out.println(jwt);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
