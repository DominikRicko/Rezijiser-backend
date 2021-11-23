package hr.foka.rezijiser.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.httpBasic();
		http.authorizeRequests().antMatchers("/login", "/perform_login", "/register", "/perform_register", "/reset_password").permitAll();
		http.authorizeRequests().antMatchers("/js/**", "/images/**", "/css/**").permitAll();
		http.authorizeRequests().antMatchers("/e/**").authenticated();
		http.authorizeRequests().antMatchers("/i/**").permitAll();
		http.authorizeRequests().antMatchers("/key/**").permitAll();

		http.authorizeRequests().antMatchers("/users").hasAuthority("READ_PROFILES");
		http.authorizeRequests().antMatchers("/groups").hasAuthority("READ_GROUPS");
		http.authorizeRequests().antMatchers("/privileges").hasAuthority("READ_PRIVILEGES");

		http.authorizeRequests().anyRequest().authenticated();

		http.formLogin().loginPage("/login").loginProcessingUrl("/perform_login").and().logout().logoutUrl("/perform_logout").deleteCookies("JSESSIONID");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}