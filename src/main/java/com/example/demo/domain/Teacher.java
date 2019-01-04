package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.example.demo.validation.PersonName;

@Entity
@Table(name="teacher")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="teacher_id")
	private Long teacherId;
	
	@Column(name="first_name")
	/*@Size(max=12, message="First name must be 12 characters max")
	@NotEmpty(message="First name is required.")*/
	@PersonName(max=50, message="First name is invalid.")
	private String firstName;
	
	@Column(name="last_name")
//	@Size(max=50, message="Last name must be 1 to 12 characters")
//	@NotEmpty(message="Last name is required.")
	@PersonName(max=50, message="Last name is invalid.")
	private String lastName;
	
	@Column(name="email")
	//@Email(message="Email is invalid.")
	//@NotEmpty(message="Email is required.")
	@Pattern(regexp="(\\S+@\\S+\\.[a-zA-Z]{2,3})|([^.]{0})", message="Email is invalid.")
	private String email;
	
	@Column(name="phone_number")
	@Pattern(regexp="(\\d{3}-){2}\\d{4}")
	private String phoneNumber;
	
	public Teacher() {
		this("", "", "", "");
	}
	
	public Teacher(String firstName, String lastName, String email, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + "]";
	}
	
	
}
