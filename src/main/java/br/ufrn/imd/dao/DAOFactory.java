package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
	 * Salvar ou atualizar uma determinada entidade 
	 * @param entidade entidade que será salva ou atualizada
	 * @return a entidade atualizada
	 */
	<S extends T> S salvar(S entidade) {
		manager.getTransaction().begin();
		manager.persist(entidade);
		manager.getTransaction().commit();
		
		return entidade;
	}
	/**
	 * Remover entidade
	 * @param entidade que será removida
	 * @return variável que indica se a remoção foi realizada
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
		List<T> list = query.getResultList();
		return list;
	}
	
	/**
	 * Buscar entidade por email
	 * @param id da entidade que será buscada
	 * @return entidade que refere-se ao id buscado
	 */
	@SuppressWarnings("unchecked")
	T buscarPorId(Integer id) {
		Query query = manager.createQuery("Select t from " + persistentClass.getSimpleName() + " t where t.id = :value1")
					.setParameter("value1", id);
		
		return (T) query.getSingleResult();
	}
	
	/**
	 * Buscar entidade por campos desejados
	 * @param queryPesquisa string que representa a query de pesquisa
	 * @return a entidade com base nos parâmetros buscados
	 */
	@SuppressWarnings("unchecked")
	T buscarEntidadePorCampo(String queryPesquisa) {
		Query query = manager.createQuery(queryPesquisa);
		return (T) query.getSingleResult();
	}
	
	/**
	 * Buscar uma lista referente a uma entidade
	 * @param queryPesquisa string que representa a query de pesquisa
	 * @return a lista com objetos do tipo <T> com base nos parâmetros buscados
	 */
	@SuppressWarnings("unchecked")
	List<T> buscarEntidadesPorCampos(String queryPesquisa){
		Query query = manager.createQuery(queryPesquisa);
		List<T> list = query.getResultList();
		return list;
	}
}
