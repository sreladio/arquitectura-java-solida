package com.arquitecturajava.aplicacion.dao;

/**
 * FACTORÍA ABSTRACTA
 *  
 * @author eladio
 */
public abstract class DAOAbstractFactory {
	
	public static DAOFactory getInstance() {
		// por el momento creará siempre instancias de la implementación JPA
		String tipo = "JPA";
		DAOFactory instancia = null;
		
		if(tipo.equals("JDBC")) {
			;
		}
		else if(tipo.equals("hibernate")) {
			;
		}
		else if(tipo.equals("JPA")) {
			instancia = new DAOJPAFactory();
		}
		
		return instancia;
	}

}
