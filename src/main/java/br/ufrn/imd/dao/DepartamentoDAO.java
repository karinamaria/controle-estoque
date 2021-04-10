package br.ufrn.imd.dao;

import java.util.List;

import br.ufrn.imd.modelo.Departamento;

public class DepartamentoDAO extends DAOFactory<Departamento> {
	public Departamento save(Departamento departamento) {
		return salvar(departamento);
	}
	
	public List<Departamento> findAll(){
		return buscarTodos();
	}
	
	public Departamento findByNome(String nome) {
		String query = "Select d from Departamento d where d.nome='"+nome+"'";
		return buscarEntidadePorCampo(query);
	}
}
