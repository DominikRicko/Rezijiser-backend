package hr.foka.rezijiser.security.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.security.resource.LoginResource;

@Service
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonAuthenticationFilter.class);

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    private ThreadLocal<String> username;
    private ThreadLocal<String> password;

    public JsonAuthenticationFilter(
        AuthenticationManager authenticationManager,
        AuthenticationSuccessHandler authenticationSuccessHandler,
        AuthenticationFailureHandler authenticationFailureHandler){

        this.setAuthenticationManager(authenticationManager);
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;

        this.username = new ThreadLocal<>();
        this.password = new ThreadLocal<>();
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {

        if ("application/json".equals(request.getHeader("Content-Type"))) {
           return this.password.get();
        }else{
            return super.obtainPassword(request);
        }
    }

    @Override
    protected String obtainUsername(HttpServletRequest request){
        if ("application/json".equals(request.getHeader("Content-Type"))) {
            return username.get();
        }else{
            return super.obtainUsername(request);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

        Authentication authentication;

        if ("application/json".equals(request.getHeader("Content-Type"))) {
            try(ServletInputStream inputStream = request.getInputStream()){
                ObjectMapper mapper = new ObjectMapper();
                LoginResource loginRequest = mapper.readValue(inputStream, LoginResource.class);
                
                this.username.set(loginRequest.getUsername());
                this.password.set(loginRequest.getPassword());

                authentication = new UsernamePasswordAuthenticationToken(obtainUsername(request), obtainPassword(request));
                authentication = this.getAuthenticationManager().authenticate(authentication);
            } catch (Exception e) {
                LOGGER.error("Could not read JSON.", e);
                return super.attemptAuthentication(request, response);
            }
        } else {
            authentication = super.attemptAuthentication(request, response);
        }

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
    }

}
