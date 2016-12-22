package com.jnit.entities.interceptors;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.jnit.entities.composite.Author;

public class DeleteInterceptor extends EmptyInterceptor{

	private static final long serialVersionUID = 1L;
	
	//called up on execution of delete, before removing the record from db
	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if(entity instanceof Author){
			Author a = (Author) entity;
			System.out.println(a.getAuthorId().getFirstName() + " is deleted");
		}
	}
}
