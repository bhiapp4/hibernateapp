package com.jnit.main;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jnit.config.HibernateUtil;
import com.jnit.entities.composite.Author;
//Criteria
//Restrictions - where conditions for ur sql
//Projections - aggregate operations(group by, having, avg, count)
//Create criteria object for the class that we want to perform a query on
//add restrictions
//execute criteria
public class CriteriaHibernate4 {

	public static void main(String[] args) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		Criteria criteria = session.createCriteria(Author.class);
//		//criteria.add(Restrictions.eq("middleName", "Henry"));
//		//criteria.add(Restrictions.eq("city", "Coppell"));
//		criteria.add(Restrictions.like("authorId.firstName", "Har%"));
//		//Author a1 = (Author) criteria.uniqueResult();
//		//System.out.println(a1.getAuthorId().getFirstName());
//		criteria.addOrder(Order.asc("city"));
//		List<Author>authors = criteria.list();
//		System.out.println(authors.size());
//		session.close();
//		sessionFactory.close();
		performProjectionQuery();
	}
	//group by city- Coppell 2, Lewisville 5..
	public static void performProjectionQuery(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Author.class);
		criteria.setProjection( Projections.projectionList().add(Projections.groupProperty("city"))
				.add( Projections.rowCount(), "authorCount" ));
		List list = criteria.list();
		System.out.println(list);
		session.close();
		sessionFactory.close();
		
		
	}
}
