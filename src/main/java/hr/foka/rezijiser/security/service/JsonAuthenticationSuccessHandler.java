package hr.foka.rezijiser.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

@Service
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                response.reset();
                response.getWriter().write("Login successful.");
                response.setStatus(200);
                

                LOGGER.info("Request: {}", request);
                LOGGER.info("Response: {}", response);
                LOGGER.info("Authentication: {}", authentication);
    }

}