package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.lang.reflect.ParameterizedType;

public abstract class DAOFactory <T> {
	private Class<T> persistentClass = nomeClasse();
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
	private static EntityManager manager = factory.createEntityManager();
	
	@SuppressWarnings("unchecked")
	private Class<T> nomeClasse(){
		return (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * Salvar uma determinada entidade 
	 * @param entidade entidade que ser? salva 
	 * @return a entidade salva
	 */
	<S extends T> S salvar(S entidade) {
		manager.getTransaction().begin();
		manager.persist(entidade);
		manager.getTransaction().commit();
		
		return entidade;
	}
	
	/**
	 * Atualiza uma determinada entidade
	 * @param entidade que ser? atualizada
	 * @return a entidade atualizada
	 */
	<S extends T> S update(S entidade) {
		manager.getTransaction().begin();
		manager.merge(entidade);
		manager.getTransaction().commit();
		return entidade;
	}
	
	/**
	 * Remover entidade
	 * @param entidade que ser? removida
	 * @return vari?vel que indica se a remo??o foi realizada
	 */
	<S extends T> boolean remover(S entidade) {
		manager.getTransaction().begin();
		manager.remove(entidade);
		manager.getTransaction().commit();
	
		return true;
		
	}
	
	/**
	 * Buscar todos os registros salvos de determinada entidade
	 * @return a lista de registros encontrados
	 */
	@SuppressWarnings("unchecked")
	List<T> buscarTodos(){
		Query query = manager.createQuery("Select t from " + persistentClass.getSimpleName() + " t");
		List<T> list = null;
		try {
			list = query.getResultList();
		}catch(NoResultException nre) {
			list = null;
		}
		return list;
	}
	
	/**
	 * Buscar entidade por email
	 * @param id da entidade que ser? buscada
	 * @return entidade que refere-se ao id buscado
	 */
	@SuppressWarnings("unchecked")
	T buscarPorId(Integer id){
		Query query = manager.createQuery("Select t from " + persistentClass.getSimpleName() + " t where t.id = :value1")
					.setParameter("value1", id);
		T entidade = null;
		try{
			entidade = (T) query.getSingleResult();
		}catch(NoResultException nre) {
			entidade = null;
		}
		return entidade;
	}
	
	/**
	 * Buscar entidade por campos desejados
	 * @param queryPesquisa string que representa a query de pesquisa
	 * @return a entidade com base nos par?metros buscados
	 */
	@SuppressWarnings("unchecked")
	T buscarEntidadePorCampo(String queryPesquisa){
		Query query = manager.createQuery(queryPesquisa);
		T entidade = null;
		try{
			entidade = (T) query.getSingleResult();
		}catch(NoResultException nre) {
			entidade = null;
		}
		
		return entidade;
	}
	
	/**
	 * Buscar uma lista referente a uma entidade
	 * @param queryPesquisa string que representa a query de pesquisa
	 * @return a lista com objetos do tipo <T> com base nos par?metros buscados
	 */
	@SuppressWarnings("unchecked")
	List<T> buscarEntidadesPorCampos(String queryPesquisa){
		Query query = manager.createQuery(queryPesquisa);
		List<T> list = null;
		try {
			list = query.getResultList();
		}catch(NoResultException nre) {
			list = null;
		}
		return list;
	}
}
