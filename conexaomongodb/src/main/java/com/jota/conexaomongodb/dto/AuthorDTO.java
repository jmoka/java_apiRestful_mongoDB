package com.jota.conexaomongodb.dto;

import java.io.Serializable;

import com.jota.conexaomongodb.domain.User;

public class AuthorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	
	public AuthorDTO() {
		
	}
	
	public AuthorDTO(User user) {
		id = user.getId();
		nome = user.getNome();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
