package br.ufrn.imd.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Responsável por guardar as credenciais do usuário para entrar no sistema
 * @author Karina e Maria Eduarda
 *
 */
@Entity
@Table(name="Usuario", schema="public")
public class Usuario{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="SEQ_USUARIO", sequenceName="public.id_seq_usuario", allocationSize=1)
	@Column(name="id_usuario")
	private Integer id;
	
	@Column(name="login", columnDefinition = "character varying(100) default ''", unique = true)
	private String login;
	
	@Column(name="senha", columnDefinition = "character varying(100) default ''")
	private String senha;
	
	@Column(name = "papel")
	@Enumerated(EnumType.STRING)
	private Papel permissao;
	
	public Usuario() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Papel getPermissao() {
		return permissao;
	}

	public void setPermissao(Papel permissao) {
		this.permissao = permissao;
	}
	
	

}
