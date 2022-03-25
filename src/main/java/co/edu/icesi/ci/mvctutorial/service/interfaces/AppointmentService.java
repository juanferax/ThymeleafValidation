package co.edu.icesi.ci.mvctutorial.service.interfaces;

import java.util.Optional;

import co.edu.icesi.ci.mvctutorial.model.Appointment;

public interface AppointmentService {
	public void save(Appointment user);

	public Optional<Appointment> findById(long id);

	public Iterable<Appointment> findAll();

	public void delete(Appointment user);
}
