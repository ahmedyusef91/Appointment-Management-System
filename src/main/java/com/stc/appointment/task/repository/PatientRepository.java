package com.stc.appointment.task.repository;

import org.springframework.data.repository.CrudRepository;

import com.stc.appointment.task.model.Patient;

/**
 * @author Ahmed.Yusef
 *
 * @Date Mar 19, 2022
 */
public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
