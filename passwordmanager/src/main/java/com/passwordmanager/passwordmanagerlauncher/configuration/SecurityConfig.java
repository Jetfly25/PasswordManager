package com.passwordmanager.passwordmanagerlauncher.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/login", "/home", "/register", "/styles.css", "/h2-console/**").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/homepage", true)
                .permitAll()
            )
            .csrf((csrf) -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )

			// This will be removed once completed testing
			.headers((headers) -> headers
                .frameOptions().sameOrigin()
            );

		return http.build();
	}
    
}
