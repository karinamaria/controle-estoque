package br.ufrn.imd.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Representa a pessoa jurídica do tipo fornecedor
 * @author Karina e Maria Eduarda
 *
 */
@Entity
@Table(name="fornecedor", schema="public")
@SequenceGenerator(name="SEQ_PESSOA", sequenceName="id_seq_fornecedor", allocationSize=1)
public class Fornecedor extends Pessoa {
	
	@Column(name="cnpj", columnDefinition = "character varying(20) default ''", unique = true)
	private String cnpj;
	
	@Column(name="site", columnDefinition = "character varying(100) default ''")
	private String site;
	
	@Column(name="inscricao_estadual", columnDefinition = "character varying(20) default ''")
	private String inscricaoEstatudal;
	
	@Column(name="area_atuacao", columnDefinition = "character varying(100) default ''")
	private String areaAtuacao;
	
	public Fornecedor() {
		
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getInscricaoEstatudal() {
		return inscricaoEstatudal;
	}

	public void setInscricaoEstatudal(String inscricaoEstatudal) {
		this.inscricaoEstatudal = inscricaoEstatudal;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

}
