package cgg.springboot.security.springbootsecurityexample.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails normalUser = User
         .withUserName("ajay")
         .password(passwordEncoder().encode("1234"))
         .roles("NORMAL")
         .build();

         UserDetails adminUser = User
                                .withUserName("ajay")
                                .password("1234")
                                .roles("ADMIN")
                                .build();
            return new InMemoryUserDetailsManager(normalUser,adminUser);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
         
                httpSecurity.csrf().disable()
                        .authorizeHttpRequests()
                        // .requestMatchers("/home/admin")
                        // .hasRole("ADMIN")
                        // .requestMatchers("/home/normal")
                        // .hasRole("NORMAL")
                        .requestMatchers("/home/public")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                        .and()
                        .formLogin();
           
            return httpSecurity.build();
    }

   
}
