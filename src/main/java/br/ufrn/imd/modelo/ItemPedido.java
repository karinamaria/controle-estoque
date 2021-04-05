package br.ufrn.imd.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe utilizada para especificar a quantidade de itens desejada
 * @author Karina e Maria Eduarda
 *
 */
@Entity
@Table(name="item_pedido", schema="public")
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ITEM_PEDIDO")
	@SequenceGenerator(name="SEQ_ITEM_PEDIDO", sequenceName="public.id_seq_item_pedido", allocationSize=1)
	@Column(name="id_item_pedido")
	private Integer id;
	
	@Column(name="quantidade", columnDefinition = "int")
	private int quantidade;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
	private Produto produto;
	
	public ItemPedido() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
