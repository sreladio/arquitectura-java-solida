package com.arquitecturajava.aplicacion.servicios.impl;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arquitecturajava.aplicacion.beans.Categoria;
import com.arquitecturajava.aplicacion.beans.Libro;
import com.arquitecturajava.aplicacion.dao.CategoriaDAO;
import com.arquitecturajava.aplicacion.dao.LibroDAO;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;

public class ServicioLibrosImpl implements ServicioLibros {

	private LibroDAO libroDAO = null;
	private CategoriaDAO categoriaDAO = null;
	
	/**
	 * En el constructor se encuentra el código de inicialización de acceso al DAO
	 */
	public ServicioLibrosImpl() {
		// Inicialización a través de una FACTORÍA ABSTRACTA:
		// -------------------------------------------------
		
		//DAOFactory factoria = DAOAbstractFactory.getInstance();
		//libroDAO = factoria.getLibroDAO();
		//categoriaDAO = factoria.getCategoriaDAO();
		
		// Inicialización a través del framework SPRING:
		// --------------------------------------------
		
		ClassPathXmlApplicationContext factoria = new ClassPathXmlApplicationContext("contextoAplicacion.xml"); // factoría de Spring que busca un fichero dentro del classpath
		libroDAO= (LibroDAO)factoria.getBean("libroDAO");
		categoriaDAO=(CategoriaDAO)factoria.getBean("categoriaDAO");
	}
	
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

}
