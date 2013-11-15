package com.arquitecturajava.aplicacion.controlador.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.beans.Libro;
import com.arquitecturajava.aplicacion.beans.Categoria;

public class SalvarLibroAccion extends Accion {

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		String categoriaId = request.getParameter("categoriaId");
		
		Categoria categoria = new Categoria(categoriaId);
		Libro libro = new Libro(isbn, titulo, categoria);
		
		libro.salvar();
		
		return "/MostrarLibros";
	}
}
