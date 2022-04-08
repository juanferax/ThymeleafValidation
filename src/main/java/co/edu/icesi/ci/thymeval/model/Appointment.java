package co.edu.icesi.ci.thymeval.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Entity
@Data
public class Appointment {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FutureOrPresent
	@NotNull(message = "Date cannot be blank")
	private LocalDate date;
	
	@DateTimeFormat(iso = ISO.TIME)
	@NotNull(message="Time cannot be blank")
	private LocalTime time;
	
	@ManyToOne
	@NotNull(message="Patient cannot be blank")
	private UserApp patient;
	
	@ManyToOne
	@NotNull(message="Doctor cannot be blank")
	private UserApp doctor;
}
