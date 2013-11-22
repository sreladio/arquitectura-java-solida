package com.arquitecturajava.aplicacion.dao;

/**
 * FACTORÍA que se encarga de crear todos los DAO's que se implementen
 * mediante el framework Hibernate.
 * 
 * @author eladio
 */
public class DAOHibernateFactory implements DAOFactory {

	@Override
	public LibroDAO getLibroDAO() {
		// new LibroDAOHibernateImpl();
		return null;
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		// new CategoriaDAOHibernateImpl();
		return null;
	}

}
