package com.jnit.entities.composite;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jnit.config.HibernateUtil;

public class OptimisticLockingMain {
	private static SessionFactory sf = HibernateUtil.getSessionFactory();

	public static void main(String[] args) {
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();
		Author author = session.get(Author.class, new AuthorId("Harry", "Dietel", "How to Program Java"));
		System.out.println(author.getCity());
		author.setMiddleName("Paul");
		session.saveOrUpdate(author);
		tran.commit();
		session.close();

	}

}
