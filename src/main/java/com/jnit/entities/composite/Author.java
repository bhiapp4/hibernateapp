package com.jnit.entities.composite;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name="author")
@NamedQueries(value={
		@NamedQuery(name="authorsByCity", query="FROM Author where city=?")
})
@NamedNativeQueries(value={
		@NamedNativeQuery(name="selectAll", query="select * from author")
})
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Audited
public class Author {
	@Id
	private AuthorId authorId;
	@NotAudited
	private String middleName;
	private String isbn;
	private String city;
	
	@Version//optimistic locking column
	private Integer versionId;

	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public AuthorId getAuthorId() {
		return authorId;
	}

	public void setAuthorId(AuthorId authorId) {
		this.authorId = authorId;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
//	@Version
//	public Date updatedDate;
//
//	public Date getUpdatedDate() {
//		return updatedDate;
//	}
//
//	public void setUpdatedDate(Date updatedDate) {
//		this.updatedDate = updatedDate;
//	}
	

}
