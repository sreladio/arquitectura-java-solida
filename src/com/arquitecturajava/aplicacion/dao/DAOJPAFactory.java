package com.arquitecturajava.aplicacion.dao;

import com.arquitecturajava.aplicacion.dao.jpa.CategoriaDAOJPAImpl;
import com.arquitecturajava.aplicacion.dao.jpa.LibroDAOJPAImpl;

/**
 * FACTORÍA que se encarga de crear todos los DAO's que se implementen
 * mediante el estándard JPA.
 * 
 * @author eladio
 */
public class DAOJPAFactory implements DAOFactory {

	@Override
	public LibroDAO getLibroDAO() {
		return new LibroDAOJPAImpl();
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		return new CategoriaDAOJPAImpl();
	}


}
