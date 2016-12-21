package com.jnit.entities.singletable;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
//SINGLE_TABLE - one table for all three entities
//Joined -- common values go to parent table and child tables has their own values http://www.javaroots.com/2013/07/hibernate-inheritance-joined-strategy.html
//Table_per_class - separate table for each class - duplicate info stored between tables
@Entity
@Table(name="cd")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="cd_type",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("Cd")
public class CD implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
    @Column(name = "cd_id")
	private Long id;

 	private String title;
    
    private String artist;

    @Column(name = "purchase_date")
	private Date purchaseDate;
    
	private double cost;
	
	public CD(String title, String artist, Date purchaseDate, double cost){
		this.title = title;
		this.artist=artist;
		this.purchaseDate=purchaseDate;
		this.cost=cost;
	}
	
	public CD(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
