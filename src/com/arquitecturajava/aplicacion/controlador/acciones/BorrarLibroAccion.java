package com.arquitecturajava.aplicacion.controlador.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.beans.Libro;
import com.arquitecturajava.aplicacion.dao.LibroDAO;
import com.arquitecturajava.aplicacion.dao.jpa.LibroDAOJPAImpl;

public class BorrarLibroAccion extends Accion {

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		LibroDAO libroDAO = new LibroDAOJPAImpl();
		String isbn = request.getParameter("isbn");
		
		Libro libro = new Libro(isbn);
		libroDAO.borrar(libro);
		
		return "/MostrarLibros";
	}
}
