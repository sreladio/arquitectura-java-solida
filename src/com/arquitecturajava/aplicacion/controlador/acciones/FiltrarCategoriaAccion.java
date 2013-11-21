package com.arquitecturajava.aplicacion.controlador.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.beans.Libro;
import com.arquitecturajava.aplicacion.beans.Categoria;
import com.arquitecturajava.aplicacion.dao.CategoriaDAO;
import com.arquitecturajava.aplicacion.dao.LibroDAO;
import com.arquitecturajava.aplicacion.dao.jpa.CategoriaDAOJPAImpl;
import com.arquitecturajava.aplicacion.dao.jpa.LibroDAOJPAImpl;

public class FiltrarCategoriaAccion extends Accion {

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		LibroDAO libroDAO = new LibroDAOJPAImpl();
		CategoriaDAO categoriaDAO = new CategoriaDAOJPAImpl();
		
		List<Libro> listaDeLibros = null;
		List<Categoria> listaDeCategorias = categoriaDAO.buscarTodos();
		
		String categoriaId = request.getParameter("categoriaId"); 
		Categoria categoria = new Categoria(categoriaId);
		
		if(categoriaId == null || categoriaId.equals("seleccionar")) {
			listaDeLibros = libroDAO.buscarTodos();
		}
		else {
			listaDeLibros = libroDAO.buscarPorCategoria(categoria);
		}
		
		request.setAttribute("listaDeLibros", listaDeLibros);
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		
		return "MostrarLibros.jsp";
	}
}
