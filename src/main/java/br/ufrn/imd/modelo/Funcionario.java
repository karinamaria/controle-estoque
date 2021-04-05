package br.ufrn.imd.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa a pessoa física do tipo funcionário
 * @author Karina e Maria Eduarda
 *
 */
@Entity
@Table(name="funcionario", schema="public")
@SequenceGenerator(name="SEQ_FUNCIONARIO", sequenceName="id_seq_funcionario", allocationSize=1)
public class Funcionario extends Pessoa {
	
	@Column(name="cpf", columnDefinition = "character varying(20) default ''")
	private String cpf;
	
	@Column(name = "data_nascimento", columnDefinition="DATE", nullable=true) 
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(name="sexo", columnDefinition = "character varying(2) default ''")
	private String sexo;
	
	@Column(name="salario")
	private double salario;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	private Usuario usuario;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
	private Departamento departamento;
	
	public Funcionario() {
		
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
}
