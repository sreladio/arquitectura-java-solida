package com.arquitecturajava;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class DataBaseHelper<T> {
	private static final Logger log = Logger.getLogger(DataBaseHelper.class); 
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String SCHEMA = "jdbc:mysql://localhost/arquitecturajava";
	private final String USER = "root";
	private final String PASS = "hardflip";

	/**
	 * Ejecuta una consulta SQL de modificación (insert, update, delete)
	 * 
	 * @param consultaSQL
	 * @return Número de filas que han modifiacado
	 */
	public int modificarRegistro(String consultaSQL) {
		Connection conexion = null;
		Statement sentencia = null;
		int filas = 0;

		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(SCHEMA, USER, PASS);
			sentencia = conexion.createStatement();
			filas = sentencia.executeUpdate(consultaSQL);
		} catch (ClassNotFoundException e) {
			log.error("CRUD: no se ha podido cargar el driver " + e.getMessage());
			throw new DataBaseException("CRUD: no se ha podido cargar el driver", e);
		} catch (SQLException e) {
			log.error("CRUD: no se ha podido acceder a la bbdd " + e.getMessage());
			throw new DataBaseException("CRUD: no se ha podido acceder a la bbdd", e);
		} finally {
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException e) {
					log.error("CRUD: error al cerrar la sentencia "
							+ e.getMessage());
				}
			}
			if (conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					log.error("CRUD: error al cerrar la conexion "
							+ e.getMessage());
				}
			}
		}

		return filas;
	}

	@SuppressWarnings("unchecked")
	public List<T> seleccionarRegistros(String consultaSQL, Class<T> clase) {
		Connection conexion = null;
		Statement sentencia = null;
		ResultSet filasSeleccionadas = null;
		List<T> listaDeObjetos = new ArrayList<T>();

		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(SCHEMA, USER, PASS);
			sentencia = conexion.createStatement();
			filasSeleccionadas = sentencia.executeQuery(consultaSQL);

			while (filasSeleccionadas.next()) {
				// creamos el objeto a partir de la clase que nos pasan como parametro
				T objeto = (T) Class.forName(clase.getName()).newInstance();
				
				// para inicializar el objeto obtenemos todos sus metodos
				Method [] metodos = objeto.getClass().getDeclaredMethods();
				
				// y buscamos aquellos que empiecen por 'set'
				for(Method metodo:metodos) {
					
					// inicializamos los metodos set con los valores del ResultSet
					if(metodo.getName().startsWith("set")) {
						
						// si le quitamos los 3 primeros caracteres a un metodo 'set' 
						// obtendremos el nombre de la columna del ResultSet
						String columna = metodo.getName().substring(3).toLowerCase();
						metodo.invoke(objeto, filasSeleccionadas.getString(columna));
					}
					
					// ad-hoc para las consultas de categorias existentes 
					if(objeto.getClass().getName().equals("java.lang.String")) {
						objeto = (T) filasSeleccionadas.getString(1);
					}
				}
				listaDeObjetos.add(objeto);
			}
		} catch (ClassNotFoundException e) {
			log.error("SELECTION: no se ha podido cargar el driver " + e.getMessage());
		} catch (SQLException e) {
			log.error("SELECTION: no se ha podido acceder a la bbdd " + e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("REFLECTION: " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			log.error("REFLECTION " + e.getMessage());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			log.error("REFLECTION: no se ha podido invocar al método " + e.getMessage());
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("REFLECTION: no se ha podido instanciar la clase " + e.getMessage());
			e.printStackTrace();
		} finally {
			if(sentencia != null) {
				try {
					sentencia.close();
				} catch(SQLException e) {
					log.error(e.getMessage());
				}
			}
			if(conexion != null) {
				try {
					conexion.close();
				} catch(SQLException e) {
					log.error(e.getMessage());
				}
			}
		}

		return listaDeObjetos;
	}
}
