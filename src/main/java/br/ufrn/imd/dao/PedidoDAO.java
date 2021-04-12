package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.modelo.Pedido;

public class PedidoDAO extends DAOFactory<Pedido> {
	public Pedido save(Pedido entidade) {
		return salvar(entidade);
	}
	public List<Pedido> findAll(){
		return buscarTodos();
	}
	
	public Pedido FindByNumero(int numero) {
		String query = "Select p from Pedido p where p.numeroPedido="+numero;
		return buscarEntidadePorCampo(query);
	}
	
	public List<Pedido> findPedidoByDepartamento(int idDepartamento){
		String query = "Select p from Pedido p where p.departamento.id="+idDepartamento;
		return buscarEntidadesPorCampos(query);
	}
	
	public List<Pedido> findAllPedidosNaoFinalizados(){
		String query = "Select p from Pedido p where p.pedidoFinalizado=false order by p.dataPedido asc";
		return buscarEntidadesPorCampos(query);
	}
}
