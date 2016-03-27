package com.hibernate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT_ADDRESS")
public class StudentAddress {
	
	@Id
	@GeneratedValue
	private int addresId;
	
	private String addressDetails;
	
	@OneToMany(cascade =CascadeType.ALL , mappedBy ="studentAddress")
	private Set<Student> students =  new HashSet<Student>(0);

	
	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public int getAddresId() {
		return addresId;
	}

	public void setAddresId(int addresId) {
		this.addresId = addresId;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
}
