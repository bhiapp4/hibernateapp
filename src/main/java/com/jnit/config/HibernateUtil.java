package com.jnit.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	static{
		sessionFactory = new MetadataSources(new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml").build())
				.buildMetadata().buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
