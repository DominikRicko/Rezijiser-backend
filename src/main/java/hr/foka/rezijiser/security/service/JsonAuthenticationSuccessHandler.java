package hr.foka.rezijiser.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

@Service
@Qualifier("actualHandler")
public class JsonAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonAuthenticationSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

                response.getWriter().write("Login successful.");
                response.setStatus(HttpServletResponse.SC_OK);

                LOGGER.error("Request: {}", request);
                LOGGER.error("Response: {}", response);
                LOGGER.error("Authentication: {}", authentication);

                
    }

}