package com.arquitecturajava.aplicacion.controlador.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.Libro;

public class FormularioEditarLibroAccion extends Accion {

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		Libro libro = Libro.buscarPorClave(isbn);
		List<String> listaDeCategorias = Libro.buscarTodasLasCategorias();
		request.setAttribute("libro", libro);
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		return "FormularioEditarLibro.jsp";
	}
}
