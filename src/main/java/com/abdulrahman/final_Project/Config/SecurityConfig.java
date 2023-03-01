package com.abdulrahman.final_Project.Config;

import com.abdulrahman.final_Project.MyUser.MyUserDetailedService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailedService myUserDetailedService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailedService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v3/auth/register/**").permitAll()
                .requestMatchers("/api/v3/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/api/v3/order/admin/**").hasAuthority("ADMIN")
//                .requestMatchers("/api/v3/advisor/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/api/v3/advisor/**").hasAuthority("ADVISOR")
                .requestMatchers("/api/v3/start-up/**").hasAuthority("STARTUP")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v3/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }

}
