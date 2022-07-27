package com.insight.cdp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.insight.cdp.security.jwt.AuthEntryPointJwt;
import com.insight.cdp.security.jwt.AuthTokenFilter;
import com.insight.cdp.security.jwt.JwtUtils;
import com.insight.cdp.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsService;
	private AuthEntryPointJwt unauthorizedHandler;
	private JwtUtils jwtUtils;

	public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AuthEntryPointJwt unauthorizedHandler, JwtUtils jwtUtils) {
		this.userDetailsService = userDetailsService;
		this.unauthorizedHandler = unauthorizedHandler;
		this.jwtUtils=jwtUtils;
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter(jwtUtils,userDetailsService);
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()

				.antMatchers("/api/auth/**").permitAll()

				.antMatchers("/create-category").hasAuthority("ROLE_ADMIN")
				.antMatchers("/get-all-category").permitAll()
				.antMatchers("/get-category-by-id/{id}").permitAll()
				.antMatchers("/update-category").hasAuthority("ROLE_ADMIN")
				.antMatchers("/delete-category-by-id/{id}").hasAuthority("ROLE_ADMIN")

				.antMatchers("/create-expense").hasAuthority("ROLE_USER")
				.antMatchers("/get-all-expense").hasAuthority("ROLE_ADMIN")
				.antMatchers("/get-expense-by-id/{id}").hasAuthority("ROLE_USER")
				.antMatchers("/update-expense").hasAuthority("ROLE_USER")
				.antMatchers("/delete-expense-by-id/{id}").hasAuthority("ROLE_USER")
				.antMatchers("/get-expense-by-user/{userId}").hasAuthority("ROLE_USER")
				
				.antMatchers("/get-all-users").hasAuthority("ROLE_ADMIN")
				.antMatchers("/delete-user-by-id/{id}").hasAuthority("ROLE_ADMIN")
				.antMatchers("/make-admin/{userId}").permitAll()

				.antMatchers("/v2/api-docs", "/v3/api-docs/**", "/configuration/ui", "/swagger-resources/**",
						"/configuration/security", "/swagger-ui/**", "/webjars/**")
				.permitAll().anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
