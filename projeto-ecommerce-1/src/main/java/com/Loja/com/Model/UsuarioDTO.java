package com.Loja.com.Model;

public class UsuarioDTO {

	private String nome;
	private String email;
	private String endereco;
	private String telefone;
	
	
	
	  public UsuarioDTO(String email, String nome, String telefone, String endereco) {
	        this.email = email;
	        this.nome = nome;
	        this.telefone = telefone;
	        this.endereco = endereco;
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

}
