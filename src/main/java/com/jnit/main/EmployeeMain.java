package com.jnit.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jnit.config.HibernateUtil;
import com.jnit.entities.Address;
import com.jnit.entities.Employee;
//select,update,insert,delete
//save,update,get,delete
//saveOrUpdate,load
//get vs load -> get returns null -> if entity with the id that u r looking for is not there
//load -> ObjectNotFoundException if entity with that id is not there
//load -> proxy object(temporary fake object(id set))
//select all the records from Employee -> HQL(class names and ur property names)
public class EmployeeMain {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tran = session.beginTransaction();
//		Employee emp = new Employee("a", "b", "c");
//		Address add = new Address();
//		add.setCity("Coppell");
//		emp.setAddress(add);
//		add.setEmployee(emp);
//		session.save(emp);
		//session.save(add);
		//Employee e = session.get(Employee.class,1L);
		
		//System.out.println(e.getfName());
		//e.setfName("x1");
		//session.delete(e);
		List<Employee>employees = session.createQuery("from Employee").getResultList();
		employees.forEach( a -> System.out.println(a.getfName()));
		tran.commit();
		session.close();
		sf.close();
	}

}
