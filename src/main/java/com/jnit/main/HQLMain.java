package com.jnit.main;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jnit.config.HibernateUtil;
import com.jnit.entities.composite.Author;

public class HQLMain {

	public static void main(String[] args) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		String query = "FROM Author WHERE City=?";
//		Query hql = session.createQuery(query);
//		hql.setParameter(0, "Coppell");
//		List<Author>authors = hql.getResultList();
//		System.out.println(authors.size());
//		session.close();
//		sessionFactory.close();
		//selectFirstNames();
		//selectFirstNameAndMiddleName();
		//groupByCity();
		//nativeSQL();
		//namedQueries();
		namedNativeQueries();
	}
	
	public static void selectFirstNames(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		String query = "SELECT authorId.firstName FROM Author WHERE City=?";
		Query hql = session.createQuery(query);
		hql.setParameter(0, "Coppell");
		List<String>authorFirstNames = hql.getResultList();
		System.out.println(authorFirstNames.size());
		session.close();
		sessionFactory.close();
	
	}
	
	public static void selectFirstNameAndMiddleName(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		String query = "SELECT authorId.firstName, middleName FROM Author WHERE City=?";
		Query hql = session.createQuery(query);
		hql.setParameter(0, "Coppell");
		List<Object[]>authorObjs = hql.getResultList();
		for(Object[] obj  : authorObjs){
			System.out.println(obj[0]);
			System.out.println(obj[1]);
		}
		session.close();
		sessionFactory.close();
	
	}
	
	public static void groupByCity(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		String query = "SELECT city,count(*) FROM Author WHERE city=? group by city";
		Query hql = session.createQuery(query);
		hql.setParameter(0, "Coppell");
		List<Object[]>authorObjs = hql.getResultList();
		for(Object[] obj  : authorObjs){
			System.out.println(obj[0]);
			System.out.println(obj[1]);
		}
		session.close();
		sessionFactory.close();
	
	}
	
	//Native sql
	public static void nativeSQL(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		String query = "select * from author";
		Query hql = session.createSQLQuery(query).addEntity(Author.class);
		List<Author>authors = hql.getResultList();
		System.out.println(authors.size());
		session.close();
		sessionFactory.close();
	}
	
	//Named queries(named hql, named native sql queris)
	public static void namedQueries(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query hql = session.createNamedQuery("authorsByCity");
		hql.setParameter(1, "Coppell");
		List<Author>authors = hql.getResultList();
		System.out.println(authors.size());
		session.close();
		sessionFactory.close();
	}
	
	public static void namedNativeQueries(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query hql = session.getNamedQuery("selectAll");
		List<Author>authors = hql.getResultList();
		System.out.println(authors.size());
		session.close();
		sessionFactory.close();
	}



}
