package com.arquitecturajava.aplicacion.controlador.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.beans.Categoria;

public class FormularioInsertarLibroAccion extends Accion {

	public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
		List<Categoria> listaDeCategorias = Categoria.buscarTodos();
		request.setAttribute("listaDeCategorias", listaDeCategorias);
		return "FormularioInsertarLibro.jsp";
	}
}
