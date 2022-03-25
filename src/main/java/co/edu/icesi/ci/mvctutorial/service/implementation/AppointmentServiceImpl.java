package co.edu.icesi.ci.mvctutorial.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.mvctutorial.model.Appointment;
import co.edu.icesi.ci.mvctutorial.repository.AppointmentRepository;
import co.edu.icesi.ci.mvctutorial.service.interfaces.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	private AppointmentRepository appointmentRepository;

	@Autowired
	public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public void delete(Appointment user) {
		appointmentRepository.delete(user);

	}

	@Override
	public Iterable<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	@Override
	public Optional<Appointment> findById(long id) {

		return appointmentRepository.findById(id);
	}

	@Override
	public void save(Appointment user) {
		appointmentRepository.save(user);

	}
}
