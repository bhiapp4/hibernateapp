package com.jnit.entities.singletable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cd")
@DiscriminatorValue("International")
public class InternationalCD extends CD {

	private static final long serialVersionUID = 1L;
	@Column(name = "languages")
	private String languages;
	
	@Column(name = "region")
	private int region;
	
	public InternationalCD(){
		
	}
	
	public InternationalCD(String title, String artist, Date purchaseDate, double cost, String languages, int region){
		super(title,artist,purchaseDate,cost);
		this.languages = languages;
		this.region = region;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

}
