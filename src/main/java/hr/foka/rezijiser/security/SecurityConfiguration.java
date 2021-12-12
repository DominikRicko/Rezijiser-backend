package hr.foka.rezijiser.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private AuthenticationManager authenticationManager;
	private UsernamePasswordAuthenticationFilter authenticationFilter;
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	private AuthenticationFailureHandler authenticationFailureHandler;

	public SecurityConfiguration(
		AuthenticationManager authenticationManager, 
		UsernamePasswordAuthenticationFilter authenticationFilter,
		AuthenticationFailureHandler authenticationFailureHandler,
		AuthenticationSuccessHandler authenticationSuccessHandler){
		this.authenticationManager = authenticationManager;
		this.authenticationFilter = authenticationFilter;
		this.authenticationFailureHandler = authenticationFailureHandler;
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/login", "/register", "/reset_password", "/js/**", "/images/**", "/css/**", "/i/**", "/favicon.ico", "/", "/index");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.httpBasic();
		http.authorizeRequests().antMatchers("/login", "/register", "/reset_password").permitAll();
		http.authorizeRequests().antMatchers("/js/**", "/images/**", "/css/**").permitAll();
		http.authorizeRequests().antMatchers("/e/**").authenticated();
		http.authorizeRequests().antMatchers("/i/**").permitAll();

		http.formLogin().successHandler(this.authenticationSuccessHandler).failureHandler(this.authenticationFailureHandler);
		http.authenticationManager(this.authenticationManager);
		http.addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}

}