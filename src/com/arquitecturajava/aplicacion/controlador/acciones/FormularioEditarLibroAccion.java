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

public class FormularioEditarLibroAccion extends Accion {

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		LibroDAO libroDAO = new LibroDAOJPAImpl();
		CategoriaDAO categoriaDAO = new CategoriaDAOJPAImpl();
		String isbn = request.getParameter("isbn");
		
		Libro libro = libroDAO.buscarPorClave(isbn);
		List<Categoria> listaDeCategorias = categoriaDAO.buscarTodos();
		
		request.setAttribute("libro", libro);
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		
		return "FormularioEditarLibro.jsp";
	}
}
