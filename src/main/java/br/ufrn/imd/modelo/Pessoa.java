package br.ufrn.imd.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * Classe abstrata que representa uma pessoa do sistema
 * @author Karina e Maria Eduarda
 *
 */

@MappedSuperclass
public abstract class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_PESSOA")
	private Integer id;
	
	@Column(name="nome", columnDefinition = "character varying(255) default ''")
	private String nome;
	
	@Column(name="email", columnDefinition = "character varying(100) default ''")
	private String email;
	
	@Column(name="telefone", columnDefinition = "character varying(20) default ''")
	private String telefone;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;
	
	public Pessoa() {
		
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
