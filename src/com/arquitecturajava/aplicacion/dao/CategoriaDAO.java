package com.arquitecturajava.aplicacion.dao;

import java.util.List;

import com.arquitecturajava.aplicacion.beans.Categoria;

/**
 * FACADE que se le proporciona a los Actions del MVC con todas las operaciones
 * de persistencia que se pueden realizar sobre la entidad Categoria.
 * 
 * @author eladio
 */
public interface CategoriaDAO {
	
	public abstract List<Categoria> buscarTodos();
}
