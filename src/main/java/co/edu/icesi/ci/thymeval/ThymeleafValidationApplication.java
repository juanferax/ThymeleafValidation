package co.edu.icesi.ci.thymeval;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.ci.thymeval.model.UserApp;
import co.edu.icesi.ci.thymeval.model.UserGender;
import co.edu.icesi.ci.thymeval.model.UserType;
import co.edu.icesi.ci.thymeval.service.UserServiceImpl;

@SpringBootApplication
public class ThymeleafValidationApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(ThymeleafValidationApplication.class, args);
		
		UserServiceImpl u = c.getBean(UserServiceImpl.class);
		UserApp user1 = new UserApp();
		user1.setUsername("juanfer");
		user1.setPassword("{noop}admin123");
		user1.setName("Juan");
		user1.setEmail("jc@gmail.com");
		user1.setType(UserType.admin);
		user1.setGender(UserGender.masculine);
		user1.setBirthDate(LocalDate.now().minusMonths(1));
		u.save(user1);
		UserApp user2 = new UserApp();
		user2.setUsername("anita");
		user2.setPassword("{noop}anita");
		user2.setName("Ana");
		user2.setEmail("ana@gmail.com");
		user2.setType(UserType.doctor);
		user2.setGender(UserGender.femenine);
		user2.setBirthDate(LocalDate.now().minusMonths(1));
		u.save(user2);
	}

}
