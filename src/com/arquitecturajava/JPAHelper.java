package com.arquitecturajava;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase de ayuda para el acceso a la BB.DD emdiante JPA.
 * 
 * Implementa el patrón singleton para construir el objeto ENTITY MANAGER FACTORY
 * que permitirá al DAO trabajar contra la base de datos utilizando el 
 * estándard JPA.
 * 
 * @author eladio
 */
public class JPAHelper {

	private static EntityManagerFactory entityManagerFactory;
	
	private static void createEntityManagerFactory() {
		try {
			// lee el archivo de configuración 'persistence.xml' 
			// que se encuentra en src/META-INF e inicializa el
			// framework para su uso
			entityManagerFactory = Persistence.createEntityManagerFactory("arquitecturaJava");
		} catch(Throwable e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Error al crear la factoria JPA");
		}
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if(entityManagerFactory == null) {
			createEntityManagerFactory();
		}
		return entityManagerFactory;
	}
}
