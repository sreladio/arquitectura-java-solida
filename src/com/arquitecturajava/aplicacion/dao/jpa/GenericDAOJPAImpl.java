package com.arquitecturajava.aplicacion.dao.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.arquitecturajava.aplicacion.dao.GenericDAO;

import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación genérica del DAO genérico siguiendo el estandard JPA.
 * Utiliza la plantilla JpaTemplate del framework spring a través de la
 * clase JpaDaoSupport que proporciona el propio framework.
 * 
 * @author eladio
 *
 * @param <T> Tipo de clase de persistencia
 * @param <Id> Tipo del campo de la clave principal de la tabla
 */
public class GenericDAOJPAImpl<T, Id> implements GenericDAO <T, Id> {

	@PersistenceContext
	private EntityManager entityManager;

	protected Class<T> claseDePersistencia;
	
	/**
	 * Obtiene el tipo de la clase de persistencia 
	 */
	@SuppressWarnings("unchecked")
	public GenericDAOJPAImpl() {
		Type clasePadre = getClass().getGenericSuperclass();
        ParameterizedType parametrosClasePadre = (ParameterizedType) clasePadre;
        this.claseDePersistencia = (Class<T>) parametrosClasePadre.getActualTypeArguments()[0];
		
//		this.claseDePersistencia = (Class<T>) ((ParameterizedType) getClass()
//				.getGenericSuperclass()).getActualTypeArguments()[0];

	}
	
	// Inyección de dependencias
	// ------------------------------------------------------------------
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	
	// CRUD
	// ------------------------------------------------------------------
	
	/**
	 * Guarda una entidad en la BB.DD utilizando el standard JPA
	 * @param objeto Entidad a persistir
	 */
	@Transactional
	public void insertar(T objeto) {		
		getEntityManager().persist(objeto);
		System.out.println("INSERTANDO OBJETO");
	}
	
	/**
	 * Elimina una entidad de la BB.DD utilizando el standard JPA
	 * @param objeto Entidad a eliminar
	 */
	@Transactional
	public void borrar(T objeto) {		
		getEntityManager().remove(getEntityManager().merge(objeto));
		System.out.println("BORRANDO OBJETO");
	}
	
	/**
	 * Modifica una entidad de la BB.DD utilizando el standard JPA
	 * @param objeto Entidad a modificar
	 */
	@Transactional
	public void salvar(T objeto) {
		getEntityManager().merge(objeto);
		
		System.out.println("GUARDANDO OBJETO");
	}
	
	// Finders
	// ------------------------------------------------------------------
	
	/**
	 * Realiza un join fetch sobre una tabla de la BB.DD
	 * @return Lista con todos los elementos de la tabla
	 */
	@Transactional(readOnly=true)
	public List<T> buscarTodos() {		
		List<T> listaDeObjetos = null;
		String q = "select o from " + claseDePersistencia.getSimpleName() + " o";
		TypedQuery<T> consulta = getEntityManager().createQuery(q, claseDePersistencia); 
		listaDeObjetos = consulta.getResultList();				
		return listaDeObjetos;
	}
	
	/**
	 * Realiza una consulta sobre una tabla seleccionando aquel cuya clave
	 * coincida con la clave que se le pasa como parámetro
	 * @param id Clave primaria del libro a buscar
	 * @return T Objeto cuya clave coincida con la que se le pasa como parámetro
	 */
	public T buscarPorClave(Id id) {		
		return getEntityManager().find(claseDePersistencia, id);
	}
}
