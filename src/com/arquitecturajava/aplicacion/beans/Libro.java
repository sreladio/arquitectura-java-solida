package com.arquitecturajava.aplicacion.beans;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * Bean que representa a la tabla 'libros' de la BB.DD. 
 * Relacción N-1 con la tabla 'categorías'.
 * Clave foránea: categoría_id
 * 
 * @author eladio
 */
@NamedQueries({
	@NamedQuery(name="buscarPorCategoria", query="select l from Libro l where l.categoria=?1"),
	@NamedQuery(name="buscarPorClave", query="select l from Libro l where l.isbn=?1")
})
@Entity
@Table(name="libros")
public class Libro {

	@Id
	private String isbn;
	private String titulo;
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	// Constructores
	
	public Libro() {
	}
	
	public Libro(String isbn) {
		this.isbn = isbn;
	}
	
	public Libro(String isbn, String titulo, Categoria categoria) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.categoria = categoria;
	}
	
	// Getters y Setters
	
	public String getIsbn() {
		return this.isbn;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public Categoria getCategoria() {
		return this.categoria;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
