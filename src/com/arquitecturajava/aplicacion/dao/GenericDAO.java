package com.arquitecturajava.aplicacion.dao;

import java.util.List;

/**
 * Interface genérica del patrón DAO para implementar en función del
 * framework o estándard que se necesite: JDBC, Hibernate, JPA, ...
 * 
 * @author eladio
 *
 * @param <T> Tipo de la clase de persistencia
 * @param <Id> Tipo del campo de la clave principal de la tabla
 */
public interface GenericDAO <T, Id> {
	
	// CRUD
	public abstract void insertar(T objeto);
	public abstract void borrar(T objeto);
	public abstract void salvar(T objeto);
	
	// Finders
	public abstract List<T> buscarTodos();
	public abstract T buscarPorClave(Id id);
}
