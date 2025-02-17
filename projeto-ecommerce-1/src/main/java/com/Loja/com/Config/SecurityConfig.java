package com.Loja.com.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Loja.com.Utils.JwtFilterChain;

@Configuration
public class SecurityConfig {

	
	//Método de rotas urls
    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
               //Rotas públicas
            		.requestMatchers("/**").permitAll()
            		.requestMatchers("/js/**", "/css/**", "/img/**").permitAll()
            
            )
            
            .addFilterBefore(new JwtFilterChain(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
        
        
    }
    
    
    //Método de criptografia
    @Bean
     PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }

}