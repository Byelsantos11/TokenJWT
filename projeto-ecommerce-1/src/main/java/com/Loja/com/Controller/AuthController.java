package com.Loja.com.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Loja.com.Model.LoginRequest;
import com.Loja.com.Model.Usuario;
import com.Loja.com.Model.UsuarioDTO;
import com.Loja.com.Service.AdminService;
import com.Loja.com.Service.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/publico")
public class AuthController {
	
	@Autowired
	AuthService authservice;
	
	@Autowired
	AdminService adminService;
	
	
	//Rota para criar Adm
	@PostMapping("/criarAdm/{id}")
	public ResponseEntity<String> criarAdm (@PathVariable Long id, @RequestBody Usuario usuario){
		
		try {
			
			String novoUser = adminService.criarAdm(id, usuario);
			return ResponseEntity.ok("Admin criado com sucesso" + novoUser);
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar usuario" + e.getMessage());
		}
		
	}
	
	//Rota para excluir administrador
	@DeleteMapping("/deletarAdm/{id}")
	public ResponseEntity<String> deletarAdm(@PathVariable Long id, @RequestParam Long idAdmin){
		try {
			String resultado= adminService.excluirAdm(id, idAdmin);
			return ResponseEntity.ok("Administrador excluido com sucesso" + resultado);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar Administrador");
		}
		
	}
	

	//Rota Login publica para todos adms acessar seu login
	@PostMapping("/login")
	public ResponseEntity<?> login (@RequestBody @Valid LoginRequest loginrequest){
		
		try {
			String token = authservice.autenticar(loginrequest);
		    return ResponseEntity.ok(token);
		}catch(Exception e) {
			return ResponseEntity.status(401).body("Credenciais inválidas");
		
		}
	}
	

	//Rota selecionar AdmPrincipal
	@PutMapping("/admPrincipal/{id}")
	public ResponseEntity<String> selecionarAdm(@PathVariable Long id){
		
		try {
			String admin = authservice.selectAdm(id);
			return ResponseEntity.ok(admin);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao definir adm princiapal" + e.getMessage());
		}
		
		
		
	
}
	
	@GetMapping("/profile")
	public ResponseEntity<UsuarioDTO> profileUser(){
		
		//Pega o email autenticado pelo spring Security
		String usuarioInfo = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		//Recupera o usuario com email
		Usuario user = authservice.InforProfile(usuarioInfo);
		
		//Cria um novo dto com as informações do usuario logado la spring security
		UsuarioDTO usuariodto = new UsuarioDTO (user.getEmail(), user.getEndereco(), user.getNome(), user.getTelefone());
		return ResponseEntity.ok(usuariodto);
		
	}
	
	

}
