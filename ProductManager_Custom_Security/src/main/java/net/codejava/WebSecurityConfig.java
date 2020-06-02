package net.codejava;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProv = new DaoAuthenticationProvider();
		authProv.setUserDetailsService(userDetailsService());
		authProv.setPasswordEncoder(passwordEncoder());

		return authProv;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/delete/**").hasAnyAuthority("ADMIN")
		.antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
		.anyRequest().authenticated() 
		.and()
		.formLogin()
			.permitAll()
		.and()
		.logout()
			.permitAll()
			.logoutUrl("/do_logout")
			.logoutSuccessUrl("/logout_success")
			.and()
			.exceptionHandling().accessDeniedPage("/403");

	}
}
