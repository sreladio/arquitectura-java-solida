package com.arquitecturajava.aplicacion.servicios;

import java.util.List;

import com.arquitecturajava.aplicacion.beans.Libro;
import com.arquitecturajava.aplicacion.beans.Categoria;

/**
 * Proporciona un servício de inicialización del acceso a la capa DAO,
 * así como las operaciones que se pueden realizar sobre la BB.DD, a 
 * las distintas clases de negocio (Actions).
 * 
 * @author eladio
 */
public interface ServicioLibros {
	
	public void insertarLibro(Libro libro);
	public void borrarLibro(Libro libro);
	public void salvarLibro(Libro libro);
	
	public List<Libro> buscarTodosLosLibros();
	public Libro buscarLibroPorClave(String isbn);
	public List<Libro> buscarLibrosPorCategoria(Categoria categoria);
	
	public List<Categoria> buscarTodasLasCategorias();
}
