package com.arquitecturajava.aplicacion.servicios.impl;

import java.util.List;

import com.arquitecturajava.aplicacion.beans.Categoria;
import com.arquitecturajava.aplicacion.beans.Libro;
import com.arquitecturajava.aplicacion.dao.CategoriaDAO;
import com.arquitecturajava.aplicacion.dao.LibroDAO;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;

public class ServicioLibrosImpl implements ServicioLibros {

	private LibroDAO libroDAO = null;
	private CategoriaDAO categoriaDAO = null;
	
	/**
	 * Utilizamos Spring como framework de inyección de dependencias para la
	 * inicialización de libroDAO y categoriaDAO a través del fichero xml,
	 * el cual usará los getters y setters de esta clase para dicha tarea.
	 */
	public ServicioLibrosImpl() {
		// Inicialización a través de una FACTORÍA ABSTRACTA:
		// -------------------------------------------------
		
		//DAOFactory factoria = DAOAbstractFactory.getInstance();
		//libroDAO = factoria.getLibroDAO();
		//categoriaDAO = factoria.getCategoriaDAO();
		
		// Inicialización a través del framework SPRING:
		// --------------------------------------------
		
		//ClassPathXmlApplicationContext factoria = new ClassPathXmlApplicationContext("contextoAplicacion.xml");
		//libroDAO= (LibroDAO)factoria.getBean("libroDAO");
		//categoriaDAO=(CategoriaDAO)factoria.getBean("categoriaDAO");
	}
	
	// CRUD
	
	@Override
	public void insertarLibro(Libro libro) {
		this.libroDAO.insertar(libro);
	}

	@Override
	public void borrarLibro(Libro libro) {
		this.libroDAO.borrar(libro);
	}

	@Override
	public void salvarLibro(Libro libro) {
		this.libroDAO.salvar(libro);
	}

	// Finders
	
	@Override
	public List<Libro> buscarTodosLosLibros() {
		return this.libroDAO.buscarTodos();
	}

	@Override
	public Libro buscarLibroPorClave(String isbn) {
		return this.libroDAO.buscarPorClave(isbn);
	}

	@Override
	public List<Libro> buscarLibrosPorCategoria(Categoria categoria) {
		return this.libroDAO.buscarPorCategoria(categoria);
	}

	@Override
	public List<Categoria> buscarTodasLasCategorias() {
		return this.categoriaDAO.buscarTodos();
	}

	// Inyección de dependencias
	
	@Override
	public LibroDAO getLibroDAO() {
		return this.libroDAO;
	}

	@Override
	public CategoriaDAO getCategoriaDAO() {
		return this.categoriaDAO;
	}

	@Override
	public void setLibroDAO(LibroDAO libroDAO) {
		this.libroDAO = libroDAO;
	}

	@Override
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

}
