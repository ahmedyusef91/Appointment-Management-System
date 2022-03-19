package com.stc.appointment.task.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.stc.appointment.task.model.enums.CancellationReasonType;

/**
 * @author Ahmed.Yusef
 *
 * @Date Mar 19, 2022
 */
@Entity
@Table(name = "APPOINTMENTS")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "APP_ID")
	private Integer appid;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "PATIENT_ID") })
	private Patient patient;

	@Column(name = "STATUS")
	private boolean status;

	@Column(name = "DATE")
	private LocalDate date;

	@Column(name = "CANCELATION_REASON")
	private CancellationReasonType cancellationReason;

	@Column(name = "Comment")
	private String comment;

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public CancellationReasonType getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(CancellationReasonType cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

}
