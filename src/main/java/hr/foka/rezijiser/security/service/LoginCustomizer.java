package hr.foka.rezijiser.security.service;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

@Service
public class LoginCustomizer implements Customizer<FormLoginConfigurer<HttpSecurity>>{

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginCustomizer.class);

    private AuthenticationSuccessHandler authenticationSuccessHandler;
	private AuthenticationFailureHandler authenticationFailureHandler;
    private ObjectPostProcessor<Object> objectPostProcessor;
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    public LoginCustomizer(
        AuthenticationFailureHandler failureHandler,
        AuthenticationSuccessHandler successHandler,
        ObjectPostProcessor<Object> objectPostProcessor,
        AuthenticationManagerBuilder authenticationBuilder
    ){
        this.authenticationFailureHandler = failureHandler;
        this.authenticationSuccessHandler = successHandler;
        this.objectPostProcessor = objectPostProcessor;
        this.authenticationManagerBuilder = authenticationBuilder;
    }

    @Override
    public void customize(FormLoginConfigurer<HttpSecurity> t) {

        HttpSecurity security = new HttpSecurity(objectPostProcessor, authenticationManagerBuilder, new HashMap<Class<?>, Object>());

        t.disable();
        try{
            t.init(security);
        }
        catch(Exception e){
            LOGGER.error("", e);
        }
        t.successHandler(this.authenticationSuccessHandler);
        t.failureHandler(this.authenticationFailureHandler);
    }
    
}
