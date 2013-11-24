package com.arquitecturajava.aplicacion.controlador.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.beans.Libro;
import com.arquitecturajava.aplicacion.beans.Categoria;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;
import com.arquitecturajava.aplicacion.servicios.impl.ServicioLibrosImpl;

public class FiltrarCategoriaAccion extends Accion {

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		ServicioLibros servicioLibros = (ServicioLibrosImpl)getBean("servicioLibros");
		
		List<Libro> listaDeLibros = null;
		List<Categoria> listaDeCategorias = servicioLibros.buscarTodasLasCategorias();
		
		String categoriaId = request.getParameter("categoriaId"); 
		Categoria categoria = new Categoria(categoriaId);
		
		if(categoriaId == null || categoriaId.equals("seleccionar")) {
			listaDeLibros = servicioLibros.buscarTodosLosLibros();
		}
		else {
			listaDeLibros = servicioLibros.buscarLibrosPorCategoria(categoria);
		}
		
		request.setAttribute("listaDeLibros", listaDeLibros);
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		
		return "MostrarLibros.jsp";
	}
}
