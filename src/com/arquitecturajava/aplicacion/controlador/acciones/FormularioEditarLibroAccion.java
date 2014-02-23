package com.arquitecturajava.aplicacion.controlador.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;
import com.arquitecturajava.aplicacion.servicios.impl.ServicioLibrosImpl;

public class FormularioEditarLibroAccion extends Accion {

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioLibros servicioLibros = (ServicioLibrosImpl)getBean("servicioLibros", request);
				
		String isbn = request.getParameter("isbn");
		
		Libro libro = servicioLibros.buscarLibroPorClave(isbn);
		List<Categoria> listaDeCategorias = servicioLibros.buscarTodasLasCategorias();
		
		request.setAttribute("libro", libro);
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		
		return "FormularioEditarLibro.jsp";
	}
}
