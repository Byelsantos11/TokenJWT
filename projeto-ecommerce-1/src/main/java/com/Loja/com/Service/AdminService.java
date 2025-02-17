package com.Loja.com.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Loja.com.Model.Usuario;
import com.Loja.com.Repository.UsuarioRepository;

@Service
public class AdminService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	 PasswordEncoder passwordEncoder;
	
	
	//Apenas adm Principal consegui criar
	public String criarAdm(Long idAdm, Usuario novoAdm) {
		
		Optional<Usuario> usuarioPrincipal= usuarioRepository.findById(idAdm);
		if(usuarioPrincipal.isEmpty() || !usuarioPrincipal.get().isMainAdmin()) {
			  throw  new RuntimeException("Apenas admin principal pode criar outro admin");
		}

		novoAdm.setSenha(passwordEncoder.encode(novoAdm.getSenha()));
		novoAdm.setTipoUsuario(com.Loja.com.Model.tipo_usuario.ADMIN);
		novoAdm.setMainAdmin(false);
		usuarioRepository.save(novoAdm);
		return "Usuario cadastrado com sucesso";

	}
	
	
	//Método de visualizar todos usuarios
	public List<Usuario> TodosAdm(){
		return usuarioRepository.findByTipoUsuario(com.Loja.com.Model.tipo_usuario.ADMIN);	

	}
	
	
	public String excluirAdm(Long id, Long adminId) {
	    try {
	        // Verifica se o administrador principal existe
	        Optional<Usuario> adminPrincipalOpt = usuarioRepository.findById(adminId);
	        if (adminPrincipalOpt.isPresent()) {
	            Usuario adminPrincipal = adminPrincipalOpt.get();

	            // Verifica se o adminPrincipal é realmente o administrador principal
	            if (adminPrincipal.isMainAdmin()) { 
	            	
	                // Agora verifica se o administrador que será excluído existe
	                Optional<Usuario> admToDeleteOpt = usuarioRepository.findById(id);
	                if (admToDeleteOpt.isPresent()) {
	                    Usuario usuarioAdmin = admToDeleteOpt.get();

	                    // Verifica se o usuário é do tipo ADMIN
	                    if (usuarioAdmin.getTipoUsuario() ==com.Loja.com.Model.tipo_usuario.ADMIN) {
	                        usuarioRepository.delete(usuarioAdmin);  // Deleta o administrador
	                        return "Admin deletado com sucesso";
	                    } else {
	                        return "O usuário selecionado não é um administrador!";
	                    }
	                } else {
	                    return "Administrador não encontrado para exclusão!";
	                }
	            } else {
	                return "Somente o administrador principal pode excluir outros administradores!";
	            }
	        } else {
	            return "Administrador principal não encontrado!";
	        }
	    } catch (Exception e) {
	        return "Erro: não foi possível deletar o administrador: " + e.getMessage();
	    }
	}
}
	
	
	

