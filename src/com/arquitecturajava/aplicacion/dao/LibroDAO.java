package com.arquitecturajava.aplicacion.dao;

import java.util.List;

import com.arquitecturajava.aplicacion.beans.Categoria;
import com.arquitecturajava.aplicacion.beans.Libro;

/**
 * FACADE que se le proporciona a los Actions del MVC con todas las operaciones
 * de persistencia que se pueden realizar sobre la entidad Libro.
 * 
 * @author eladio
 */
public interface LibroDAO {

	// CRUD
	public abstract void insertar(Libro libro);
	public abstract void borrar(Libro libro);
	public abstract void salvar(Libro libro);
	
	// Finders
	public abstract Libro buscarPorClave(String isbn);
	public abstract List<Libro> buscarPorCategoria(Categoria categoria);
	public abstract List<Libro> buscarTodos();
}
