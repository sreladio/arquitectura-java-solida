package com.arquitecturajava.aplicacion.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.arquitecturajava.JPAHelper;
import com.arquitecturajava.aplicacion.beans.Categoria;
import com.arquitecturajava.aplicacion.beans.Libro;
import com.arquitecturajava.aplicacion.dao.LibroDAO;

/**
 * Extiende la funcionalidad de la implementación genérica del DAO según el 
 * estandard JPA para la entida Libro.
 * 
 * @author eladio
 */
public class LibroDAOJPAImpl extends GenericDAOJPAImpl <Libro, String> implements LibroDAO {
	
	/**
	 * Realiza un join implícito seleccionando todos los libros cuya categoría
	 * coincida con la categoría que se le pasa como parámetro
	 * @param categoria Clave foránea de la entidad Libro
	 * @return Lista de libros
	 */
	public List<Libro> buscarPorCategoria(Categoria categoria) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Libro> listaDeLibros = null;
		TypedQuery<Libro> consulta = entityManager.createNamedQuery("buscarPorCategoria", Libro.class);
		consulta.setParameter(1, categoria);
		try {
			listaDeLibros = consulta.getResultList();
		} finally {
			entityManager.close();
		}
		return listaDeLibros;
	}
	
	/**
	 * Para evitar el LAZY LOADING realizamos un JOIN FETCH en la consulta
	 * cargando todos los elementos de la relacción en una sola tabla
	 */
	@Override
	public List<Libro> buscarTodos() {
		EntityManagerFactory entityManagerFactory = JPAHelper.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Libro> listaDeLibros = null;
		String q = "select l from Libro l JOIN FETCH l.categoria";
		TypedQuery<Libro> consulta = entityManager.createQuery(q, Libro.class);
		try {
			listaDeLibros = consulta.getResultList();
		} finally {
			entityManager.close();
		}
		return listaDeLibros;
	}
}
