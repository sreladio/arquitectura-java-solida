package com.arquitecturajava.aplicacion.dao;

/**
 * Interface que implementan las FACTORíAS CONCRETAS.
 * 
 * @author eladio
 */
public interface DAOFactory {

	public abstract LibroDAO getLibroDAO();
	public abstract CategoriaDAO getCategoriaDAO();
	
}
