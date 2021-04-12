package br.ufrn.imd.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.modelo.HistoricoDeEntrada;

public class HistoricoEntradaDAO extends DAOFactory<HistoricoDeEntrada> {
	public HistoricoDeEntrada save(HistoricoDeEntrada h) {
		return salvar(h);
	}
	
	public List<HistoricoDeEntrada> findAll(){
		return buscarTodos();
	}
	
	public HistoricoDeEntrada FindByNumero(int numero) {
		String query = "Select h from HistoricoDeEntrada h where h.numero="+numero;
		return buscarEntidadePorCampo(query);
	}
	
	public List<Integer> findNumerosHistorico(){
		List<HistoricoDeEntrada> historico = buscarTodos();
		List<Integer> numeros = new ArrayList<Integer>();
		for(HistoricoDeEntrada h : historico) {
			numeros.add(h.getNumero());
		}
		return numeros;
	}
}
