package co.edu.icesi.ci.mvctutorial.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.mvctutorial.model.User;
import co.edu.icesi.ci.mvctutorial.model.UserGender;
import co.edu.icesi.ci.mvctutorial.model.UserType;
import co.edu.icesi.ci.mvctutorial.repository.UserRepository;
import co.edu.icesi.ci.mvctutorial.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);

	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Iterable<User> findAllDoctors() {
		return userRepository.findByType(UserType.doctor);
	}

	@Override
	public Iterable<User> findAllPatients() {
		return userRepository.findByType(UserType.patient);
	}

	@Override
	public Optional<User> findById(long id) {

		return userRepository.findById(id);
	}

	@Override
	public UserGender[] getGenders() {

		return UserGender.values();
	}

	@Override
	public UserType[] getTypes() {
		// TODO Auto-generated method stub
		return UserType.values();
	}

	@Override
	public void save(User user) {
		userRepository.save(user);

	}
}
