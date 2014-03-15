package com.arquitecturajava.aplicacion.serviciosexternos.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;
import com.arquitecturajava.aplicacion.serviciosexternos.LibroDTO;
import com.arquitecturajava.aplicacion.serviciosexternos.ServicioInformacionLibros;

@Service
@WebService(endpointInterface="com.arquitecturajava.aplicacion.serviciosexternos.ServicioInformacionLibros")
public class ServicioInformacionLibrosCXF implements ServicioInformacionLibros {

	@Autowired
	private ServicioLibros servicioLibros;
	
	@Override
	@WebMethod
	public List<LibroDTO> listaInformacionLibros() {
		List<Libro> libros = servicioLibros.buscarTodosLosLibros();
		List<LibroDTO> librosDTO = new ArrayList<LibroDTO>();
		
		for (Libro libro : libros) {
			LibroDTO libroDTO = new LibroDTO(libro.getIsbn(), libro.getTitulo(), libro.getCategoria().getNombre());
			librosDTO.add(libroDTO);
		}
		
		return librosDTO;
	}

	public ServicioLibros getServicioInformacionLibros() {
		return this.servicioLibros;
	}
	
	public void setServicioLibros(ServicioLibros servicioLibros) {
		this.servicioLibros = servicioLibros;
	}
}
