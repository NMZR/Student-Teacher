package com.nadia.studentsmgm.security;

import com.nadia.studentsmgm.repository.UserCredentialsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.SecureRandom;
import java.util.Arrays;

@Configuration //
@EnableWebSecurity //

public class SecurityConfig {
  private final UserCredentialsRepository userCredentialsRepository;
  private final JwtAuthinticationFilter jwtAuthinticationFilter;

   public SecurityConfig(UserCredentialsRepository userCredentialsRepository, JwtAuthinticationFilter jwtAuthinticationFilter) {
       this.userCredentialsRepository = userCredentialsRepository;
      this.jwtAuthinticationFilter = jwtAuthinticationFilter;
   }

    @Bean
    public SecurityFilterChain firterchain(HttpSecurity http) throws Exception {
       http
               .cors(Customizer.withDefaults())
               .csrf(csrf -> csrf.disable())
               .authorizeHttpRequests(ar -> ar.anyRequest().permitAll());
       return http.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();
        source.registerCorsConfiguration("/**",config);
        return source;
    }

        /*return http.httpBasic(Customizer.withDefaults()).authorizeHttpRequests(c ->c.anyRequest()
                .authenticated()).
                build();*/
//        http
//                .csrf(c -> c.disable()).cors(c -> c.disable())
//                .authorizeHttpRequests(htp -> htp.requestMatchers("api/v1/auth/login")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated())
//                .sessionManagement(sec -> sec.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider())
//                .addFilterBefore()
//
//                );
//
//    }
   @Bean
    public UserDetailsService userDetailsService(){
       /* UserDetails admins = User.builder().username("admin").password(passwordEncoder().encode("adminpassword")).roles("Admin").build();
        UserDetails user = User.builder().username("user").password(passwordEncoder().encode("userpassword")).roles("USER").build();
        return new InMemoryUserDetailsManager(admins,user);*/
        return username -> userCredentialsRepository.findByEmail(username).orElseThrow(()-> new EntityNotFoundException("User not found"));

    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean

    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder(10,new SecureRandom());
}
}
