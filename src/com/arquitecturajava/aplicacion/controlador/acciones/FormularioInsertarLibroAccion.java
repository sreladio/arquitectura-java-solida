package com.arquitecturajava.aplicacion.controlador.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.beans.Categoria;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;
import com.arquitecturajava.aplicacion.servicios.impl.ServicioLibrosImpl;

public class FormularioInsertarLibroAccion extends Accion {

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioLibros servicioLibros = (ServicioLibrosImpl)getBean("servicioLibros", request);
				
		List<Categoria> listaDeCategorias = servicioLibros.buscarTodasLasCategorias();
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		
		return "FormularioInsertarLibro.jsp";
	}
}
