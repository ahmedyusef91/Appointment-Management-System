package com.stc.appointment.task.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.stc.appointment.task.model.Appointment;

/**
 * @author Ahmed.Yusef
 *
 * @Date Mar 19, 2022
 */
public interface AppointmentRepository
		extends JpaRepository<Appointment, Integer>, JpaSpecificationExecutor<Appointment> {

	List<Appointment> findAllByDate(LocalDate localDate);

	List<Appointment> findAllByDateAfter(LocalDate date);

	List<Appointment> findAllByDateBefore(LocalDate date);
	
	List<Appointment> findByPatientNameContainingIgnoreCase(String name);
	
	List<Appointment> findAllByPatientPatientId(Integer patientId);
	
}
