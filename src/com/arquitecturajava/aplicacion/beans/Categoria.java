package com.arquitecturajava.aplicacion.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Bean que representa a la tabla 'categorias' de la BB.DD.
 * Relacción 1-N con la tabla 'libros'.
 * Clave principal: id
 * 
 * @author eladio
 */
@Entity
@Table(name="categorias")
public class Categoria {

	@Id
	private String id;
	private String nombre;
	@OneToMany
	@JoinColumn(name="id")
	private List<Libro> listaDeLibros;
	
	// Constructores
	
	public Categoria() {
	}
	
	public Categoria(String id) {
		this.id = id;
	}
	
	public Categoria(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	// Getters y Setters
	
	public String getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public List<Libro> getListaDeLibros() {
		return this.listaDeLibros;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setListaDeLibros(List<Libro> listaDeLibros) {
		this.listaDeLibros = listaDeLibros;
	}
	
	@Override
	public boolean equals(Object o) {
		String categoriaId= ((Categoria)o).getId();
		return id.equals(categoriaId);
	}

}
