package br.com.agenda.model;

import java.util.Date;

public class Contato {
	
	private int id;
	private String nome;
	private int idade;
	private Date dataCadastro;
	private String cpf;
	private String email;
	private String password;
	private int numCrud = 1;
	
	public int getNumCrud() {
		return numCrud;
	}
	public void setNumCrud(int numCrud) {
		this.numCrud = numCrud;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
