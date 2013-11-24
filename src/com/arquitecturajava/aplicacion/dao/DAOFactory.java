package com.arquitecturajava.aplicacion.dao;

/**
 * Interface que implementan las FACTORíAS CONCRETAS.
 * 
 * En desuso a favor del framework Spring
 * 
 * @author eladio
 */
@Deprecated
public interface DAOFactory {

	public abstract LibroDAO getLibroDAO();
	public abstract CategoriaDAO getCategoriaDAO();
	
}
