package fi.haagahelia.bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests( authorize -> authorize // /index endpointti konfiguroitu niin että
                .requestMatchers("/index", "/login").permitAll() // authentisointia ei tarvita
                .anyRequest().authenticated() // All other paths must be authenticate
                )                               // If you don't give loginPage
            .formLogin( formlogin -> formlogin  // our application will use the
                .loginPage("/login")            // spring boot default login page
                .defaultSuccessUrl("/booklist", true)   // <-- Tells where to go after
                .permitAll()                        // successful login
                )
            .logout( logout -> logout
                .permitAll()
                );
                return http.build();
    }

   /*  @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder passwordEncoder) {
        UserDetails user = User.builder() // withDefaultPasswordEncoder() ilman bcrypt encoderia
                .username("user")
                .password(passwordEncoder.encode("password")) // bcrypt lisäsi -> passwordEncoder.encode
                .roles("USER")
                .build();
                // List<UserDetails> users = new ArrayList<UserDetails>();
                // users.add(user); 

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
 */
}
