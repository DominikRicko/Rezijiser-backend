package hr.foka.rezijiser.security.service;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import hr.foka.rezijiser.security.resource.LoginResource;

@Service
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonAuthenticationFilter.class);

    private ThreadLocal<String> username;
    private ThreadLocal<String> password;

    public JsonAuthenticationFilter(AuthenticationManager authenticationManager){
        this.setAuthenticationManager(authenticationManager);
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

}
