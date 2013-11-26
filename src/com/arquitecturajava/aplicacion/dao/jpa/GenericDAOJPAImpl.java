package com.arquitecturajava.aplicacion.dao.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.List;

import com.arquitecturajava.aplicacion.dao.GenericDAO;

import org.springframework.orm.jpa.JpaTemplate;

/**
 * Implementación genérica del DAO genérico siguiendo el estandard JPA
 * 
 * @author eladio
 *
 * @param <T> Tipo de clase de persistencia
 * @param <Id> Tipo del campo de la clave principal de la tabla
 */
public class GenericDAOJPAImpl<T, Id> implements GenericDAO <T, Id> {

	// protected EntityManagerFactory entityManagerFactory;
	protected JpaTemplate plantillaJPA;
	private Class<T> claseDePersistencia;
	
	/**
	 * Obtiene el tipo de la clase de persistencia 
	 */
	@SuppressWarnings("unchecked")
	public GenericDAOJPAImpl() {
		Type clasePadre = getClass().getGenericSuperclass();
        ParameterizedType parametrosClasePadre = (ParameterizedType) clasePadre;
        this.claseDePersistencia = (Class<T>) parametrosClasePadre.getActualTypeArguments()[0];
	}
	
	// Inyección de dependencias
	
//	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
//		this.entityManagerFactory = entityManagerFactory;
//	}
//	
//	public EntityManagerFactory getEntityManagerFactory() {
//		return this.entityManagerFactory;
//	}
	
	public void setPlantillaJPA(JpaTemplate plantillaJPA) {
		this.plantillaJPA = plantillaJPA;
	}
	
	public JpaTemplate setPlantillaJPA() {
		return this.plantillaJPA;
	}
	
	// CRUD
	
	/**
	 * Guarda una entidad en la BB.DD utilizando el standard JPA
	 * @param objeto Entidad a persistir
	 */
	public void insertar(T objeto) {		
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaccion = null;
//		try {
//			transaccion = entityManager.getTransaction();
//			transaccion.begin();
//			entityManager.persist(objeto);
//			transaccion.commit();
//		} catch(PersistenceException e) {
//			entityManager.getTransaction().rollback();
//			throw e;
//		} finally {
//			entityManager.close();
//		}
		
		this.plantillaJPA.persist(objeto);
	}
	
	/**
	 * Elimina una entidad de la BB.DD utilizando el standard JPA
	 * @param objeto Entidad a eliminar
	 */
	public void borrar(T objeto) {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaccion = null;
//		try {
//			transaccion = entityManager.getTransaction();
//			transaccion.begin();
//			entityManager.remove(entityManager.merge(objeto));
//			transaccion.commit();
//		} catch(PersistenceException e) {
//			entityManager.getTransaction().rollback();
//			throw e;
//		} finally {
//			entityManager.close();	
//		}
		
		this.plantillaJPA.remove(this.plantillaJPA.merge(objeto));
	}
	
	/**
	 * Modifica una entidad de la BB.DD utilizando el standard JPA
	 * @param objeto Entidad a modificar
	 */
	public void salvar(T objeto) {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaccion = null;
//		try {
//			transaccion = entityManager.getTransaction();
//			transaccion.begin();
//			entityManager.merge(objeto);
//			transaccion.commit();
//		} catch(PersistenceException e) {
//			entityManager.getTransaction().rollback();
//			throw e;
//		} finally {
//			entityManager.close();
//		}
		
		this.plantillaJPA.merge(objeto);
	}
	
	// Finders
	
	/**
	 * Realiza un join fetch sobre una tabla de la BB.DD
	 * @return Lista con todos los elementos de la tabla
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos() {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		List<T> listaDeObjetos = null;
//		String q = "select o from " + claseDePersistencia.getSimpleName() + " o";
//		TypedQuery<T> consulta = entityManager.createQuery(q, this.claseDePersistencia);
//		try {
//			listaDeObjetos = consulta.getResultList();
//		} finally {
//			entityManager.close();
//		}
//		return listaDeObjetos;
		
		return this.plantillaJPA.find("select o from " + claseDePersistencia.getSimpleName() + " o");
	}
	
	/**
	 * Realiza una consulta sobre una tabla seleccionando aquel cuya clave
	 * coincida con la clave que se le pasa como parámetro
	 * @param id Clave primaria del libro a buscar
	 * @return T Objeto cuya clave coincida con la que se le pasa como parámetro
	 */
	public T buscarPorClave(Id id) {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		T objeto = null;
//		TypedQuery<T> consulta = entityManager.createNamedQuery("buscarPorClave", claseDePersistencia);
//		consulta.setParameter(1, id);
//		try {
//			objeto = consulta.getSingleResult();
//		} finally {
//			entityManager.close();
//		}
//		return objeto;
		
		return this.plantillaJPA.find(claseDePersistencia, id);
	}
}
