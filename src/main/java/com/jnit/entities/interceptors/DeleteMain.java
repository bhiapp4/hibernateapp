package com.jnit.entities.interceptors;

import javax.persistence.Query;

import org.hibernate.Session;

import com.jnit.config.HibernateUtil;
import com.jnit.entities.composite.Author;
import com.jnit.entities.composite.AuthorId;

public class DeleteMain {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory()
				.withOptions().interceptor(new DeleteInterceptor())
				.openSession();
		session.beginTransaction();
		Query q = session.createQuery(" FROM Author where middleName=?");
		q.setParameter(0, "Mike");
		Author a  = (Author)q.getSingleResult();
		System.out.println(a.getCity());
		session.delete(a);
		session.getTransaction().commit();
		session.close();
		HibernateUtil.getSessionFactory().close();
	}

}
