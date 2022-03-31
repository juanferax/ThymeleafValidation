package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class User {
	
	public interface ValidationStepOne {
        // validation group marker interface
    }

    public interface ValidationStepTwo {
        // validation group marker interface
    }

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@NotBlank(groups = ValidationStepOne.class)
	private String username;
	
	@NotBlank(groups = ValidationStepOne.class)
	private String password;
	
	@NotBlank(groups = ValidationStepTwo.class)
	private String name;
	
	@NotBlank(groups = ValidationStepTwo.class)
	@Email
	private String email;
	
	@NotNull(groups = ValidationStepTwo.class, message="User type cannot be blank")
	private UserType type;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(groups = ValidationStepOne.class, message="Birth Date cannot be blank")
	@Past
	private LocalDate birthDate;
	
	@NotNull(groups = ValidationStepTwo.class, message="Gender cannot be blank")
	private UserGender gender;
	
//	@OneToMany
//	private List<Appointment> appointments;
}
