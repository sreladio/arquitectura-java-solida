package com.arquitecturajava.aplicacion.controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.controlador.acciones.Accion;

/**
 * Controlador del MVC2 que implementa al INVOKER del patrón Command.
 * 
 * Recive todas las peticiones de las JSP's (CLIENTES) y delegará en
 * las aciones (COMMANDS) que serán las que realicen las operaciones
 * concretas.  
 *  
 * @author eladio
 */
public class ControladorLibros extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher despachador = null;
		Accion accion = null;
		
		// El formato de una acción siempre será: NOMBRE REPRESENTATIVO + ACCION,
		// por lo que podemos sacar el nombre representativo de la acción del path,
		// y pasárselo a la factoría para que nos devuelva la instancia de la acción
		accion = Accion.getAccion(request.getServletPath());
		
		// Como los métodos 'ejecutar(...)' de las acciones reciven los parámetros
		// HttpServletRequest y HttpServletResponse, podemos delegar en la acción
		// que acabamos de instanciar para que realice las operaciones necesarias
		String vista = accion.ejecutar(request, response);
		
		// Una vez realizadas las operaciones dentro de la acción, redireccionamos
		// a la vista (JSP) que la acción nos devuelva en el return
		despachador = request.getRequestDispatcher(vista);
		despachador.forward(request, response);
	}

}
