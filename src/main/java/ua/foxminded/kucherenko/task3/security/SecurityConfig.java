package ua.foxminded.kucherenko.task3.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
    @Autowired
    private CustomUserDetailService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
        LOGGER.debug("Authenticated user: " + SecurityContextHolder.getContext().getAuthentication());
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(rQ -> rQ.requestMatchers("/home").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/**").hasAuthority("SUPERADMIN")
                        .requestMatchers("/administrators").hasAnyAuthority("ADMIN", "SUPERADMIN")
                        .requestMatchers("/courses/**", "/groups/**", "/students/**").hasAnyAuthority("STUDENT", "TEACHER")
                        .requestMatchers("/teacher",  "/students").hasAuthority("TEACHER")
                        .anyRequest().authenticated())
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler((request, response, authentication) -> {
                            LOGGER.debug("Authenticated user: " + SecurityContextHolder.getContext().getAuthentication());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            response.sendRedirect("/home");
                        })
                        .failureUrl("/login?error")
                        .permitAll())
                .logout(logoutConfigurer ->
                        logoutConfigurer.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());


        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }
}
