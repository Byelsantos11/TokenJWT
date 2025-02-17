package com.Loja.com.Service;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Loja.com.Model.LoginRequest;
import com.Loja.com.Model.Usuario;
import com.Loja.com.Repository.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class AuthService {
	
	private static final String SECRET_KEY = "secreto123";
	
	@Autowired 
	UsuarioRepository usuarioRepository;
	
	@Autowired
	 PasswordEncoder passwordEncoder;
	
	
	public Usuario InforProfile(String email) {
		return usuarioRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("Usuario não encontrado"));
	}
	
	
	//Método verifica o usuario e senha e authentica grearndo o token
	public String autenticar (LoginRequest request) {
		
		Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
				.orElseThrow(()-> new RuntimeException("Usuario não encotrado"));
		
		    if(!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
			throw new RuntimeException("Senha inválida");
		}
		
		return gerarToken(usuario);
	}
	
	//Método de gerar Token
	public String gerarToken(Usuario usuario) {
		
		return JWT.create()
				.withSubject(usuario.getEmail())
				.withClaim("role", usuario.getTipoUsuario().name())
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + 86400000))
				.sign(Algorithm.HMAC256(SECRET_KEY));
					
	}
	
	//Método de selecionar o Adm principal
	public String selectAdm(Long idAdm) {
	    // Verifica se o usuário com o ID fornecido existe
	    Optional<Usuario> adminOptional = usuarioRepository.findById(idAdm);

	    if (adminOptional.isPresent()) {
	        Usuario adminNovo = adminOptional.get();

	        // Verifica se já existe um admin principal
	        Optional<Usuario> existingAdminOptional = usuarioRepository.findByIsMainAdminTrue();

	        if (existingAdminOptional.isPresent()) {
	            Usuario existingAdmin = existingAdminOptional.get();
	            existingAdmin.setMainAdmin(false);
	            usuarioRepository.save(existingAdmin); 
	        }

	        // Define o novo admin principal
	        adminNovo.setMainAdmin(true);
	        usuarioRepository.save(adminNovo);

	        return "Admin principal alterado com sucesso: " + adminNovo.getNome();
	    } else {
	        throw new RuntimeException("Usuário com ID " + idAdm + " não encontrado.");
	    }
	}

		
}
	

