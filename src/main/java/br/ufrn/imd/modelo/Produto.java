package br.ufrn.imd.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Representa o produto em si
 * @author Karina e Maria Eduarda
 *
 */
@Entity
@Table(name="produto", schema="public")
public class Produto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PRODUTO")
	@SequenceGenerator(name="SEQ_PRODUTO", sequenceName="public.id_seq_produto", allocationSize=1)
	@Column(name="id_produto")
	private Integer id;
	
	@Column(name="nome", columnDefinition = "character varying(255) default ''")
	private String nome;
	
	@Column(name="codigo", columnDefinition = "character varying(20) default ''", unique = true)
	private String codigo;
	
	@Column(name="quantidade_estoque", columnDefinition = "int")
	private Integer quantidadeNoEstoque;
	
	@Column(name="preco_compra")
	private double precoCompra;
	
	public Produto() {
		
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public Integer getQuantidadeNoEstoque() {
		return quantidadeNoEstoque;
	}

	public void setQuantidadeNoEstoque(Integer quantidadeNoEstoque) {
		this.quantidadeNoEstoque = quantidadeNoEstoque;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(double precoCompra) {
		this.precoCompra = precoCompra;
	}
	
	public String toString() {
		return getNome();
	}
}
