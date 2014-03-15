package com.arquitecturajava.aplicacion.serviciosexternos;

import java.util.List;

import javax.jws.WebService;

@WebService
public interface ServicioInformacionLibros {

	public List<LibroDTO> listaInformacionLibros();
}
