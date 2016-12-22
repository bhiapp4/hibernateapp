package com.jnit.main;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jnit.config.HibernateUtil;
import com.jnit.entities.composite.Author;

public class QueryCacheMain {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session1 = sessionFactory.openSession();
		String query = "FROM Author WHERE City=?";
		Query<Author> hql1 = session1.createQuery(query);
		hql1.setCacheable(true);
		hql1.setParameter(0, "Coppell");
		List<Author>authors1 = hql1.getResultList();
		System.out.println(authors1.size());
		session1.close();
		
		Session session2 = sessionFactory.openSession();
		Query<Author> hql2 = session2.createQuery(query);
		hql2.setCacheable(true);
		hql2.setParameter(0, "Coppell");
		List<Author>authors2 = hql2.getResultList();
		System.out.println(authors2.size());
		session2.close();

		sessionFactory.close();

	}

}
