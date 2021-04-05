package br.ufrn.imd.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Representa o endereço da pessoa física ou jurídica
 * @author Karina e Maria Eduarda
 *
 */
@Entity
@Table(name="endereco", schema="public")
public class Endereco {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="SEQ_ENDERECO", sequenceName="public.id_seq_endereco", allocationSize=1)
	@Column(name="id_endereco")
	private Integer id;
	
	@Column(name="rua", columnDefinition = "character varying(255) default ''")
	private String rua;
	
	@Column(name="cep", columnDefinition = "character varying(10) default ''")
	private String cep;
	
	@Column(name="bairro", columnDefinition = "character varying(255) default ''")
	private String bairro;
	
	@Column(name="cidade", columnDefinition = "character varying(255) default ''")
	private String cidade;
	
	@Column(name="pais", columnDefinition = "character varying(255) default ''")
	private String pais;

	@Column(name="numero", columnDefinition = "int")
	private int numero;
	
	@Column(name="complemento", columnDefinition = "character varying(255) default ''")
	private String complemento;

	public Endereco() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
