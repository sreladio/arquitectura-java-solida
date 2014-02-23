package com.arquitecturajava.aplicacion.servicios;

import java.util.List;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.CategoriaDAO;
import com.arquitecturajava.aplicacion.dao.LibroDAO;

/**
 * Proporciona un servício de inicialización del acceso a la capa DAO,
 * así como las operaciones que se pueden realizar sobre la BB.DD, a 
 * las distintas clases de negocio (Actions).
 * 
 * @author eladio
 */
public interface ServicioLibros {
	
	// CRUD
	public void insertarLibro(Libro libro);
	public void borrarLibro(Libro libro);
	public void salvarLibro(Libro libro);
	
	// Finders
	public List<Libro> buscarTodosLosLibros();
	public Libro buscarLibroPorClave(String isbn);
	public List<Libro> buscarLibrosPorCategoria(Categoria categoria);
	public List<Categoria> buscarTodasLasCategorias();
	
	// Getters y Setters para DI
	public LibroDAO getLibroDAO();
	public CategoriaDAO getCategoriaDAO();
	public void setLibroDAO(LibroDAO libroDAO);
	public void setCategoriaDAO(CategoriaDAO categoriaDAO);
}
