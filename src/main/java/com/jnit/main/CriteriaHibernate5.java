package com.jnit.main;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.jnit.config.HibernateUtil;
import com.jnit.entities.composite.Author;
import com.jnit.entities.composite.AuthorId;
//JPA Criteria API
//create CriteriaBuilder object
//construct an object graph
//set parameters - where conditions
//results
public class CriteriaHibernate5 {

	public static void main(String[] args) {
		//createAuthor();
		//fidnAuthorsByMiddeName();
		//findAuthorFirstNamesByCity();
		//selectMultipleFieldsUsingCriteria();
		//selectMultipleFieldsUsingCriteriaTuple();
		//selectMultipleFieldsUsingCriteriaWrapper();
		performGroupByWithCriteria();

	}
	
	public static void createAuthor(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Author a = new Author();
		AuthorId aid = new AuthorId("Harry","Dietel","How to Program Java");
		a.setAuthorId(aid);
		a.setIsbn("012345");
		a.setMiddleName("Henry");
		a.setCity("Coppell");
		session.save(a);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
	}
	
	public static void fidnAuthorsByMiddeName(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();//create criteria builder
		CriteriaQuery<Author> criteria = criteriaBuilder.createQuery(Author.class);//create criteria for the class that we are querying
		Root<Author> root = criteria.from( Author.class );//create root
		criteria.select(root);//select the root
		ParameterExpression<String> middleNameParameter = criteriaBuilder.parameter( String.class );//define input parameter
		criteria.where( criteriaBuilder.equal( root.get("middleName" ), middleNameParameter ) );//add where condition

		TypedQuery<Author> query = session.createQuery( criteria );//create a query object
		query.setParameter( middleNameParameter, "Henry" );//set the parameter with the value
		List<Author> authors = query.getResultList();//get results
		System.out.println(authors.size());
		session.close();
		sessionFactory.close();		
	}
	
	public static void findAuthorFirstNamesByCity(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();//create criteria builder
		CriteriaQuery<String> criteria = criteriaBuilder.createQuery(String.class);//create criteria for the class that we are querying
		Root<Author> root = criteria.from( Author.class );//create root
		criteria.select(root.get("authorId").get("firstName"));//select the firstName from the root
		ParameterExpression<String> cityParameter = criteriaBuilder.parameter( String.class );//define input parameter
		criteria.where( criteriaBuilder.equal( root.get("city" ), cityParameter ) );//add where condition

		TypedQuery<String> query = session.createQuery( criteria );//create a query object
		query.setParameter( cityParameter, "Coppell" );//set the parameter with the value
		List<String> authors = query.getResultList();//get results
		System.out.println(authors);
		session.close();
		sessionFactory.close();

	}
	
	public static void selectMultipleFieldsUsingCriteria(){

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();//create criteria builder
		CriteriaQuery<Object[]> criteria = criteriaBuilder.createQuery(Object[].class);//create criteria for the class that we are querying
		Root<Author> root = criteria.from( Author.class );//create root
		Path<String> firstNamePath = root.get("authorId").get("firstName");//select the firstName from the root
		Path<String> middleNamePath = root.get("middleName");//select the firstName from the root
		criteria.multiselect(firstNamePath, middleNamePath);
		ParameterExpression<String> cityParameter = criteriaBuilder.parameter( String.class );//define input parameter
		criteria.where( criteriaBuilder.equal( root.get("city" ), cityParameter ) );//add where condition

		TypedQuery<Object[]> query = session.createQuery( criteria );//create a query object
		query.setParameter( cityParameter, "Coppell" );//set the parameter with the value
		List<Object[]> authorFNameandMiddleNames = query.getResultList();//get results
		for(Object[] a : authorFNameandMiddleNames){
			System.out.println(a[0]);
			System.out.println(a[1]);
		}
		session.close();
		sessionFactory.close();

	}
	
