package com.arquitecturajava;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {

	private static SessionFactory sessionFactory;
	private static Logger log = Logger.getLogger(HibernateHelper.class);
	
	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		SessionFactory sessionFactory = null;
		try {
			configuration.configure();
			sessionFactory = configuration.buildSessionFactory();
			return sessionFactory;
		} catch(HibernateException e) {
			log.error("Error creando una factoria de session" + e.getStackTrace());
			throw new DataBaseException("Error creando una factoria de session", e);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;
	}
}
