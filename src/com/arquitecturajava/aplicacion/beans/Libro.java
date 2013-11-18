package com.arquitecturajava.aplicacion.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceException;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

// import org.hibernate.Query;
// import org.hibernate.Session;
// import org.hibernate.SessionFactory;
// import org.hibernate.Transaction;

// import com.arquitecturajava.HibernateHelper;
import com.arquitecturajava.JPAHelper;

/**
 * Clase Active Record que almacena todas las consultas que 
 * manejen los datos que contiene la tabla 'libros'
 * @author eladio
 * 
 */
@NamedQueries({
	@NamedQuery(name="buscarTodos", query="select l from Libro l JOIN FETCH l.categoria"),
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
		 * PERSISTENCIA CON JDBC
		 * ---------------------
		 * 
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "insert into libros values";
		 * consultaSQL += "('" + this.isbn + "','" + this.titulo + "','" + this.categoria + "')";
		 * db.modificarRegistro(consultaSQL);
		 */
		
		/**
		 * PERSISTENCIA CON HINERNATE
		 * --------------------------
		 * 
		 * SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		 * Session sesion = sesionFactoria.openSession();
		 * Transaction transaccion = sesion.beginTransaction();
		 * sesion.save(this);
		 * transaccion.commit();
		 * sesion.close();
		 */
		
		/**
		 * PERSISTENCIA CON JPA
		 * --------------------
		 */
		EntityManagerFactory entityManagerFactory = JPAHelper.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaccion = null;
		try {
			transaccion = entityManager.getTransaction();
			transaccion.begin();
			entityManager.persist(this);
			transaccion.commit();
		} catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}
	
	public void borrar() {
		/**
		 * PERSISTENCIA CON JDBC
		 * ---------------------
		 * 
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "delete from libros where isbn='" + this.isbn + "'";
		 * db.modificarRegistro(consultaSQL);
		 */
		
		/**
		 * PERSISTENCIA CON HIBERNATE
		 * --------------------------
		 * 
		 * SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		 * Session sesion = sesionFactoria.openSession();
		 * Transaction transaccion = sesion.beginTransaction();
		 * sesion.delete(this);
		 * transaccion.commit();
		 * sesion.close();
		 */	
		
		/**
		 * PERSISTENCIA CON JPA
		 * --------------------
		 */
		EntityManagerFactory entityManagerFactory = JPAHelper.getEntityManagerFactory();
		EntityManager  entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaccion = null;
		try {
			transaccion = entityManager.getTransaction();
			transaccion.begin();
			entityManager.remove(entityManager.merge(this));
			transaccion.commit();
		} catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();	
		}
	}
	
	public void salvar() {
		/**
		 * PERSISTENCIA CON JDBC
		 * ---------------------
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "update libros set titulo='" + this.titulo + 
		 *										"', categoria='" + this.categoria + 
		 *										"' where isbn='" + this.isbn + "'";
		 * db.modificarRegistro(consultaSQL);
		 */
		
		/**
		 * PERSISTENCIA CON HIBERNATE
		 * --------------------------
		 * 
		 * SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		 * Session sesion = sesionFactoria.openSession();
		 * Transaction transaccion = sesion.beginTransaction();
		 * sesion.saveOrUpdate(this);
		 * transaccion.commit();
		 * sesion.close();
		 */
		
		/**
		 * PERSISTENCIA CON JPA
		 * --------------------
		 */
		EntityManagerFactory entityManagerFactory = JPAHelper.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaccion = null;
		try {
			transaccion = entityManager.getTransaction();
			transaccion.begin();
			entityManager.merge(this);
			transaccion.commit();
		} catch(PersistenceException e) {
			entityManager.getTransaction().rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}
	
	public static Libro buscarPorClave(String isbn) {
		/**
		 * CONSULTA CON JDBC
		 * -----------------
		 * 
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "select * from libros where isbn='" + isbn + "'";
		 * List<Libro> libros = db.seleccionarRegistros(consultaSQL, Libro.class);	
		 * return libros.get(0);
		 */
		
		/**
		 * CONSULTA CON HIBERNATE
		 * ----------------------
		 * 
		 * SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		 * Session sesion = sesionFactoria.openSession();
		 * Libro libro = (Libro)sesion.get(Libro.class, isbn);
		 * sesion.close();
		 * return libro; 
		 */
		
		/**
		 * CONSULTA CON JPA
		 * ----------------
		 */
		EntityManagerFactory entityManagerFactory = JPAHelper.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<Libro> consulta = entityManager.createNamedQuery("buscarPorClave", Libro.class);
		consulta.setParameter(1, isbn);
		Libro libro = consulta.getSingleResult();
		entityManager.close();
		return libro;
	}
	
	public static List<Libro> buscarPorCategoria(Categoria categoria) {
		/**
		 * CONSULTA CON JDBC
		 * -----------------
		 * 
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "select * from libros where categoria='" + categoria + "'";
		 * List<Libro> libros =  db.seleccionarRegistros(consultaSQL, Libro.class);
		 * return libros;
		 */
		
		/**
		 * CONSULTA CON HIBERNATE
		 * ---------------------- 
		 * 		 
		 * SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		 * Session sesion = sesionFactoria.openSession();
		 * Query consulta = sesion.createQuery("from Libro libro where libro.categoria=:categoria ");
		 * consulta.setString("categoria", categoria);
		 * List<Libro> listaDeLibros = consulta.list();
		 * sesion.close();
		 * return listaDeLibros; 
		 */
		
		/**
		 * CONSULTA CON JPA
		 * ----------------
		 */
		EntityManagerFactory entityManagerFactory = JPAHelper.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<Libro> consulta = entityManager.createNamedQuery("buscarPorCategoria", Libro.class);
		consulta.setParameter(1, categoria);
		List<Libro> listaDeLibros = consulta.getResultList();
		entityManager.close();
		return listaDeLibros;
	}
	
	public static List<Libro> buscarTodos() {
		/**
		 * CONSULTA CON JDBC
		 * -----------------
		 * 
		 * DataBaseHelper<Libro> db = new DataBaseHelper<Libro>();
		 * String consultaSQL = "select * from libros";
		 * List<Libro> listaDeLibros = db.seleccionarRegistros(consultaSQL, Libro.class);
		 * return listaDeLibros;
		 */
		
		/**
		 * CONSULTA CON HIBERNATE
		 * ----------------------
		 * 
		 * SessionFactory sesionFactoria = HibernateHelper.getSessionFactory();
		 * Session sesion = sesionFactoria.openSession();
		 * 
		 * // Este 'JOIN FETCH' se utiliza para cargar de forma agresiva el extremo ONE
		 * // de la asociacion, evitando el lazy loaded y el problema de las N+1 consultas  
		 * 		
		 * // Así solo realizamos una consulta a una tabla cuando accedemos a un campo de la
		 * // entidad Categoria desde la capa de presentación: "libro[x].categoria.nombre"
		 * 
		 * String consulta = "from Libro libro right join fetch libro.categoria";
		 * List<Libro> listaDeLibros = sesion.createQuery(consulta).list();
		 * sesion.close();
		 * return listaDeLibros; 
		 */
		
		/**
		 * CONSULTA CON JPA
		 * ----------------
		 */
		EntityManagerFactory entityManagerFactory = JPAHelper.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		// al igual que con Hibernate hacemos una carga agresiva (eager) del extremo ONE
		TypedQuery<Libro> consulta = entityManager.createNamedQuery("buscarTodos", Libro.class);
		List<Libro> listaDeLibros = consulta.getResultList();
		entityManager.close();
		return listaDeLibros;
	}
	
}
