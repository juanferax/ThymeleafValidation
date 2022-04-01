package co.edu.icesi.ci.thymeval.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.thymeval.model.User;
import co.edu.icesi.ci.thymeval.model.UserType;
import co.edu.icesi.ci.thymeval.model.UserGender;
import co.edu.icesi.ci.thymeval.repository.UserRepository;
import co.edu.icesi.ci.thymeval.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void save(User user) {
		userRepository.save(user);

	}

	public Optional<User> findById(long id) {

		return userRepository.findById(id);
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}
	
	public Iterable<User> findAllPatients() {
		return userRepository.findByType(UserType.patient);
	}
	
	public Iterable<User> findAllDoctors() {
		return userRepository.findByType(UserType.doctor);
	}


	public void delete(User user) {
		userRepository.delete(user);

	}

	public UserGender[] getGenders() {
		
		return UserGender.values();
	}

	public UserType[] getTypes() {
		// TODO Auto-generated method stub
		return UserType.values();
	}

	public boolean confirmPassword(Long id, String currentPassword) {
		return userRepository.findById(id).get().getPassword().equals(currentPassword);
	}
}
