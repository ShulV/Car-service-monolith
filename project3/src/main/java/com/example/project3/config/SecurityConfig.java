package com.example.project3.config;

import com.example.project3.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;


    @Autowired
    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                //кастомная форма аутентификации
                .authorizeRequests()
                .antMatchers("/repair-request/all").hasRole("ADMIN")
                .antMatchers("/", "/auth/login", "/auth/registration", "/error", "/assets/**").permitAll()
                .anyRequest().authenticated()

                .and()

                .formLogin().loginPage("/auth/login")

                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/", true)

                .failureUrl("/auth/login?error")
                .and()
                //разлогирование
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login")
                .and()
                .userDetailsService(jpaUserDetailsService)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
