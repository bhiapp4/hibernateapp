package com.jnit.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jnit.config.HibernateUtil;
import com.jnit.entities.Employee;

public class FirstLevelCacheMain {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Employee e = session.get(Employee.class, 1L);
		System.out.println(e.getfName());
		//session.evict(e);
		Employee e1 = session.get(Employee.class, 1L);
		System.out.println(e1.getfName());
		session.close();
		sf.close();

	}

}
