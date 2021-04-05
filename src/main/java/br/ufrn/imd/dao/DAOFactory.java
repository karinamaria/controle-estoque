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
	public Class<T> nomeClasse(){
		return (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * Salvar ou atualizar uma determinada entidade 
	 * @param entidade entidade que será salva ou atualizada
	 * @return a entidade atualizada
	 */
	protected <S extends T> S salvar(S entidade) {
		manager.getTransaction().begin();
		manager.merge(entidade);
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
	
	
	@SuppressWarnings("unchecked")
	List<T> buscarTodos(){
		Query query = manager.createQuery("Select t from " + persistentClass.getSimpleName() + " t");
		List<T> list = query.getResultList();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	T buscarPorId(Integer id) {
		Query query = manager.createQuery("Select t from " + persistentClass.getSimpleName() + " t where t.id = :value1")
					.setParameter("value1", id);
		
		return (T) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	T buscarPorCampo(Object campo, Object value) {
		Query query = manager.createQuery("Select t from " + persistentClass.getSimpleName() + " t where t."+campo.toString()+"= :value1")
				.setParameter("value1", value);
	
		return (T) query.getSingleResult();
	}
	
}
