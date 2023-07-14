package com.compras.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.compras.security.jwt.JwtAuthenticationFilter;
import com.compras.security.jwt.JwtAuthorizationFilter;
import com.compras.security.jwt.JwtUtils;
import com.compras.service.impl.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtAuthorizationFilter jwtAuthorizationFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
		
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		
		return http
				.cors(cors -> {})
				.csrf(c -> c.disable())
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/login").permitAll();
					auth.requestMatchers("/logout").permitAll();
					auth.requestMatchers("/usuario/nuevo").permitAll();
					auth.requestMatchers("/categoria/listar").permitAll();
					auth.requestMatchers("/producto/listar/**").permitAll();
					auth.anyRequest().authenticated();
				})
				.sessionManagement(session ->{
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
					
				})
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	PasswordEncoder passworEncoder() {
		return new BCryptPasswordEncoder();
	}	
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
		AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
		return authBuilder.build();
	}
}
