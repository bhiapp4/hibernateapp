package com.jnit.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import com.jnit.config.HibernateUtil;
import com.jnit.entities.Employee;

public class SecondLevelCacheMain {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		//Statistics stats = sf.getStatistics();
		//stats.setStatisticsEnabled(true);
		
		Session session1 = sf.openSession();
		//printStats(stats);
		Employee e = session1.load(Employee.class, 1L);
		System.out.println(e.getfName());
		session1.close();
		//printStats(stats);
		Session session2 = sf.openSession();
		Employee e1 = session2.load(Employee.class, 1L);
		System.out.println(e1.getfName());
		session2.close();
		//printStats(stats);
		sf.close();

	}
	
	private static void printStats(Statistics stats) {
		System.out.println("Fetch Count="
				+ stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count="
				+ stats.getSecondLevelCacheHitCount());
		System.out
				.println("Second Level Miss Count="
						+ stats.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count="
				+ stats.getSecondLevelCachePutCount());
	}

}
