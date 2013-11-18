package com.arquitecturajava;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHelper {

	private static EntityManagerFactory entityManagerFactory;
	
	private static void createEntityManagerFactory() {
		try {
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
