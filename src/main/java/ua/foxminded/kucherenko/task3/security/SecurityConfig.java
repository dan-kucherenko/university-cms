package ua.foxminded.kucherenko.task3.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(rQ ->
                        rQ.requestMatchers("/home").permitAll()
                                .requestMatchers("/register/**").permitAll()
                                .requestMatchers("/administrators/manage-roles").hasAnyAuthority("ADMIN", "SUPERADMIN")
                                .requestMatchers("/administrators").hasAnyAuthority("ADMIN", "SUPERADMIN")
                                .requestMatchers("/departments").hasAnyAuthority("ADMIN", "SUPERADMIN", "TEACHER")
                                .requestMatchers("/teachers", "/courses", "/groups", "/students").hasAnyAuthority("ADMIN", "SUPERADMIN", "STUDENT", "TEACHER")
                                .anyRequest().authenticated())
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .permitAll()
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            response.sendRedirect("/home");
                        })
                        .failureUrl("/login-error")
                        .permitAll())
                .logout(logoutConfigurer ->
                        logoutConfigurer.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());


        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
