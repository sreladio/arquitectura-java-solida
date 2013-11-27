package com.arquitecturajava.aplicacion.dao.jpa;

import java.util.List;

import com.arquitecturajava.aplicacion.beans.Categoria;
import com.arquitecturajava.aplicacion.beans.Libro;
import com.arquitecturajava.aplicacion.dao.LibroDAO;

/**
 * Extiende la funcionalidad de la implementación genérica del DAO según el 
 * estandard JPA para la entidad Libro.
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
	@SuppressWarnings("unchecked")
	public List<Libro> buscarPorCategoria(Categoria categoria) {		
		return getJpaTemplate().findByNamedQuery("buscarPorCategoria", categoria);
	}
	
	/**
	 * Para evitar el LAZY LOADING realizamos un JOIN FETCH en la consulta
	 * cargando todos los elementos de la relacción en una sola tabla
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Libro> buscarTodos() {
		String q = "select l from Libro l JOIN FETCH l.categoria";		
		return getJpaTemplate().find(q);
	}
}
