package br.ufrn.imd.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Representa os departamentos que solicitarão pedidos
 * @author Karina e Maria Eduarda
 *
 */
@Entity
@Table(name="departamento", schema="public")
public class Departamento {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_DEPARTAMENTO")
	@SequenceGenerator(name="SEQ_DEPARTAMENTO", sequenceName="public.id_seq_departamento", allocationSize=1)
	@Column(name="id_departamento")
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@OneToMany(mappedBy = "departamento", targetEntity = Funcionario.class, fetch = FetchType.LAZY)
	private List<Funcionario> funcionarios;
	
	@OneToMany(mappedBy = "departamento", targetEntity = Pedido.class, fetch = FetchType.LAZY)
	private List<Pedido> pedidos;
	
	public Departamento() {
		
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

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public String toString() {
		return getNome();
	}
}
