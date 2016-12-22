package com.jnit.pessimistic;

import java.util.concurrent.TimeUnit;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jnit.config.HibernateUtil;
import com.jnit.entities.Employee;

public class Main2 {
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public static void main(String[] args) {
		Session session = sessionFactory.openSession();
		//session.beginTransaction();
		Employee emp = session.get(Employee.class, 1L, LockMode.PESSIMISTIC_READ);
		//emp.setfName("AB");
		//session.save(emp);
		//session.getTransaction().commit();
		System.out.println(emp.getfName());
		session.close();

	}
}
