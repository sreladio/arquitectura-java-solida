package com.arquitecturajava.aplicacion.controlador.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.beans.Libro;
import com.arquitecturajava.aplicacion.beans.Categoria;

public class FiltrarCategoriaAccion extends Accion {

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		List<Libro> listaDeLibros = null;
		List<Categoria> listaDeCategorias = Categoria.buscarTodos();
		String categoriaId = request.getParameter("categoriaId"); 
		Categoria categoria = new Categoria(categoriaId);
		
		if(categoriaId == null || categoriaId.equals("seleccionar")) {
			listaDeLibros = Libro.buscarTodos();
		}
		else {
			listaDeLibros = Libro.buscarPorCategoria(categoria);
		}
		
		request.setAttribute("listaDeLibros", listaDeLibros);
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		
		return "MostrarLibros.jsp";
	}
}
