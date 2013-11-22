package com.arquitecturajava.aplicacion.controlador.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}
