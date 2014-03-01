package com.arquitecturajava.aplicacion.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;

/**
 * Contiene toda la funcionalidad que antes estaba dispersa en las
 * distintas acciones del modelo MVC2
 * 
 * @author eladio
 */
@ManagedBean
@SessionScoped
public class FormularioLibroManagedBean {

	private String isbn;
	private String titulo;
	private String categoria;
	private List<Libro> listaDeLibros;
	private List<SelectItem> listaDeCategorias;
	
	// Constructor Post
	// ----------------
	
	/**
	 * Invoca a la clase ServicioLibros para cargar los atributos del bean
	 */
	@PostConstruct // define que método ejecutar una vez creado el ManagedBean
	public void iniciar() {
		listaDeLibros = getServicioLibros().buscarTodosLosLibros();
		List<Categoria> categorias = getServicioLibros().buscarTodasLasCategorias();
		listaDeCategorias = new ArrayList<SelectItem>();
		
		for (Categoria categoria : categorias) {
			listaDeCategorias.add(new SelectItem(categoria.getId(), categoria.getNombre()));
		}
	}
	
	// Servicios
	// ---------
	
	/**
	 * Se encarga de cargar todos los beans a nivel del framework Spring	
	 * y a ponerlos a disposición de JSF.
	 * @return ServicioLibros
	 */
	public ServicioLibros getServicioLibros() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext contexto = FacesContextUtils.getWebApplicationContext(facesContext);
		return (ServicioLibros) contexto.getBean("servicioLibros");
	}
	
	// Acciones y eventos
	// ------------------
	
	/**
	 * Utiliza el servício para insertar un nuevo libro
	 * @param evento
	 */
	public void insertar(ActionEvent evento) {
		Categoria categoria = new Categoria(this.categoria);
		Libro libro = new Libro(isbn, titulo, categoria);
		
		// Realizamos la propia acción
		this.getServicioLibros().insertarLibro(libro);
		
		// Actualizamos la lista del managed bean
		List<Libro> listaDeLibros = getServicioLibros().buscarTodosLosLibros();
		this.setListaDeLibros(listaDeLibros);
		
		// Reseteamos categoria para que nos muestre todos los libros
		this.setCategoria("0");
	}
	
	/**
	 * Utiliza el servicio para eliminar un libro
	 * @param evento
	 */
	public void borrar(ActionEvent evento) {
		// Obtenemos el parámetro de la request
		UIComponent componente = (UIComponent) evento.getComponent();
		String isbn = componente.getAttributes().get("isbn").toString();
		
		// Realizamos la propia acción
		this.getServicioLibros().borrarLibro(new Libro(isbn));
		
		// Actualizamos la lista del managed bean
		this.setListaDeLibros(getServicioLibros().buscarTodosLosLibros());
	}
	
	/**
	 * Carga en el ManagedBean los datos relativos al libro que se va a editar
	 * @param evento
	 */
	public void editar(ActionEvent evento) {
		// Obtenemos el parámetro de la request
		UIComponent componente = (UIComponent) evento.getComponent();
		String isbn = componente.getAttributes().get("isbn").toString();
		
		// Buscamos el libro que se va a editar
		Libro libro = getServicioLibros().buscarLibroPorClave(isbn);
		
		// Obtenemos los datos del libro
		this.isbn = libro.getIsbn();
		this.titulo = libro.getTitulo();
	}
	
	/**
	 * Llama al servício para actualizar un libro
	 * @param evento
	 */
	public void salvar(ActionEvent evento) {
		Categoria categoria = new Categoria(this.categoria);
		Libro libro = new Libro(this.isbn, this.titulo, categoria);
		
		// Llamamos al método update del servicio
		this.getServicioLibros().salvarLibro(libro);
		
		// Actualizamos la lista de Libros en el ManagedBean
		List<Libro> libros = this.getServicioLibros().buscarTodosLosLibros();
		this.setListaDeLibros(libros);
		
		// Reseteamos categoria para que nos muestre todos los libros
		this.setCategoria("0");
	}
	
	/**
	 * 
	 * @param evento
	 */
	public void filtrar(ValueChangeEvent evento) {
		UIComponent componente = (UIComponent) evento.getComponent();
		String idCategoria = componente.getAttributes().get("value").toString();
		
		if(idCategoria.compareTo("0") != 0) {
			setListaDeLibros(getServicioLibros().buscarLibrosPorCategoria(new Categoria(idCategoria)));
		} 
		else {
			setListaDeLibros(getServicioLibros().buscarTodosLosLibros());
		}
	}
	
	/**
	 * Se encarga de vaciar las variables isbn y titulo
	 * @param e
	 */
	public void formularioInsertar(ActionEvent e) {
		this.isbn = "";
		this.titulo = "";
	}
	
	// Getters y Setters
	// -----------------
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public List<Libro> getListaDeLibros() {
		return listaDeLibros;
	}
	
	public void setListaDeLibros(List<Libro> listaDeLibros) {
		this.listaDeLibros = listaDeLibros;
	}
	public List<SelectItem> getListaDeCategorias() {
		return listaDeCategorias;
	}
	
	public void setListaDeCategorias(List<SelectItem> listaDeCategorias) {
		this.listaDeCategorias = listaDeCategorias;
	}
	
}
