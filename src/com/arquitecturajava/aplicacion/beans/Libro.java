package com.arquitecturajava.aplicacion.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.arquitecturajava.HibernateHelper;

/**
 * Clase Active Record que almacena todas las consultas que 
 * manejen los datos que contiene la tabla 'libros'
 * @author eladio
 * 
 */
@Entity
@Table(name="libros")
public class Libro {

	@Id
	private String isbn;
	private String titulo;
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
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
	
	public void insertar() {
		/**
		 * CODIGO PARA UTILIZAR CON LA CLASE DATABASE HELPER
		 * 
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "insert into libros values";
		 * consultaSQL += "('" + this.isbn + "','" + this.titulo + "','" + this.categoria + "')";
		 * db.modificarRegistro(consultaSQL);
		 */
		
		SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		Session sesion = sesionFactoria.openSession();
		Transaction transaccion = sesion.beginTransaction();
		sesion.save(this);
		transaccion.commit();
		sesion.close();
	}
	
	public void borrar() {
		/**
		 * CODIGO PARA UTILIZAR CON LA CLASE DATABASE HELPER
		 * 
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "delete from libros where isbn='" + this.isbn + "'";
		 * db.modificarRegistro(consultaSQL);
		 */
		
		SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		Session sesion = sesionFactoria.openSession();
		Transaction transaccion = sesion.beginTransaction();
		sesion.delete(this);
		transaccion.commit();
		sesion.close();
	}
	
	public void salvar() {
		/**
		 * CODIGO PARA UTILIZAR CON LA CLASE DATABASE HELPER
		 * 
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "update libros set titulo='" + this.titulo + 
		 *										"', categoria='" + this.categoria + 
		 *										"' where isbn='" + this.isbn + "'";
		 * db.modificarRegistro(consultaSQL);
		 */
		
		SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		Session sesion = sesionFactoria.openSession();
		Transaction transaccion = sesion.beginTransaction();
		sesion.saveOrUpdate(this);
		transaccion.commit();
		sesion.close();
	}
	
	public static Libro buscarPorClave(String isbn) {
		/**
		 * CODIGO PARA UTILIZAR CON LA CLASE DATABASE HELPER
		 * 
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "select * from libros where isbn='" + isbn + "'";
		 * List<Libro> libros = db.seleccionarRegistros(consultaSQL, Libro.class);	
		 * return libros.get(0);
		 */
		
		SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		Session sesion = sesionFactoria.openSession();
		Libro libro = (Libro)sesion.get(Libro.class, isbn);
		sesion.close();
		return libro;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Libro> buscarPorCategoria(String categoria) {
		/**
		 * CODIGO PARA UTILIZAR CON LA CLASE DATABASE HELPER
		 * 
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "select * from libros where categoria='" + categoria + "'";
		 * List<Libro> libros =  db.seleccionarRegistros(consultaSQL, Libro.class);
		 * return libros;
		 */
		
		SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		Session sesion = sesionFactoria.openSession();
		Query consulta = sesion.createQuery("from Libro libro where libro.categoria=:categoria ");
		consulta.setString("categoria", categoria);
		List<Libro> listaDeLibros = consulta.list();
		sesion.close();
		return listaDeLibros;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Libro> buscarTodos() {
		/**
		 * CODIGO PARA UTILIZAR CON LA CLASE DATABASE HELPER
		 * 
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "select * from libros";
		 * List<Libro> listaDeLibros = db.seleccionarRegistros(consultaSQL, Libro.class);
		 * return listaDeLibros;
		 */
		
		SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		Session sesion = sesionFactoria.openSession();
		// Esta	clausula 'rigth join fetch' ejecuta una consulta de joins entre
		// las dos tablas, cargando todos los datos en una única consulta.
		
		// Así evitamos tener que hacer varias consultas cuando accedemos a un campo de la
		// entidad Categoria desde la capa de presentación: "libro[x].categoria.nombre"
		String consulta = "from Libro libro right join fetch libro.categoria";
		List<Libro> listaDeLibros = sesion.createQuery(consulta).list();
		sesion.close();
		return listaDeLibros;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> buscarTodasLasCategorias() {
		/**
		 * CODIGO PARA UTILIZAR CON LA CLASE DATABASE HELPER
		 * 
		 * DataBaseHelper<String> bd = new DataBaseHelper<String>();
		 * String consultaSQL = "select distinct(categoria) from libros";
		 * List<String> listaDeCategorias = bd.seleccionarRegistros(consultaSQL, String.class);
		 * return listaDeCategorias;
		 */
		
		SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		Session sesion = sesionFactoria.openSession();
		String consulta = "select distinct libro.categoria from Libro libro";
		List<String> listaDeCategorias = sesion.createQuery(consulta).list();
		sesion.close();
		return listaDeCategorias;
	}
}
