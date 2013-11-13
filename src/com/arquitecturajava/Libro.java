package com.arquitecturajava;

import java.util.List;

/**
 * Almacena todas las consultas que manejen los datos que contiene la tabla 'libros'
 * @author eladio
 *
 */
public class Libro {

	private String isbn;
	private String titulo;
	private String categoria;
	
	public Libro() {
		
	}
	
	public Libro(String isbn) {
		this.isbn = isbn;
	}
	
	public Libro(String isbn, String titulo, String categoria) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.categoria = categoria;
	}
	
	public String getIsbn() {
		return this.isbn;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public void insertar() {
		DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		String consultaSQL = "insert into libros values";
		consultaSQL += "('" + this.isbn + "','" + this.titulo + "','" + this.categoria + "')";
		db.modificarRegistro(consultaSQL);
	}
	
	public void borrar() {
		DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		String consultaSQL = "delete from libros where isbn='" + this.isbn + "'";
		db.modificarRegistro(consultaSQL);
	}
	
	public void salvar() {
		DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		String consultaSQL = "update libros set titulo='" + this.titulo + 
												"', categoria='" + this.categoria + 
												"' where isbn='" + this.isbn + "'";
		db.modificarRegistro(consultaSQL);
	}
	
	public static Libro buscarPorClave(String isbn) {
		DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		String consultaSQL = "select * from libros where isbn='" + isbn + "'";
		List<Libro> libros = db.seleccionarRegistros(consultaSQL, Libro.class);	
		return libros.get(0);
	}
	
	public static List<Libro> buscarPorCategoria(String categoria) {
		DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		String consultaSQL = "select * from libros where categoria='" + categoria + "'";
		List<Libro> libros =  db.seleccionarRegistros(consultaSQL, Libro.class);
		return libros;
	}
	
	public static List<Libro> buscarTodos() {
		DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		String consultaSQL = "select * from libros";
		List<Libro> listaDeLibros = db.seleccionarRegistros(consultaSQL, Libro.class);
		return listaDeLibros;
	}
	
	public static List<String> buscarTodasLasCategorias() {
		DataBaseHelper<String> bd = new DataBaseHelper<String>();
		String consultaSQL = "select distinct(categoria) from libros";
		List<String> listaDeCategorias = bd.seleccionarRegistros(consultaSQL, String.class);
		return listaDeCategorias;
	}
}
