package com.Loja.com.Utils;
import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilterChain extends OncePerRequestFilter {
	
	//Chave secreta
	private static final String SECRET_KEY = "secreta123";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
		

		//Verifica o cabeçãho e o role da requisição
			String token= request.getHeader(SECRET_KEY);
			if(token== null ||  token.startsWith("Bearer")) {
				filterChain.doFilter(request, response);
				return;
			}
			
			token= token.replace("Bearer", "");
			
			try {
				String email = JWT.require(Algorithm.HMAC256(SECRET_KEY))
					.build()
					.verify(token)
					.getSubject();
				
				String role= JWT.require(Algorithm.HMAC256(SECRET_KEY))
						.build()
						.verify(token)
						.getClaim("role").asString();
				
				
				 SecurityContextHolder.getContext().setAuthentication(
			                new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList())
			            );

				
			}catch (JWTVerificationException ex) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            return;
		
		
		
			}
			filterChain.doFilter(request, response);
	}}
