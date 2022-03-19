package com.stc.appointment.task.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stc.appointment.task.model.Appointment;
import com.stc.appointment.task.model.Patient;
import com.stc.appointment.task.model.enums.CancellationReasonType;
import com.stc.appointment.task.model.enums.SearchDateType;
import com.stc.appointment.task.model.exception.BusinessException;
import com.stc.appointment.task.repository.AppointmentRepository;
import com.stc.appointment.task.service.AppointmentService;

/**
 * @author Ahmed.Yusef
 *
 * @Date Mar 19, 2022
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Override
	public List<Appointment> getAllAppointment() {
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment saveAppointment(Appointment appointment) throws BusinessException {
		return appointmentRepository.save(appointment);
	}

	@Override
	public void cancelAppointment(Integer appointmentId, CancellationReasonType cancellationReason)
			throws BusinessException {
		try {
			Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
			appointment.get().setStatus(false);
			appointment.get().setCancellationReason(cancellationReason);
			appointmentRepository.save(appointment.get());
		} catch (BusinessException be) {
			be.printStackTrace();
			throw new BusinessException("there is no appointment");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Appointment> findAllTodayAppointment() {
		return appointmentRepository.findAllByDate(LocalDate.now());
	}

	@Override
	public List<Appointment> searchAppointmentByDate(SearchDateType searchDateType, LocalDate date) {
		List<Appointment> appointment = new ArrayList<Appointment>();
		if (searchDateType == SearchDateType.FUTURE) {
			appointment = appointmentRepository.findAllByDateAfter(date);
		} else {
			appointment = appointmentRepository.findAllByDateBefore(date);

		}
		return appointment;
	}

	@Override
	public List<Appointment> searchByPatient(Patient searchCriteria) {

		List<Appointment> result = getAllAppointment();

		if (searchCriteria.getName() == null && searchCriteria.getGender() == null
				&& searchCriteria.getPatientId() == null && searchCriteria.getPhone() == null)
			return result;

		Map<Integer, Appointment> appointmentHashMap = new HashMap<>();

		System.out.println("search Criteria" + searchCriteria);

		if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
			appointmentRepository.findByPatientNameContainingIgnoreCase(searchCriteria.getName())
					.forEach(s -> appointmentHashMap.put(s.getAppid(), s));

		} else if (searchCriteria.getGender() != null) {
			result.stream().filter(a -> a.getPatient().getGender().equals(searchCriteria.getGender()))
					.forEach(s -> appointmentHashMap.put(s.getAppid(), s));

		} else if (searchCriteria.getPatientId() != null) {
			result.stream().filter(a -> a.getPatient().getPatientId().equals(searchCriteria.getPatientId()))
					.forEach(s -> appointmentHashMap.put(s.getAppid(), s));

		} else if (searchCriteria.getPhone() != null) {
			result.stream().filter(a -> a.getPatient().getPhone().equals(searchCriteria.getPhone()))
					.forEach(s -> appointmentHashMap.put(s.getAppid(), s));
		}

		return new ArrayList<Appointment>(appointmentHashMap.values());

	}

	@Override
	public List<Appointment> findAllByPatientPatientId(Integer patientId) {
		return appointmentRepository.findAllByPatientPatientId(patientId);
	}

}
