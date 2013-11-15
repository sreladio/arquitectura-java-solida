package com.arquitecturajava.aplicacion.controlador.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.beans.Libro;
import com.arquitecturajava.aplicacion.beans.Categoria;

public class FormularioEditarLibroAccion extends Accion {

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		
		Libro libro = Libro.buscarPorClave(isbn);
		List<Categoria> listaDeCategorias = Categoria.buscarTodos();
		
		request.setAttribute("libro", libro);
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		
		return "FormularioEditarLibro.jsp";
	}
}
