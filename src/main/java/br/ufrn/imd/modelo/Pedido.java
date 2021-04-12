package br.ufrn.imd.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * Representa um pedido a ser atendido pelo estoque
 * @author Karina e Maria Eduarda
 *
 */
@Entity
@Table(name="pedido", schema="public")
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PEDIDO")
	@SequenceGenerator(name="SEQ_PEDIDO", sequenceName="public.id_seq_pedido", allocationSize=1)
	@Column(name="id_pedido")
	private Integer id;
	
	@Column(name="numero_pedido", columnDefinition = "int", unique = true)
	private int numeroPedido;
	
	@Column(name = "data_pedido", columnDefinition="DATE", nullable=true) 
	@Temporal(TemporalType.DATE)
	private Date dataPedido;
	
	@Column(name="valor_total")
	private double valorTotal;

	@Column(name="descricao", columnDefinition = "character varying(20) default ''")
	private String descricao;
	
	@Column(name="motivo", columnDefinition = "character varying(20) default ''")
	private String motivo;

	private boolean pedidoRealizado = false;

	private boolean pedidoFinalizado = false;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
	private Departamento departamento;
	
	@OneToMany(mappedBy = "produto", targetEntity = ItemPedido.class, fetch = FetchType.LAZY)
	private List<ItemPedido> itensPedido;
	
	public Pedido() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public boolean isPedidoRealizado() {
		return pedidoRealizado;
	}

	public void setPedidoRealizado(boolean pedidoRealizado) {
		this.pedidoRealizado = pedidoRealizado;
	}

	public boolean isPedidoFinalizado() {
		return pedidoFinalizado;
	}

	public void setPedidoFinalizado(boolean pedidoFinalizado) {
		this.pedidoFinalizado = pedidoFinalizado;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

}
