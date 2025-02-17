package com.Loja.com.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Classe Usuário
@Entity
@Table(name= "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable= false)
	private String nome;
	
	@Column(nullable = false, unique=true)
	private String email;
	
	@Column(nullable= false)
	private String senha;
	
	
	@Column(nullable= false)
	private String endereco;
	
	@Column(nullable= false)
	private String telefone;
	
	 @Enumerated(EnumType.STRING)
	 @Column(nullable = false)
	 private tipo_usuario tipoUsuario;
	 
	 @Column(name = "is_main_admin")
	 private boolean isMainAdmin;
 


	public boolean isMainAdmin() {
	    return isMainAdmin;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public tipo_usuario getTipoUsuario() {
		return tipoUsuario;
	}


	public void setTipoUsuario(tipo_usuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


	public void setMainAdmin(boolean isMainAdmin) {
		this.isMainAdmin = isMainAdmin;
	}

}
