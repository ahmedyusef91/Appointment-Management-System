package com.stc.appointment.task.service;

import java.time.LocalDate;
import java.util.List;

import com.stc.appointment.task.model.Appointment;
import com.stc.appointment.task.model.Patient;
import com.stc.appointment.task.model.enums.CancellationReasonType;
import com.stc.appointment.task.model.enums.SearchDateType;
import com.stc.appointment.task.model.exception.BusinessException;

/**
 * @author Ahmed.Yusef
 *
 * @Date Mar 19, 2022
 */
public interface AppointmentService {

	List<Appointment> getAllAppointment();

	List<Appointment> findAllTodayAppointment();

	Appointment saveAppointment(Appointment appointment) throws BusinessException;

	void cancelAppointment(Integer appointmentId, CancellationReasonType cancellationReason) throws BusinessException;

	List<Appointment> searchAppointmentByDate(SearchDateType searchDateType, LocalDate date);

	List<Appointment> searchByPatient(Patient patient);

	List<Appointment> findAllByPatientPatientId(Integer patientId);

}
