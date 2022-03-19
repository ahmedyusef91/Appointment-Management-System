package com.stc.appointment.task.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stc.appointment.task.model.Appointment;
import com.stc.appointment.task.model.Patient;
import com.stc.appointment.task.model.enums.CancellationReasonType;
import com.stc.appointment.task.model.enums.SearchDateType;
import com.stc.appointment.task.model.exception.BusinessException;
import com.stc.appointment.task.service.AppointmentService;

/**
 * @author Ahmed.Yusef
 *
 * @Date Mar 19, 2022
 */
@RestController
@RequestMapping("/stc/task/AMS/appointment")
public class AppointmentController {
	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/all")
	public ResponseEntity<List<Appointment>> allAppointments() {
		List<Appointment> appointment = appointmentService.getAllAppointment();
		return ResponseEntity.status(HttpStatus.OK).body(appointment);
	}

	@GetMapping("/today")
	public ResponseEntity<List<Appointment>> todayAppointments() {
		List<Appointment> appointment = appointmentService.findAllTodayAppointment();
		return ResponseEntity.status(HttpStatus.OK).body(appointment);
	}

	@PostMapping(value = "/create")
	public ResponseEntity<Appointment> AddAppointment(@RequestBody Appointment appointment) throws BusinessException {
		appointment = appointmentService.saveAppointment(appointment);
		return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
	}

	@GetMapping("/cancel/{appointmentId}")
	public ResponseEntity<?> cancelAppointment(@PathVariable(name = "appointmentId") Integer appointmentId,
			@RequestParam("cancelReason") CancellationReasonType cancelReason) throws BusinessException {
		appointmentService.cancelAppointment(appointmentId, cancelReason);
		return ResponseEntity.status(HttpStatus.OK).body("Appointment cancelled successfully");
	}

	@PostMapping(value = "/searchByDate")
	public ResponseEntity<List<Appointment>> searchByDate(@RequestParam(name = "date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date,
			@RequestParam(name = "searchDateType") SearchDateType searchDateType) throws BusinessException {
		List<Appointment> appointments = appointmentService.searchAppointmentByDate(searchDateType, date);
		return ResponseEntity.status(HttpStatus.OK).body(appointments);
	}

	@PostMapping(value = "/searchByPatient")
	public ResponseEntity<List<Appointment>> searchByPatient(@RequestBody Patient patient) throws BusinessException {
		List<Appointment> appointments = appointmentService.searchByPatient(patient);
		return ResponseEntity.status(HttpStatus.OK).body(appointments);
	}

	@GetMapping(value = "/patientHistory/{patientId}")
	public ResponseEntity<List<Appointment>> findPatientHistory(@PathVariable(name = "patientId") Integer patientId)
			throws BusinessException {
		List<Appointment> appointments = appointmentService.findAllByPatientPatientId(patientId);
		return ResponseEntity.status(HttpStatus.OK).body(appointments);
	}

}
