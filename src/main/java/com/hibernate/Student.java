package com.hibernate;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="STUDENT")
public class Student {
	
	@Id
	@GeneratedValue
	private int studentId;
	
	@Column(name="name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "studentId")
	private StudentDetail studentDetail;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private StudentAddress studentAddress;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	private Set<StudentCertification> studentCertifications = new HashSet<>();
			
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public StudentDetail getStudentDetail() {
		return studentDetail;
	}
	public void setStudentDetail(StudentDetail studentDetail) {
		this.studentDetail = studentDetail;
	}
	public StudentAddress getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(StudentAddress studentAddress) {
		this.studentAddress = studentAddress;
	}

	
}
