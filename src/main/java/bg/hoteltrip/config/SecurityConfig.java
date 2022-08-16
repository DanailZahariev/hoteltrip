package bg.hoteltrip.config;

import bg.hoteltrip.repository.UserRepository;
import bg.hoteltrip.service.HotelTripDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                authorizeRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                antMatchers("/users/login", "/users/register", "/", "/hotels/**", "/api/search/")
                .permitAll().
                antMatchers("/reservation/**").hasRole("USER").
                antMatchers("/users/**").hasRole("USER").
                antMatchers("/admin/**").hasRole("ADMIN").
                anyRequest().
                authenticated().
                and().
                formLogin().
                loginPage("/users/login").
                usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/").
                failureForwardUrl("/users/login-error").
                and().
                logout().
                logoutUrl("/users/logout").
                logoutSuccessUrl("/").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID");

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new HotelTripDetailsService(userRepository);
    }
}
