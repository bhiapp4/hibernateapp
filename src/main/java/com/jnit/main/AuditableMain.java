package com.jnit.main;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jnit.config.HibernateUtil;
import com.jnit.entities.composite.Author;
import com.jnit.entities.composite.AuthorId;

public class AuditableMain {

	public static void main(String[] args) {
		//createAuthor();
		//LocalDateTime ldt = LocalDateTime.
		Date date = new Date(1482424879598L);
		System.out.println(date);
		//updateAuthor();
		
		
	}
	
	public static void createAuthor(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Author a = new Author();
		AuthorId aid = new AuthorId("Paul","Dietel","How to Program Java");
		a.setAuthorId(aid);
		a.setIsbn("012345");
		a.setMiddleName("Mike");
		a.setCity("Lewisville");
		session.save(a);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
	}
	
	public static void updateAuthor(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery(" FROM Author where middleName=?");
		q.setParameter(0, "Mike");
		Author a  = (Author)q.getSingleResult();
		System.out.println(a.getCity());
		a.setCity("Irving");
		session.saveOrUpdate(a);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
	}



}
