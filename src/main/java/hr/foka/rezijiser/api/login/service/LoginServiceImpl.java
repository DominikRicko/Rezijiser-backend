package hr.foka.rezijiser.api.login.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.api.login.resource.LoginResource;
import hr.foka.rezijiser.api.login.resource.LoginResponseResource;
import hr.foka.rezijiser.security.service.JwtTokenUtil;

@Service
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    public LoginServiceImpl(
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public ResponseEntity<?> authenticate(LoginResource resource) {

        AbstractAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(resource.getEmail(),
                resource.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authToken);
        UserDetails user = userDetailsService.loadUserByUsername((String) authenticate.getPrincipal());

        String jwtToken = jwtTokenUtil.generateToken(user);
        LoginResponseResource response = new LoginResponseResource();
        response.setToken(jwtToken);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .body(response);
    }

}