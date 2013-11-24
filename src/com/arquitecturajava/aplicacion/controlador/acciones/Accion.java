package com.arquitecturajava.aplicacion.controlador.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Nivel más alto de la jerarquía de acciones
 * 
 * @author eladio
 */
public abstract class Accion {

	public abstract String ejecutar(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Método FACTORÍA que se encarga de instanciar las distintas acciones
	 * @param tipo Nombre representativo de la acción (Servlet Path)
	 * @return Instancia de una acción concreta
	 */
	public static Accion getAccion(String tipo) {
		String clase = tipo.substring(1).concat("Accion");
		Package pkg = Accion.class.getPackage();
		Accion accion = null;
		
		try {			
			accion = (Accion)Class.forName(pkg.getName() + "." + clase).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return accion;
	}
	
	/**
	 * Arranca el framework Spring, normalmente para inicializar la clase de
	 * servicios que nos dará acceso al DAO
	 * @param nombre Nombre del bean en el fichero contextoAplicacion.xml (serviciosLibro)
	 * @return Instancia del bean. Instancia de la clase servicios
	 */
	public Object getBean(String nombre) {
		// factoría clásica de Spring que busca un fichero dentro del classpath
		ClassPathXmlApplicationContext factoria = new ClassPathXmlApplicationContext("contextoAplicacion.xml");
		return factoria.getBean(nombre);
	}
	
	/**
	 * Método getBean sobrecargado para que Spring sólo cargue el fichero de
	 * configuración una única vez al arrancar la aplicación web
	 * @param nombre Nombre del bean en el fichero contextoAplicacion.xml (serviciosLibro)
	 * @param request 
	 * @return Instancia del bean. Instancia de la clase servicios
	 */
	public Object getBean(String nombre, HttpServletRequest request) {
		// factoría específica de Spring a nivel de aplicación web
		WebApplicationContext factoria = WebApplicationContextUtils.
				getRequiredWebApplicationContext(request.getSession().
				getServletContext());
		return factoria.getBean(nombre);
	}
}
