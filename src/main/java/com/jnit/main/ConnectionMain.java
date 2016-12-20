package com.jnit.main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jnit.config.HibernateUtil;
import com.jnit.entities.Course;
import com.jnit.entities.SkillLevel;
import com.jnit.entities.Topic;
import com.jnit.entities.User;

public class ConnectionMain {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();
		//User user = new User("appi.bh@gmail.com","Appi", "Bhimavarapu","R", "appi123", LocalDate.of(1984, Month.SEPTEMBER, 19), "585-260-5875");
		//session.save(user);
		//Course course = new Course("Java - I", "Appi",SkillLevel.BEGINNER, "Intro to java", null, new BigDecimal(0.0), "gets understanding of java", "free");
		//course.getRegisteredUsers().add(session.get(User.class,1l));
		//Topic topic = new Topic("data types", "10",course);
		//session.save(course);
		Course course = session.get(Course.class, 1l);
		//System.out.println(course.getTopicNames());
		tran.commit();
		session.close();
		sf.close();
	}

}
