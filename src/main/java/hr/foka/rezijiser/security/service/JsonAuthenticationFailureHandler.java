package hr.foka.rezijiser.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

                response.getWriter().write("Login failed.");
                response.setStatus(401);

                LOGGER.info("Request: {}", request);
                LOGGER.info("Response: {}", response);
                LOGGER.info("Exception: {}", exception);
    }

}
