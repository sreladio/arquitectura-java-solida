package com.arquitecturajava.aplicacion.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.arquitecturajava.HibernateHelper;

/**
 * Clase Active Record que almacena todas las consultas que 
 * manejen los datos que contiene la tabla 'categorias'
 * @author eladio
 * 
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
	
	public Categoria() {
	}
	
	public Categoria(String id) {
		this.id = id;
	}
	
	public Categoria(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
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
	
	@SuppressWarnings("unchecked")
	public static List<Categoria> buscarTodos() {
		SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		Session sesion = sesionFactoria.openSession();
		String consulta = "from Categoria categoria";
		List<Categoria> listaDeCategorias = sesion.createQuery(consulta).list();
		sesion.close();
		return listaDeCategorias;
	}
}
