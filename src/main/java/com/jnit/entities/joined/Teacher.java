package com.jnit.entities.joined;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
@PrimaryKeyJoinColumn(name = "teacher_id", referencedColumnName = "person_id")
public class Teacher extends Person {

	@Column(name = "main_subject")
	private String mainSubject;

	private int salary;

	private String type; // Primary or Secondary School teacher

	public String getMainSubject() {
		return mainSubject;
	}

	public void setMainSubject(String mainSubject) {
		this.mainSubject = mainSubject;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
