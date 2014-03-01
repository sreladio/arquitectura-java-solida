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
 * distintas acciones
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
	
	public void insertar(ActionEvent evento) {
		Categoria categoria = new Categoria(this.categoria);
		Libro libro = new Libro(isbn, titulo, categoria);
		
		// Realizamos la propia acción
		getServicioLibros().insertarLibro(libro);
		
		// Actualizamos la lista del managed bean
		List<Libro> listaDeLibros = getServicioLibros().buscarTodosLosLibros();
		this.setListaDeLibros(listaDeLibros);
	}
	
	public void borrar(ActionEvent evento) {
		// Obtenemos el parámetro de la request
		UIComponent componente = (UIComponent) evento.getComponent();
		String isbn = componente.getAttributes().get("isbn").toString();
		
		// Realizamos la propia acción
		getServicioLibros().borrarLibro(new Libro(isbn));
		
		// Actualizamos la lista del managed bean
		setListaDeLibros(getServicioLibros().buscarTodosLosLibros());
	}
	
	public void editar(ActionEvent evento) {
		UIComponent componente = (UIComponent) evento.getComponent();
		Libro libro = getServicioLibros().buscarLibroPorClave(
		componente.getAttributes().get("isbn").toString());
		isbn = libro.getIsbn();
		titulo = libro.getTitulo();
	}
	
	public void salvar(ActionEvent evento) {
		getServicioLibros().salvarLibro(
				new Libro(isbn, titulo, new Categoria(categoria)));
				setListaDeLibros(getServicioLibros().buscarTodosLosLibros());
	}
	
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
