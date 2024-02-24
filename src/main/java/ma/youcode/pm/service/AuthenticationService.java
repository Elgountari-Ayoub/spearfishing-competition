package ma.youcode.pm.service;

import ma.youcode.pm.dao.request.SignUpRequest;
import ma.youcode.pm.dao.request.SigninRequest;
import ma.youcode.pm.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
