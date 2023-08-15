package com.example.springsecurity.config;

import com.example.springsecurity.model.enums.UserRoleEnum;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.AppUserDetailService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailService(userRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()
                .requestMatchers("/", "users/login", "users/register")
                .permitAll()
                .requestMatchers("/pages/moderators")
                .hasRole(UserRoleEnum.MODERATOR.name())
                .requestMatchers("/pages/admins")
                .hasRole(UserRoleEnum.ADMIN.name())
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .defaultSuccessUrl("/")
                .failureForwardUrl("/users/login-error")

                .and()
                .logout()
                .logoutUrl("/users/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http.build();
    }
}
