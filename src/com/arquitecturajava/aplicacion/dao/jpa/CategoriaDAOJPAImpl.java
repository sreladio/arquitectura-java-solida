package com.arquitecturajava.aplicacion.dao.jpa;

import com.arquitecturajava.aplicacion.beans.Categoria;
import com.arquitecturajava.aplicacion.dao.CategoriaDAO;

/**
 * Extiende la funcionalidad de la implementación genérica del DAO según el 
 * estandard JPA para la entida Categoría.
 * 
 * @author eladio
 */
public class CategoriaDAOJPAImpl extends GenericDAOJPAImpl<Categoria, String> implements CategoriaDAO {

	// No hay nada que añadir, todo lo aporta la clase GenericDAOJPAImpl de la cual extendemos

}
