package com.arquitecturajava.aplicacion.controlador.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;
import com.arquitecturajava.aplicacion.servicios.impl.ServicioLibrosImpl;

public class InsertarLibroAccion extends Accion {
	
	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		String categoriaId = request.getParameter("categoriaId");
		
		Categoria categoria = new Categoria(categoriaId);
		Libro libro = new Libro(isbn, titulo, categoria);
		
		ServicioLibros servicioLibros = (ServicioLibrosImpl)getBean("servicioLibros", request);
		servicioLibros.insertarLibro(libro);
		
		return "/MostrarLibros";
	}
	
}