	public static void selectMultipleFieldsUsingCriteriaTuple(){

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();//create criteria builder
		CriteriaQuery<Tuple> criteria = criteriaBuilder.createQuery(Tuple.class);//create criteria for the class that we are querying
		Root<Author> root = criteria.from( Author.class );//create root
		Path<String> firstNamePath = root.get("authorId").get("firstName");//select the firstName from the root
		Path<String> middleNamePath = root.get("middleName");//select the firstName from the root
		criteria.multiselect(firstNamePath, middleNamePath);
		ParameterExpression<String> cityParameter = criteriaBuilder.parameter( String.class );//define input parameter
		criteria.where( criteriaBuilder.equal( root.get("city" ), cityParameter ) );//add where condition

		TypedQuery<Tuple> query = session.createQuery( criteria );//create a query object
		query.setParameter( cityParameter, "Coppell" );//set the parameter with the value
		List<Tuple> authorFNameandMiddleNames = query.getResultList();//get results
		for(Tuple a : authorFNameandMiddleNames){
			System.out.println(a.get(0));
			System.out.println(a.get(1));
		}
		session.close();
		sessionFactory.close();

	}
	
	public static void selectMultipleFieldsUsingCriteriaWrapper(){

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();//create criteria builder
		CriteriaQuery<AuthorWrapper> criteria = criteriaBuilder.createQuery(AuthorWrapper.class);//create criteria for the class that we are querying
		Root<Author> root = criteria.from( Author.class );//create root
		Path<String> firstNamePath = root.get("authorId").get("firstName");//select the firstName from the root
		Path<String> middleNamePath = root.get("middleName");//select the firstName from the root
		criteria.select(criteriaBuilder.construct(AuthorWrapper.class,firstNamePath, middleNamePath));
		ParameterExpression<String> cityParameter = criteriaBuilder.parameter( String.class );//define input parameter
		criteria.where( criteriaBuilder.equal( root.get("city" ), cityParameter ) );//add where condition
		criteria.orderBy(criteriaBuilder.asc(root.get("middleName")));//ORDER BY
		TypedQuery<AuthorWrapper> query = session.createQuery( criteria );//create a query object
		query.setParameter( cityParameter, "Coppell" );//set the parameter with the value
		List<AuthorWrapper> authorFNameandMiddleNames = query.getResultList();//get results
		for(AuthorWrapper a : authorFNameandMiddleNames){
			System.out.println(a.getFirsName());
			System.out.println(a.getMiddleName());
		}
		session.close();
		sessionFactory.close();

	}
	
	public static void performGroupByWithCriteria(){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();//create criteria builder
		CriteriaQuery<Tuple> criteria = criteriaBuilder.createQuery(Tuple.class);//create criteria for the class that we are querying
		Root<Author> root = criteria.from( Author.class );//create root
		Path<String> cityPath = root.get("city");//select city from the root
		criteria.multiselect(cityPath, criteriaBuilder.count(root));
		ParameterExpression<String> cityParameter = criteriaBuilder.parameter( String.class );//define input parameter
		criteria.groupBy(root.get("city" ), cityParameter);//add groupby condition
		criteria.having(criteriaBuilder.gt(criteriaBuilder.count(root), 1));

		TypedQuery<Tuple> query = session.createQuery( criteria );//create a query object
		query.setParameter( cityParameter, "Coppell" );//set the parameter with the value
		List<Tuple> authorFNameandMiddleNames = query.getResultList();//get results
		for(Tuple a : authorFNameandMiddleNames){
			System.out.println(a.get(0));
			System.out.println(a.get(1));
		}
		session.close();
		sessionFactory.close();		
	}


}

class AuthorWrapper{
	private String firsName,middleName;
	

	public AuthorWrapper(String firsName, String middleName) {
		super();
		this.firsName = firsName;
		this.middleName = middleName;
	}

	public String getFirsName() {
		return firsName;
	}

	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	
}
