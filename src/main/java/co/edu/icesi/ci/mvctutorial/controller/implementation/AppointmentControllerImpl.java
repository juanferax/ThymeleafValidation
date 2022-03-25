package co.edu.icesi.ci.mvctutorial.controller.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.mvctutorial.controller.interfaces.AppointmentController;
import co.edu.icesi.ci.mvctutorial.model.Appointment;
import co.edu.icesi.ci.mvctutorial.service.interfaces.AppointmentService;
import co.edu.icesi.ci.mvctutorial.service.interfaces.UserService;

@Controller
public class AppointmentControllerImpl implements AppointmentController {

	AppointmentService appointmentService;
	UserService userService;

	@Autowired
	public AppointmentControllerImpl(AppointmentService appointmentService, UserService userService) {
		this.userService = userService;
		this.appointmentService = appointmentService;
	}

	@Override
	@GetMapping("/apps/add")
	public String addApp(Model model) {
		model.addAttribute("app", new Appointment());
		model.addAttribute("doctors", userService.findAllDoctors());
		model.addAttribute("patients", userService.findAllPatients());
		return "apps/add-app";
	}

	@Override
	@GetMapping("/apps/del/{id}")
	public String deleteApp(@PathVariable("id") long id) {
		Appointment app = appointmentService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		appointmentService.delete(app);
		return "redirect:/apps/";
	}

	@Override
	@GetMapping("/apps/")
	public String indexApp(Model model) {
		model.addAttribute("apps", appointmentService.findAll());
		return "apps/index";
	}

	@Override
	@PostMapping("/apps/add")
	public String saveApp(@RequestParam(value = "action", required = true) String action, Appointment app,
			Model model) {
		if (!action.equals("Cancel"))
			appointmentService.save(app);
		return "redirect:/apps/";
	}

	@Override
	@GetMapping("/apps/edit/{id}")
	public String showUpdateApp(@PathVariable("id") long id, Model model) {
		Optional<Appointment> app = appointmentService.findById(id);
		if (app == null)
			throw new IllegalArgumentException("Invalid appointment Id:" + id);
		model.addAttribute("app", app.get());

		model.addAttribute("doctors", userService.findAllDoctors());
		model.addAttribute("patients", userService.findAllPatients());
		return "apps/update-app";
	}

	@Override
	@PostMapping("/apps/edit/{id}")
	public String updateApp(@PathVariable("id") long id, @RequestParam(value = "action", required = true) String action,
			Appointment app) {
		if (!action.equals("Cancel")) {
			appointmentService.save(app);
		}
		return "redirect:/apps/";
	}
}
