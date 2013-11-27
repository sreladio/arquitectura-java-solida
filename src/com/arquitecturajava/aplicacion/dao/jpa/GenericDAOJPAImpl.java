package com.arquitecturajava.aplicacion.dao.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.List;

import com.arquitecturajava.aplicacion.dao.GenericDAO;

import org.springframework.orm.jpa.support.JpaDaoSupport;

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
public class GenericDAOJPAImpl<T, Id> extends JpaDaoSupport implements GenericDAO <T, Id> {

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
	
	// CRUD
	// ------------------------------------------------------------------
	
	/**
	 * Guarda una entidad en la BB.DD utilizando el standard JPA
	 * @param objeto Entidad a persistir
	 */
	public void insertar(T objeto) {		
		getJpaTemplate().persist(objeto);
		System.out.println("INSERTANDO OBJETO");
	}
	
	/**
	 * Elimina una entidad de la BB.DD utilizando el standard JPA
	 * @param objeto Entidad a eliminar
	 */
	public void borrar(T objeto) {		
		getJpaTemplate().remove(getJpaTemplate().merge(objeto));
		System.out.println("BORRANDO OBJETO");
	}
	
	/**
	 * Modifica una entidad de la BB.DD utilizando el standard JPA
	 * @param objeto Entidad a modificar
	 */
	public void salvar(T objeto) {
		getJpaTemplate().merge(objeto);
		
		System.out.println("GUARDANDO OBJETO");
	}
	
	// Finders
	// ------------------------------------------------------------------
	
	/**
	 * Realiza un join fetch sobre una tabla de la BB.DD
	 * @return Lista con todos los elementos de la tabla
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos() {		
		return getJpaTemplate().find("select o from " + claseDePersistencia.getSimpleName() + " o");
	}
	
	/**
	 * Realiza una consulta sobre una tabla seleccionando aquel cuya clave
	 * coincida con la clave que se le pasa como parámetro
	 * @param id Clave primaria del libro a buscar
	 * @return T Objeto cuya clave coincida con la que se le pasa como parámetro
	 */
	public T buscarPorClave(Id id) {		
		return getJpaTemplate().find(claseDePersistencia, id);
	}
}
