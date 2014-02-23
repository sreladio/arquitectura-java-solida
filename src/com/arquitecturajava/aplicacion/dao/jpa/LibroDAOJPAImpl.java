package com.arquitecturajava.aplicacion.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.LibroDAO;

/**
 * Extiende la funcionalidad de la implementación genérica del DAO según el 
 * estandard JPA para la entidad Libro.
 * 
 * @author eladio
 */
@Repository
public class LibroDAOJPAImpl extends GenericDAOJPAImpl <Libro, String> implements LibroDAO {
	
	/**
	 * Realiza un join implícito seleccionando todos los libros cuya categoría
	 * coincida con la categoría que se le pasa como parámetro
	 * @param categoria Clave foránea de la entidad Libro
	 * @return Lista de libros
	 */
	@Transactional
	public List<Libro> buscarPorCategoria(Categoria categoria) {	
		TypedQuery <Libro> consulta = getEntityManager().createNamedQuery("buscarPorCategoria", claseDePersistencia);
		consulta.setParameter(1,categoria);
		return consulta.getResultList();
	}
	
	/**
	 * Para evitar el LAZY LOADING realizamos un JOIN FETCH en la consulta
	 * cargando todos los elementos de la relacción en una sola tabla
	 */
	@Override
	@Transactional
	public List<Libro> buscarTodos() {
		String q = "select l from Libro l JOIN FETCH l.categoria";
		TypedQuery <Libro> consulta = getEntityManager().createQuery(q, claseDePersistencia);
		return consulta.getResultList();
	}
}
