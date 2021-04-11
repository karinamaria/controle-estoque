package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.modelo.HistoricoDeEntrada;

public class HistoricoEntradaDAO extends DAOFactory<HistoricoDeEntrada> {
	public HistoricoDeEntrada save(HistoricoDeEntrada h) {
		return salvar(h);
	}
	
	public List<HistoricoDeEntrada> findAll(){
		return buscarTodos();
	}
}
