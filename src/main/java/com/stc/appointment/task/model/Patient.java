package com.stc.appointment.task.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ahmed.Yusef
 *
 * @Date Mar 19, 2022
 */
@Entity
@Table(name = "Patients")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PATIENT_ID")
	private Integer patientId;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "NAME")
	private String name;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "PHONE")
	private String phone;

	public Patient(String email, String name, String gender, String phone) {
		this.email = email;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
	}
	
	public Patient() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
