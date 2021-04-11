package br.ufrn.imd.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representa o histórico de entradas de produtos registradas no estoque
 * @author Karina e Maria Eduarda
 *
 */
@Entity
@Table(name="historico_entrada", schema="public")
public class HistoricoDeEntrada {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_HISTORICO_ENTRADA")
	@SequenceGenerator(name="SEQ_HISTORICO_ENTRADA", sequenceName="public.id_seq_historico_entrada", allocationSize=1)
	@Column(name="id_historico_entrada")
	private Integer id;
	
	@Column(name = "data_operacao", columnDefinition="DATE", nullable=true) 
	@Temporal(TemporalType.DATE)
	private Date dataOperacao;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_fornecedor", referencedColumnName = "id")
	private Fornecedor fornecedor;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<ItemPedido> itensPedido;
	
	public HistoricoDeEntrada() {
		
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
}
