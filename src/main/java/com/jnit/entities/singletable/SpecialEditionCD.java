package com.jnit.entities.singletable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cd")
@DiscriminatorValue("Special")
public class SpecialEditionCD extends CD {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "newFeatures")
	private String newFeatures;

	public SpecialEditionCD(String title, String artist, Date purchaseDate, double cost, String newFeatures){
		super(title,artist,purchaseDate,cost);
		this.newFeatures = newFeatures;

	}
	
	public String getNewFeatures() {
		return newFeatures;
	}

	public void setNewFeatures(String newFeatures) {
		this.newFeatures = newFeatures;
	}
}
