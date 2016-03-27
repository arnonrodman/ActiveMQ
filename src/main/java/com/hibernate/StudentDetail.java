package com.hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="STUDENT_DETAIL")
public class StudentDetail {
	
	@Id @GeneratedValue(generator = "newGenerator")
	@GenericGenerator(name="newGenerator",strategy = "foreign",parameters ={@Parameter(value="student",name="property")})
	private int studentId;
	
	@Column(name="STUDENT_MOBIL_NUMBER")
	private String studentMobileNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "studentId")
	private Student student;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentMobileNumber() {
		return studentMobileNumber;
	}

	public void setStudentMobileNumber(String studentMobileNumber) {
		this.studentMobileNumber = studentMobileNumber;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
	
}
